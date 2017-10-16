package com.tlyy.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class BIOTCPClient extends TCPClient{

	public BIOTCPClient(String ip, int port) {
		super(ip, port);
	}

	@Override
	public void start() throws Exception {
		Socket socket = new Socket(ip,port);
		socket.setSoTimeout(0);
		
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());
				
				
		socket.close();	
	}
	

}
