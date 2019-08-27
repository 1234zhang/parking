package com.netplus.catpark.service.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.netplus.catpark.domain.bo.UserInfoBO;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 20:05.
 */

public class DecodeUtil {
    private static Logger logger = LoggerFactory.getLogger(DecodeUtil.class);
    public static void decipherByUserInfo(String encryptedData, String vi, UserInfoBO userInfo) {
        if(encryptedData == null || vi == null){
            return ;
        }
        String sessionKey = userInfo.getSessionKey();
        byte[] encryptedByte = Base64.decode(encryptedData);
        byte[] viByte = Base64.decode(vi);
        byte[] sessionKeyByte = Base64.decode(sessionKey);

        AesUtil aes = new AesUtil();
        try {
            byte[] result = aes.decrypt(encryptedByte, sessionKeyByte, viByte);
            if (null != result && result.length > 0) {
                String jsonResult = new String(result, "UTF-8");
                JSONObject jsonObject = JSONObject.parseObject(jsonResult);
                userInfo.setAvatar(jsonObject.getString("avatarUrl"));
                userInfo.setGender(jsonObject.getInteger("gender"));
                userInfo.setNickName(jsonObject.getString("nickname"));
                userInfo.setUnionId(jsonObject.getString("unionid"));
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return ;
    }
}
