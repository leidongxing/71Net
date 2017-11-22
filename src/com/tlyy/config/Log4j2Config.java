package com.tlyy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class Log4j2Config {
    private final String log4j2Path="config/log4j2.xml";
    
    public Log4j2Config(){
    	
    	
    }
    
    public void init(){
    	File log4j2File = new File(log4j2Path);
    	ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(log4j2File),log4j2File);
			Configurator.initialize(null, source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[]args) throws FileNotFoundException{
    	new Log4j2Config().init();
    }
}
