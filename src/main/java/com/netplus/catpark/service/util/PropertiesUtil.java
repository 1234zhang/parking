package com.netplus.catpark.service.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:38.
 */


public class PropertiesUtil {
    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    public static String getProperty(String fileName, String attribute){
        Properties pro = null;
        try {
            pro = properties(fileName);
            return pro.getProperty(attribute);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static Properties properties(String fileName) throws IOException {
        InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        if(input == null){
            throw new RuntimeException("文件不存在");
        }
        Properties pron = new Properties();
        pron.load(input);
        return pron;
    }
}
