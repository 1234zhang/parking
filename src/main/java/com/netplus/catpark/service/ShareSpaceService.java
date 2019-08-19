package com.netplus.catpark.service;

import com.netplus.catpark.dao.generator.UserParkingMapper;
import com.netplus.catpark.domain.dto.PublishSpaceDTO;
import com.netplus.catpark.domain.dto.PublishSuccessDTO;
import com.netplus.catpark.domain.dto.SuccessDTO;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.UserParking;
import com.netplus.catpark.service.util.*;
import com.netplus.catpark.service.util.GeoHashUtil.GeoHashHelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    /**
     * 手机发送验证码
     * @param phoneNum
     * @return
     */
    public Response phoneCheck(String phoneNum){
        if(!ParamCheckUtil.isMobile(phoneNum)){
            return new Response(1,"手机号格式错误", SuccessDTO.builder().isSuccess(false).build());
        }
        //获取六位验证码
        String authCode = CodeProductUtil.getRandomVerifyCode();

        if(shortMessageUtil.sendSms(phoneNum, authCode)){
            //将验证码存入redis中，进行下一步的验证
            redisTemplate.opsForValue().set(keepAuthCode + phoneNum, authCode, 5L, TimeUnit.MINUTES);
            return new Response(0,"success", SuccessDTO.builder().isSuccess(true).build());
        }
        return new Response(1,"fail",SuccessDTO.builder().isSuccess(false).build());
    }

    /**
     * 用户发布共享车位信息
     * @param publishSpaceDTO
     * @return
     */
    public Response<PublishSuccessDTO> publishSpace(PublishSpaceDTO publishSpaceDTO){
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
        userParking.setDeleted(false);
        userParking.setPositionGeoHash(GeoHashHelperUtil.encode(publishSpaceDTO.getLat(),publishSpaceDTO.getLng()));
        userParkingMapper.insert(userParking);
        return new Response<>(0,"success", PublishSuccessDTO.builder().isSuccess(true).build());
    }
}
