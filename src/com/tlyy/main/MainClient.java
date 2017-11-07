package com.tlyy.main;

import com.tlyy.client.BIOTCPClient;
import com.tlyy.client.NIOTCPClient;
import com.tlyy.log.LogCatchExceptionHandler;

public class MainClient {
	 public static void main(String[]args) {
		 Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
//	     BIOTCPClient biotcpClient =new  BIOTCPClient("127.0.0.1",7171);
//	     biotcpClient.start();
		 
		 NIOTCPClient niotcpClient = new NIOTCPClient("127.0.0.1",7171);
		 niotcpClient.start();
	 }	 
}
