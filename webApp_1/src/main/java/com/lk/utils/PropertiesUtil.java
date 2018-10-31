package com.lk.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
    private static Properties props;
    static {
        String fileName="lvkai.properties";
        props=new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getProperty(String key){
        String value=props.getProperty(key.trim());
        if (value == null){
            return null;
        }
        return value.trim();
    }
    public static String getProperty(String key,String defaultValue){
        String value=props.getProperty(key.trim());
        if (value == null){
            return defaultValue;
        }
        return value.trim();
    }
}
