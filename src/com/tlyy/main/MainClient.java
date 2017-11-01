package com.tlyy.main;

import com.tlyy.client.BIOTCPClient;
import com.tlyy.log.LogUtil;

public class MainClient {
	 public static void main(String[]args) {
	     BIOTCPClient biotcpClient =new  BIOTCPClient("127.0.0.1",7171);
	     biotcpClient.start();
		 LogUtil.error("dassssss");
	 }	 
}
