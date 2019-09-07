package com.netplus.catpark.service;

import com.netplus.catpark.dao.define.UserParkingDefineMapper;
import com.netplus.catpark.dao.define.UserParkingInfoDefineMapper;
import com.netplus.catpark.dao.generator.*;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.SpaceInfoBO;
import com.netplus.catpark.domain.bo.UserParkingSpaceInfoBO;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.model.exception.ErrorCode;
import com.netplus.catpark.domain.model.exception.ErrorUtil;
import com.netplus.catpark.domain.po.*;
import com.netplus.catpark.service.util.*;
import com.netplus.catpark.service.util.GeoHashUtil.GeoHashHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 13:07.
 */

@Service
public class ShareSpaceService {
    private final String keepAuthCode = "client-";

    @Autowired
    ShortMessageUtil shortMessageUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserParkingMapper userParkingMapper;

    @Autowired
    UserParkingDefineMapper userParkingDefineMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserParkingOrderTableMapper userParkingOrderTableMapper;

    @Autowired
    PublishOrderTableMapper publishOrderTableMapper;

    @Autowired
    UserParkingInfoDefineMapper userParkingInfoDefineMapper;

    @Autowired
    UserParkingInfoMapper userParkingInfoMapper;

    @Autowired
    UserLicenseRelMapper userLicenseRelMapper;
    /**
     * 手机发送验证码
     * @param phoneNum
     * @return
     */
    public Response phoneCheck(String phoneNum){
        if(!ParamCheckUtil.isMobile(phoneNum)){
            return new Response(1,"手机号格式错误", IsSuccessDTO.builder().isSuccess(false).build());
        }
        //获取六位验证码
        String authCode = CodeProductUtil.getRandomVerifyCode();

        if(shortMessageUtil.sendSms(phoneNum, authCode)){
            //将验证码存入redis中，进行下一步的验证
            redisTemplate.opsForValue().set(keepAuthCode + phoneNum, authCode, 5L, TimeUnit.MINUTES);
            return new Response(0,"success", IsSuccessDTO.builder().isSuccess(true).build());
        }
        return new Response(1,"fail",IsSuccessDTO.builder().isSuccess(false).build());
    }

    /**
     * 用户发布共享车位信息
     * @param publishSpaceDTO
     * @return
     */
    public Response<IsSuccessDTO> publishSpace(PublishSpaceDTO publishSpaceDTO){
        Long userId = ContextUser.getUserId();
        if(publishSpaceDTO.getPhoneNum() == null
                || publishSpaceDTO.getAuthCode() == null){
            return ResponseUtil.makeFail("数据为空");
        }
        String authCode = publishSpaceDTO.getAuthCode();
        String phoneNum = publishSpaceDTO.getPhoneNum();
        String redisAuthCode = (String) redisTemplate.opsForValue().get(keepAuthCode+phoneNum);
        if(redisAuthCode == null){
            return ResponseUtil.makeFail("验证码过期！");
        }
        if(!redisAuthCode.equals(authCode)){
            return ResponseUtil.makeFail("验证码错误！");
        }
        Date date = new Date();
        UserParking userParking = new UserParking();
        userParking.setGmtCreate(date);
        userParking.setParkingInfoId(publishSpaceDTO.getParkingId());
        userParking.setPayment(publishSpaceDTO.getPayment());
        userParking.setGmtUpdate(date);
        userParking.setUserId(userId);
        userParking.setBeginBookTime(tranStringToDate(publishSpaceDTO.getBeginBookTime()));
        userParking.setEndBookTime(tranStringToDate(publishSpaceDTO.getEndBookTime()));
        userParking.setParkingType(publishSpaceDTO.getParkingType());
        userParking.setDeleted(false);
        userParkingMapper.insert(userParking);
        return new Response<>(0,"success", IsSuccessDTO.builder().isSuccess(true).build());
    }

    private Date tranStringToDate(String timeString){
        try {
            Date parse = new SimpleDateFormat("hh:mm:ss").parse(timeString);
            return parse;
        } catch (ParseException e) {
            ErrorUtil.throwError(ErrorCode.SYSTEM_ERROR);
        }
        return null;
    }

    /**
     * 根据用户当前坐标获取用户周围的停车位
     * @param positionDTO
     * @return
     */
    public Response<ParkingListDTO> getNearbyParkingList(PositionDTO positionDTO){
        if(positionDTO.getLat() == null || positionDTO.getLng() == null){
            return ResponseUtil.makeFail("参数为空");
        }
        //获取该点周围的共享停车位
        List<String> geoHashList = GeoHashHelperUtil.around(positionDTO.getLat(),positionDTO.getLng());
        List<UserParkingSpaceInfoBO> userParkingSpaceInfoBOList = new ArrayList<>();
        Set<Long> parkingIdSet = new HashSet<>();
        List<UserParkingInfo> userParkingList = new ArrayList<>();
        geoHashList.forEach(b -> {
            System.out.println(b);
            userParkingList.addAll(userParkingInfoDefineMapper.getNearbyParking(b.substring(0,5)));
        });

        userParkingList.forEach(b -> {
            if(!parkingIdSet.contains(b.getId())) {
                UserParkingSpaceInfoBO build = UserParkingSpaceInfoBO.
                        builder().
                        userParkingSpaceId(b.getId()).
                        lat(b.getLatitude()).
                        lng(b.getLongitude()).
                        build();
                userParkingSpaceInfoBOList.add(build);
                parkingIdSet.add(b.getId());
            }
        });

        return new Response(0,"success", ParkingListDTO.
                builder().
                parkingList(Collections.singletonList(userParkingSpaceInfoBOList)).build());
    }

    /**
     * 获取共享停车位的信息
     * @param userParkingId
     * @return
     */
    public Response<UserSpaceInfoDTO> getSpaceInfo(Long userParkingId){
        MyAssert.notNull(userParkingId);
        // 获取用户停车位的信息
        UserParkingExample example = new UserParkingExample();
        example.createCriteria().andIdEqualTo(userParkingId).andDeletedEqualTo(false);
        List<UserParking> userParkings = userParkingMapper.selectByExample(example);
        MyAssert.notNull(userParkings);
        UserParking userParking = userParkings.get(0);


        //获取停车位信息
        UserParkingInfoExample parkingInfoExample = new UserParkingInfoExample();
        parkingInfoExample.createCriteria().andIdEqualTo(userParking.getParkingInfoId()).andDeletedEqualTo(false);
        UserParkingInfo userParkingInfo = userParkingInfoMapper.selectByExample(parkingInfoExample).get(0);

        if(userParkings.size() == 0){
            return ResponseUtil.makeFail("车位不存在！");
        }
        // 获取车位发布者的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(userParking.getUserId()).andDeletedEqualTo(false);
        User user = userMapper.selectByExample(userExample).get(0);
        UserSpaceInfoDTO build = UserSpaceInfoDTO.builder().
                address(userParkingInfo.getAddress()).
                name(user.getNickName()).
                beginBookTime(userParking.getBeginBookTime()).
                endBookTime(userParking.getEndBookTime()).
                parkingType(userParking.getParkingType()).
                payment(userParking.getPayment()).
                lat(userParkingInfo.getLatitude()).
                lng(userParkingInfo.getLongitude()).
                phoneNum(user.getPhoneNum()).
                build();
        return new Response<>(0,"success",build);
    }

    /**
     * 根据用户停车位id，进行预定车位
     * @param bookUserParkingInfoDTO
     * @return
     */
    public Response<UserParkingBookDTO> bookUserParking(BookUserParkingInfoDTO bookUserParkingInfoDTO){
        if(bookUserParkingInfoDTO == null){
            return ResponseUtil.makeFail("共享车位为空");
        }
        Long userId = ContextUser.getUserId();
        Date date = new Date();
        String orderId = UUID.randomUUID().toString().replaceAll("-","");
        UserLicenseRelExample  licenseRelExample = new UserLicenseRelExample();
        licenseRelExample.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(bookUserParkingInfoDTO.getPlateId()).andDeletedEqualTo(false);
        UserLicenseRel userLicenseRel = userLicenseRelMapper.selectByExample(licenseRelExample).get(0);
        // 共享车位插入预定表
        UserParkingOrderTable order = new UserParkingOrderTable();
        order.setUserId(userId);
        order.setUserParkingId(bookUserParkingInfoDTO.getUserParkingId());
        order.setGmtCreate(date);
        order.setGmtUpdate(date);
        order.setDeleted(false);
        order.setPayment(bookUserParkingInfoDTO.getPayment());
        order.setParikingTime(bookUserParkingInfoDTO.getTime());
        order.setOrderId(orderId);
        order.setLicensePlate(userLicenseRel.getLicensePlate());
        order.setOrderStatus((byte)5);
        order.setPrice(bookUserParkingInfoDTO.getPrice());
        userParkingOrderTableMapper.insert(order);

        //插入发布者的订单表
        PublishOrderTable publishOrderTable = new PublishOrderTable();
        UserParkingExample example = new UserParkingExample();
        example.createCriteria().andIdEqualTo(bookUserParkingInfoDTO.getUserParkingId()).andDeletedEqualTo(false);
        List<UserParking> userParkings = userParkingMapper.selectByExample(example);
        publishOrderTable.setOrderId(orderId);
        publishOrderTable.setParkingId(bookUserParkingInfoDTO.getUserParkingId());
        publishOrderTable.setDeleted(false);
        publishOrderTable.setGmtCreate(date);
        publishOrderTable.setGmtUpdate(date);
        publishOrderTable.setRentUserId(userId);
        publishOrderTable.setPublishUserId(userParkings.get(0).getUserId());
        publishOrderTableMapper.insert(publishOrderTable);

        return new Response<UserParkingBookDTO>(0,"success",
                UserParkingBookDTO.
                        builder().
                        userParkingId(bookUserParkingInfoDTO.getUserParkingId()).
                        build());
    }

    /**
     * 获取用户停车位信息
     * @return
     */
    public Response<UserParkingInfoListDTO> getUserParkingInfo(){
        UserParkingInfoExample example = new UserParkingInfoExample();
        Long userId = ContextUser.getUserId();
        example.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<UserParkingInfo> userParkingInfos = userParkingInfoMapper.selectByExample(example);
        return new Response<UserParkingInfoListDTO>(0,"success", UserParkingInfoListDTO.
                builder().
                userParkingInfos(userParkingInfos).
                build());
    }
}
