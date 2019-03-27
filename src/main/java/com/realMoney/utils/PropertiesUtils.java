package com.realMoney.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Properties;

public class PropertiesUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    public static final String REDIS_PROPERTIES_FILENAME = "redis";
    private static HashMap<String,String> propertiesFilePathMap = new HashMap<>();
    private static HashMap<String,Properties> propertiesFileNameParams ;
    static {
        propertiesFilePathMap.put(REDIS_PROPERTIES_FILENAME,"/redis.properties");
    }
    private static void init() {
        if(propertiesFileNameParams == null) {
            propertiesFileNameParams = new HashMap<>();

            for(String propertiesFileName:propertiesFilePathMap.keySet()) {

                String propertiesFilePath = propertiesFilePathMap.get(propertiesFileName);

                Properties tmpParam = loadPropertiesFileConfig(propertiesFilePath);

                propertiesFileNameParams.put(propertiesFileName,tmpParam);
            }

        }
    }
    private static Properties loadPropertiesFileConfig(String filePath) {
        Properties tmpParam = new Properties();
        try {
            tmpParam.load(PropertiesUtils.class.getResourceAsStream(filePath));
        }catch (Exception e){
            logger.error("loadPropertiesFileConfig<{}> error", filePath, e);
        }
        return tmpParam;
    }
    private static Properties getPropertiesByFileName(String propertiesFileName) {
        if (propertiesFileNameParams == null || propertiesFileNameParams.isEmpty()) {
            init();
        }
        if (propertiesFileNameParams != null || !propertiesFileNameParams.isEmpty()) {
            return propertiesFileNameParams.get(propertiesFileName);
        }
        return null;
    }
    public static Properties getRedisPropertiesFile() {
        return getPropertiesByFileName(REDIS_PROPERTIES_FILENAME);
    }
}
