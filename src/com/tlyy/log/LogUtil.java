package com.tlyy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
    private static Logger defaultLog = LoggerFactory.getLogger("com.tlyy.log.LogUtil");
    
	public static void error(String message) {
		getTrueLog().error(message);
	}

	public static void info(String message) {
		getTrueLog().info(message);
	}

	public static void debug(String message) {
		getTrueLog().debug(message);
		
	}

	public static void error(Throwable t) {
		if (t instanceof Exception) {
			Exception e = (Exception) t;
			StackTraceElement[] ste= e.getStackTrace();
			StringBuilder sb = new StringBuilder();
			sb.append(new StringBuilder().append(e.getMessage()));
			sb.append("\r\n");
			for(int i=0;i<ste.length;i++){				
				 sb.append("\tat:")
				.append(ste[i].getClassName()).append(".")
				.append(ste[i].getMethodName()).append("  (LineNumber:")
				.append(ste[i].getLineNumber()).append(") \r\n");
			}
			error(sb.toString());
		}
	}

	private static Logger getTrueLog() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		for(int i=ste.length-1;i>0;i--){
			if(ste[i].getClassName().equals(defaultLog.getName())){
				return LoggerFactory.getLogger(ste[i+1].getClassName());
			}
		}
		
		return defaultLog;
	}
}
