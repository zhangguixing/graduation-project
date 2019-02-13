package com.jefflee.util.shiro;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * 公用的配置文件处理类
 * @author Mr Du
 *
 */

public class GlobalUtil {
    private static Logger LOGGER = Logger.getLogger(GlobalUtil.class);

    /**
     * 文件配置路径
     */
    public static final String PATHCONFIG = "/global.properties";

    /**
     * 获得配置的值
     * 
     * @param key 标示
     * @return 值
     */
    public static String getValue(String key) {
        try {
            Properties properties = new Properties();
            InputStream in = GlobalUtil.class.getResourceAsStream(PATHCONFIG);
            properties.load(in);
            in.close();
            return properties.getProperty(key);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }
}
