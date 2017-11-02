package com.tlyy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.tlyy.log.LogUtil;

public class BIOTCPClient extends TCPClient{
	public BIOTCPClient(String ip, int port) {
		super(ip, port);
		flag=true;
	}

	public  void  start() {
		 try {
			LogUtil.info("client start");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream())); 
			while(flag) {
				String  str = input.readLine();
				out.print(str);
				LogUtil.debug("send to server ",str);
				String echo =buf.readLine();
				LogUtil.debug("receive echo: ",echo);
			}
		} catch (IOException e) {
			LogUtil.error(e);
		}   
	       
	}

}
