package com.netplus.catpark.service;

import com.netplus.catpark.dao.define.UserParkingDefineMapper;
import com.netplus.catpark.dao.generator.UserMapper;
import com.netplus.catpark.dao.generator.UserParkingMapper;
import com.netplus.catpark.dao.generator.UserParkingOrderTableMapper;
import com.netplus.catpark.domain.bo.SpaceInfoBO;
import com.netplus.catpark.domain.bo.UserParkingSpaceInfoBO;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.model.exception.ErrorCode;
import com.netplus.catpark.domain.model.exception.ErrorUtil;
import com.netplus.catpark.domain.po.*;
import com.netplus.catpark.resolver.ApiExceptionResolver;
import com.netplus.catpark.service.util.*;
import com.netplus.catpark.service.util.GeoHashUtil.GeoHashHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
        Long userId = 1L;
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
        userParking.setAddress(publishSpaceDTO.getAddress());
        userParking.setGmtCreate(date);
        userParking.setLatitude(publishSpaceDTO.getLat());
        userParking.setLongitude(publishSpaceDTO.getLng());
        userParking.setPayment(publishSpaceDTO.getPayment());
        userParking.setGmtUpdate(date);
        userParking.setUserId(userId);
        userParking.setBeginBookTime(tranStringToDate(publishSpaceDTO.getBeginBookTime()));
        userParking.setEndBookTime(tranStringToDate(publishSpaceDTO.getEndBookTime()));
        userParking.setParkingType(publishSpaceDTO.getParkingType());
        userParking.setDeleted(false);
        userParking.setPositionGeoHash(GeoHashHelperUtil.encode(publishSpaceDTO.getLat(),publishSpaceDTO.getLng()));
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
        List<UserParking> userParkingList = new ArrayList<>();
        geoHashList.forEach(b -> {
            System.out.println(b);
            userParkingList.addAll(userParkingDefineMapper.getNearbyParking(b.substring(0,5)));
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
        if(userParkings.size() == 0){
            return ResponseUtil.makeFail("车位不存在！");
        }
        UserParking userParking = userParkings.get(0);
        // 获取车位发布者的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(userParking.getUserId()).andDeletedEqualTo(false);
        User user = userMapper.selectByExample(userExample).get(0);
        UserSpaceInfoDTO build = UserSpaceInfoDTO.builder().
                address(userParking.getAddress()).
                name(user.getNickName()).
                beginBookTime(userParking.getBeginBookTime()).
                endBookTime(userParking.getEndBookTime()).
                parkingType(userParking.getParkingType()).
                payment(userParking.getPayment()).
                lat(userParking.getLatitude()).
                lng(userParking.getLongitude()).
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
        Long userId = 1L;
        Date date = new Date();
        String orderId = UUID.randomUUID().toString().replaceAll("-","");
        UserParkingOrderTable order = new UserParkingOrderTable();
        order.setUserId(userId);
        order.setUserParkingId(bookUserParkingInfoDTO.getUserParkingId());
        order.setGmtCreate(date);
        order.setGmtUpdate(date);
        order.setDeleted(false);
        order.setPayment(bookUserParkingInfoDTO.getPayment());
        order.setParikingTime(bookUserParkingInfoDTO.getTime());
        order.setOrderId(orderId);
        order.setOrderStatus((byte)5);
        order.setPrice(bookUserParkingInfoDTO.getPrice());
        userParkingOrderTableMapper.insert(order);
        return new Response<UserParkingBookDTO>(0,"success",
                UserParkingBookDTO.
                        builder().
                        userParkingId(bookUserParkingInfoDTO.getUserParkingId()).
                        build());
    }
}
