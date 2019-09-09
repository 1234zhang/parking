package com.netplus.catpark.service;

import com.netplus.catpark.dao.define.UserParkingInfoDefineMapper;
import com.netplus.catpark.dao.generator.UserLicenseRelMapper;
import com.netplus.catpark.dao.generator.UserMapper;
import com.netplus.catpark.dao.generator.UserParkingInfoMapper;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.RedisUser;
import com.netplus.catpark.domain.bo.UserInfoBO;
import com.netplus.catpark.domain.dto.*;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.domain.po.*;
import com.netplus.catpark.service.util.DecodeUtil;
import com.netplus.catpark.service.util.GeoHashUtil.GeoHashHelperUtil;
import com.netplus.catpark.service.util.MyAssert;
import com.netplus.catpark.service.util.ResponseUtil;
import com.netplus.catpark.service.util.wxMinProgram.WxMinProgramUtil;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:09.
 */

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserLicenseRelMapper userLicenseRelMapper;

    @Autowired
    UserParkingInfoMapper userParkingInfoMapper;

    @Autowired
    UserParkingInfoDefineMapper userParkingInfoDefineMapper;

    /**
     * 用户登录相关处理
     * @param minUser
     * @return
     * @throws IOException
     */
    public Response<UserReturnDTO> minLogin(CheckUserInfoDTO minUser) throws IOException {
        String code = minUser.getCode();
        String vi = minUser.getVi();
        String encryptedData = minUser.getEncryptedData();
        UserInfoBO userInfoBO = WxMinProgramUtil.getOpenIdAndSessionKey(code);
        if(userInfoBO == null){
            throw new RuntimeException("code 错误");
        }
        DecodeUtil.decipherByUserInfo(encryptedData, vi, userInfoBO);
        return loginSetting(userInfoBO, "min");
    }

    /**
     * 检查用户是否绑定手机号
     * @return
     */

    public Response<IsBindPhoneNumDTO> checkBindPhoneNum(){
        final String UNKNOWN = "unknown";
        RedisUser redisUser = ContextUser.getUser();
        Long userId = redisUser.getId();
        IsBindPhoneNumDTO bindPhoneNum = IsBindPhoneNumDTO.builder().build();
        if (null == redisUser.getPhoneNum() || "".equals(redisUser.getPhoneNum())) {
            bindPhoneNum.setStatus(0);
        }else{
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(userId).andDeletedEqualTo(false);
            List<User> users = userMapper.selectByExample(example);
            MyAssert.isTrue(!users.isEmpty(), "用户没有注册");
            String phoneNum = users.get(0).getPhoneNum();

            if (UNKNOWN.equalsIgnoreCase(phoneNum)) {
                bindPhoneNum.setStatus(0);
            }else{
                bindPhoneNum.setStatus(1);
                bindPhoneNum.setPhoneNum(phoneNum);
            }
        }
        return new Response<>(0,"success", bindPhoneNum);
    }

    public Response<IsSuccessDTO> checkAuthCode(PhoneNumCodeCheckDTO checkDTO){
        RedisUser user = ContextUser.getUser();
        if(checkDTO.getAuthCode() == null || checkDTO.getPhoneNum() == null){
            return ResponseUtil.makeFail("参数不能为空");
        }
        String authCode = redisTemplate.opsForValue().get("client-" + checkDTO.getPhoneNum()).toString();
        IsSuccessDTO isSuccess = IsSuccessDTO.builder().build();
        if(!authCode.equals(checkDTO.getAuthCode())){
            isSuccess.setIsSuccess(false);
        }else{
            UserInfoBO build = UserInfoBO.builder().
                    unionId(user.getUninId()).
                    openId(user.getOpenId()).
                    phoneNum(checkDTO.getPhoneNum()).
                    avatar(user.getHeadUrl()).gender(user.getSex()).build();
            Long userId = saveUserInfoIntoDB(build, user.getId());
            user.setId(userId);
            saveUserIntoRedis(build,user.getToken(),"min");
            isSuccess.setIsSuccess(true);
        }
        return new Response<>(0,"success", isSuccess);
    }

    public Response<IsSuccessDTO> insertLicense(String licensePlate){
        if(licensePlate == null || "".equals(licensePlate)){
            return ResponseUtil.makeFail("参数不能为空");
        }
        if(getLicensePlate(licensePlate)){
            return ResponseUtil.makeFail("车牌已经存在");
        }
        saveLicenseIntoDB(licensePlate);
        IsSuccessDTO build = IsSuccessDTO.builder().isSuccess(true).build();
        return new Response<>(0,"success", build);
    }

    /**
     * 判断车牌号是否已经存在；
     * @param license
     * @return
     */
    private boolean getLicensePlate(String license){
        UserLicenseRelExample example = new UserLicenseRelExample();
        example.createCriteria().andLicensePlateEqualTo(license).andDeletedEqualTo(false);
        List<UserLicenseRel> userLicenseRels = userLicenseRelMapper.selectByExample(example);
        return userLicenseRels.size() >= 1;
    }

    private void saveLicenseIntoDB(String license){
        Long userId = ContextUser.getUserId();

        UserLicenseRel rel = new UserLicenseRel();
        rel.setLicensePlate(license);
        rel.setUserId(userId);
        rel.setGmtCreate(new Date());
        rel.setGmtUpdate(new Date());
        rel.setDeleted(false);
        userLicenseRelMapper.insert(rel);
    }
    /**
     * 用户信息存储和更新
     * @param userInfoBO
     * @param type
     * @return
     */
    private Response<UserReturnDTO> loginSetting(UserInfoBO userInfoBO, String type){
        Long userId = null;
        String token = null;
        String phoneNum = "-1";
        // 根据unionId获取用户信息
        User user = getUser(userInfoBO.getUnionId());
        // 判断用户是否存在
        if(user != null){
            userId = user.getId();
            if(userInfoBO.getPhoneNum() != null && !userInfoBO.getPhoneNum().equals("")){
                phoneNum = userInfoBO.getPhoneNum();
                // 查看用户是否绑定手机
                if(phoneNum.equalsIgnoreCase("unknown")){
                    phoneNum = "";
                }
                userInfoBO.setPhoneNum(phoneNum);
            }
        }else {
            phoneNum = "";
        }

        // 获取用户id
        if(userId != null){
            userId = saveUserInfoIntoDB(userInfoBO, userId);
        }
        userInfoBO.setUserId(userId);
        // 登录凭证token
        token = UUID.randomUUID().toString();
        // 将用户登录状态存入redis
        saveUserIntoRedis(userInfoBO, token, type);
        return new Response<>(0,"success", UserReturnDTO.
                builder().
                avatar(userInfoBO.getAvatar()).
                token(token).gender(userInfoBO.getGender().byteValue()).
                nickName(userInfoBO.getNickName()).
                phoneNum(userInfoBO.getPhoneNum()).
                build());
    }

    /**
     * 获取用户，用于判断用户是否注册过
     * @param unionId
     * @return
     */
    private User getUser(String unionId){
        MyAssert.isTrue(unionId != null, "unionId为空");
        UserExample example = new UserExample();
        example.createCriteria().andUnionIdEqualTo(unionId).andDeletedEqualTo(false);
        List<User> users = userMapper.selectByExample(example);
        if(users == null || users.size() < 1){
            return null;
        }
        return users.get(0);
    }

    /**
     * 如果用户没有注册过，就插入数据库，如果注册过就更新数据库
     * @param userInfoBO
     * @param userId
     * @return
     */
    private Long saveUserInfoIntoDB(UserInfoBO userInfoBO, Long userId){
        Date date = new Date();
        User user = new User();
        user.setAvatar(userInfoBO.getAvatar());
        user.setDeleted(false);
        user.setGender(userInfoBO.getGender().byteValue());
        user.setPhoneNum(userInfoBO.getPhoneNum());
        user.setOpenId(userInfoBO.getOpenId());
        user.setUnionId(userInfoBO.getUnionId());
        user.setGmtCreate(date);
        user.setGmtUpdate(date);
        if(null == userId){
            userMapper.insertSelective(user);
            userId = user.getId();
        }else{
            UserExample example = new UserExample();
            example.createCriteria().andDeletedEqualTo(false).andIdEqualTo(userId);
            userMapper.updateByExample(user, example);
        }
        return userId;
    }

    /**
     * 将用户登录状态存入redis
     * @param userInfoBO
     * @param token
     * @param type
     */
    private void saveUserIntoRedis(UserInfoBO userInfoBO, String token, String type){
        RedisUser user = new RedisUser();
        user.setOpenId(userInfoBO.getOpenId());
        user.setUninId(userInfoBO.getUnionId());
        user.setPhoneNum(userInfoBO.getPhoneNum());
        user.setHeadUrl(userInfoBO.getAvatar());
        user.setNickName(userInfoBO.getNickName());
        user.setId(userInfoBO.getUserId());
        user.setToken(token);
        user.setType(type);
        user.setSessionKey(userInfoBO.getSessionKey());
        user.setSex(userInfoBO.getGender());

        redisTemplate.opsForValue().set(token, user, 7L, TimeUnit.DAYS);
    }

    /**
     * 保存车位信息
     * @param parkingInfoDTO
     * @return
     */
    public Response<IsSuccessDTO> saveParkingInfo(ParkingInfoDTO parkingInfoDTO){
        String hash = GeoHashHelperUtil.encode(parkingInfoDTO.getLat(),parkingInfoDTO.getLng());
        Long userId = ContextUser.getUserId();
        UserParkingInfo userParkingInfo = new UserParkingInfo();

        userParkingInfo.setAddress(parkingInfoDTO.getAddress());
        userParkingInfo.setLatitude(parkingInfoDTO.getLat());
        userParkingInfo.setLongitude(parkingInfoDTO.getLng());
        userParkingInfo.setUserId(userId);
        userParkingInfo.setPositionGeoHash(hash);
        userParkingInfo.setGmtUpdate(new Date());
        userParkingInfo.setGmtCreate(new Date());
        userParkingInfo.setDeleted(false);

        userParkingInfoMapper.insert(userParkingInfo);
        return new Response<>(0,"success", IsSuccessDTO.builder().isSuccess(true).build());
    }

    /**
     * 删除用户停车位id
     * @param parkingId
     * @return
     */
    public Response<IsSuccessDTO> deletedParkingInfo(Long parkingId){
        MyAssert.notNull(parkingId);
        Long userId = ContextUser.getUserId();

        userParkingInfoDefineMapper.deletedParkingInfo(parkingId,userId);
        return new Response<IsSuccessDTO>(0, "success", IsSuccessDTO.builder().isSuccess(true).build());

    }

    public Response<UserPlateLianceDTO> getPlate(){
        Long userId = ContextUser.getUserId();
        UserLicenseRelExample example = new UserLicenseRelExample();
        example.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        List<UserLicenseRel> userLicenseRels = userLicenseRelMapper.selectByExample(example);
        UserPlateLianceDTO build = UserPlateLianceDTO.builder().userLicenseRels(userLicenseRels).build();
        return new Response<>(0,"success", build);
    }
}
