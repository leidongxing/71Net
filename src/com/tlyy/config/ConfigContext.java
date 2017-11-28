package com.tlyy.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigContext {

	private final static Map<String,Object> configInfo=new HashMap<String,Object>();
    
    private ConfigContext(){
    	
    }

    public static void add(String className,Object o){
    	configInfo.put(className, o);
    }
    
    public static  Map<String,Object> getConfigInfo(){
        return configInfo;
    }

    public static Object getBean(String beanId){
        return configInfo.get(beanId);
    }
    
}
