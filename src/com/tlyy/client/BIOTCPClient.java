package com.tlyy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.SocketTimeoutException;

import com.tlyy.log.LogUtil;

public class BIOTCPClient extends TCPClient {
	public BIOTCPClient(String ip, int port) {
		super(ip, port);
	}

	public void start() {
		LogUtil.info("client start");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while (true) {
				String sendStr = input.readLine();
				out.println(sendStr);
				LogUtil.debug("send to server: ",sendStr);				
				String receiveStr = buf.readLine();
				LogUtil.debug("receive the server: ",receiveStr);
			}
		} catch(SocketTimeoutException e){  
            LogUtil.error("socket time out");  
        } catch (IOException e) {
			LogUtil.error(e);
		}  		
	}
}
