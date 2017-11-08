package com.tlyy.main;

import com.tlyy.client.BIOTCPClient;
import com.tlyy.log.LogCatchExceptionHandler;

public class BIOClient {
      public static void main(String[]args){
 		 Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
	     BIOTCPClient biotcpClient =new  BIOTCPClient("127.0.0.1",7171);
	     biotcpClient.start();
      }
}
