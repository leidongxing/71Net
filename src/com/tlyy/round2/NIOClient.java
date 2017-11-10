package com.tlyy.round2;

import com.tlyy.log.LogCatchExceptionHandler;

public class NIOClient {
	 public static void main(String[]args) {
		 Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
		 NIOTCPClient niotcpClient = new NIOTCPClient("127.0.0.1",7171);
		 niotcpClient.start();
	 }	 
}
