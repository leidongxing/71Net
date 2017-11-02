package com.tlyy.log;

public class LogCatchExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
		LogUtil.error(paramThrowable);	
	}
}
