package com.netplus.catpark.service.util;

import java.util.Random;

/**
 * @author Brandon.
 * @date 2019/8/19.
 * @time 12:56.
 */

public class CodeProductUtil {
    /**
     * 获取6位随机验证码
     * @return
     */
    public static String getRandomVerifyCode(){
        String[] verifyString = new String[] { "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9" };
        Random random = new Random(System.currentTimeMillis());
        StringBuilder verifyBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rd = random.nextInt(10);
            verifyBuilder.append(verifyString[rd]);
        }
        String verifyCode = verifyBuilder.toString();
        return verifyCode;
    }
}
