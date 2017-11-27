package com.tlyy.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigContext {
	
	private final static ConfigContext instanace = new ConfigContext();
	
	private Map<String,Object> configInfo=new HashMap<String,Object>();
    
    private ConfigContext(){
    	
    }
    
    public static ConfigContext Instance(){
		return instanace;
    }
    
    public void add(String className,Object o){
    	configInfo.put(className, o);
    }
    
    public Map<String,Object> getConfigInfo(){
        return configInfo;	
    }
    
    
}
