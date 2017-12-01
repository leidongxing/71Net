package com.tlyy.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigContext {

	private final static Map<String,Object> configInfo=new HashMap<String,Object>();
	private final static Map<String,Object> oldConfigInfo=new HashMap<String,Object>();
	private final static Map<String,Long> loadTime=new HashMap<String,Long>();
	private static Status currentStatus = Status.Uninitialized;
    
    private ConfigContext(){
    	
    }

    public static void add(String className,Object o){
    	configInfo.put(className, o);
    }
    
    public static void load(String fileName){
    	loadTime.put(fileName, System.currentTimeMillis());
    }
    
    
    public static  Map<String,Object> getConfigInfo(){
        return configInfo;
    }

    public static Object getBean(String beanId){
        return configInfo.get(beanId);
    }
    
    enum Status {
    	Uninitialized,Normal,HotUpdate
    }
}
