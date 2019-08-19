package com.netplus.catpark.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 13:09.
 */

public class ParamCheckUtil {
    /**
     * 检验手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
