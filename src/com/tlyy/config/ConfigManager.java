package com.tlyy.config;

import org.apache.logging.log4j.core.config.Configurator;

public class ConfigManager {
	private final ConfigManager configManager = new ConfigManager();
	private final String configPath="。。/config";
    private final ServerConfig serverConfig =null;
	private final Configurator log4jConfig=null;
    
	public ConfigManager getInstance(){
		return configManager;
	}
    
    public void init(){
    	
    }
    
    
    
	
	
}
