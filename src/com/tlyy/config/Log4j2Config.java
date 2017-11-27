package com.tlyy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import com.tlyy.annotation.Bean;

@Bean
public class Log4j2Config {
    private static final String log4j2Path="config/log4j2.xml";    
    public static  void init(){
    	File log4j2File = new File(log4j2Path);
    	ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(log4j2File),log4j2File);
			Configurator.initialize(null, source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
