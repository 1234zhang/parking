package com.netplus.catpark.service.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 11:14.
 */

@Slf4j
@Component
public class ShortMessageUtil {
    private final String regionId = "";
    private final String accessKeyID = "LTAIA3eTGCTL1d1y";
    private final String accessKeySecret = "553jbR90Dozxowx9kIuXpUCVOHoJsv";

    //阿里云短信固定名称
    private final String product = "Dysmsapi";

    //阿里云短信服务域名
    private final String messageDomain = "dysmsapi.aliyuncs.com";

    //签名
    private final String signName = "易泊";

    //短信模板

    private final String templateCode = "SMS_172603415";

    //初始化ascClient，暂时不支持多region

    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyID, accessKeySecret);
    IAcsClient acsClient = new DefaultAcsClient(profile);

    /**
     * 请求对象
     */

    SendSmsRequest request = new SendSmsRequest();

    /**
     * 响应对象
     */
    SendSmsResponse response = new SendSmsResponse();

    public boolean sendSms(String phoneNum, String authCode){
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sum.net.client.defaultReadTimeout","10000");

        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, messageDomain);
        } catch (ClientException e) {
            log.info(e.getMessage());
        }

        //使用post提交
        request.setMethod(MethodType.POST);
        //设置签名
        request.setSignName(signName);
        //设置模板
        request.setTemplateCode(templateCode);
        //设置要发送的手机号
        request.setPhoneNumbers(phoneNum);
        //设置模板参数
        request.setTemplateParam("{\"code\":\""+authCode+"\"}");
        try {
            response = acsClient.getAcsResponse(request);
            if(response.getCode() != null && response.getCode().equals("OK")) {
                return true;
            }
        } catch (ClientException e) {
            log.info("手机号为{}的短信发送失败，错误码为{}",phoneNum,response.getCode());
        }
        return false;
    }
}
