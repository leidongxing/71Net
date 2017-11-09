package com.tlyy.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
    private static Logger defaultLog = LoggerFactory.getLogger("com.tlyy.log.LogUtil");
    
	public static void debug(String message) {
		if(defaultLog.isDebugEnabled()){
			defaultLog.debug(message);
		}		
	}
	
	public static void debug(Object ...messages){
		StringBuilder sb = new StringBuilder();
		for(Object o:messages){
			sb.append(o.toString());
		}
		defaultLog.debug(sb.toString());	
	}
	
	public static void info(String message) {
		if(defaultLog.isInfoEnabled()){
			defaultLog.info(message);
		}	
	}
	
	public static void info(Object ...messages){
		StringBuilder sb = new StringBuilder();
		for(Object o:messages){
			sb.append(o.toString());
		}
		defaultLog.info(sb.toString());
	}
	
	public static void warn(Object message){
		if(defaultLog.isWarnEnabled()){
			defaultLog.warn(message.toString());
		}
	}
	
	public static void error(String message) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		for(int i=ste.length-1;i>0;i--){
			if(ste[i].getClassName().equals(defaultLog.getName())){
				int current=i+1;
				StringBuilder sb = new StringBuilder();
				sb.append("[MethodName:").append(ste[current].getClassName()).append("]-");
				sb.append("[LineNumber:").append(ste[current].getLineNumber()).append("]\r\n");
				sb.append(message);
				defaultLog.error(sb.toString());
				break;
			}
		}	
	}
	
	public static void error(Throwable t) {
		if (t instanceof Exception) {
			Exception e = (Exception) t;
			StackTraceElement[] ste= e.getStackTrace();
			StringBuilder sb = new StringBuilder();
			sb.append(new StringBuilder().append(e.getMessage()));
			for(int i=0;i<ste.length;i++){	
				 sb.append("\r\n\t at:")
				.append(ste[i].getClassName()).append(".")
				.append(ste[i].getMethodName()).append("  (LineNumber:")
				.append(ste[i].getLineNumber()).append(")");
			}
			defaultLog.error(sb.toString());
		}
	}
}
