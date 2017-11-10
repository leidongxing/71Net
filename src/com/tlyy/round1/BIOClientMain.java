package com.tlyy.round1;

import com.tlyy.log.LogCatchExceptionHandler;

public class BIOClientMain {
      public static void main(String[]args){
 		 Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
	     BIOTCPClient biotcpClient =new  BIOTCPClient("127.0.0.1",7171);
	     biotcpClient.start();
      }
}
