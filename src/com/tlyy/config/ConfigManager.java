package com.tlyy.config;

import java.io.File;


public class ConfigManager {
	private final ConfigManager configManager = new ConfigManager();
	private final String configPath="config/config.xml";
    private final ServerConfig serverConfig =null;
    
	public ConfigManager getInstance(){
		return configManager;
	}
    
    public void init(){
    	Log4j2Config.init();
    	File file = new File(configPath);
//    	XMLBeanFactory.init(file);
        
    }
    
    
    
	
	
}