package com.tlyy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	private static final Logger logger = LoggerFactory.getLogger("com.tlyy.log.LogUtil");

	public static void error(String message) {
		getTrueLog().error(message);
	}

	public static void info(String message) {
		getTrueLog().info(message);
	}

	public static  void debug(String message) {
		getTrueLog().debug(message);
	}
    
    private static Logger getTrueLog() {
    	try {
    		StackTraceElement ste[]=Thread.currentThread().getStackTrace();
			return LoggerFactory.getLogger(Class.forName((String)ste[3].getClassName()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return logger;
    }
}
