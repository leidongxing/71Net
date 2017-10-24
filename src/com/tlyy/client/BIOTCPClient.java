package com.tlyy.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BIOTCPClient extends TCPClient{
	public BIOTCPClient(String ip, int port) {
		super(ip, port);
		flag=true;
		// TODO Auto-generated constructor stub
	}

	public  void  start() {
		 try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream())); 
			while(flag) {
				String  str = input.readLine();
				out.print(str);
				String echo =buf.readLine();
				System.out.println("echo: "+echo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	       
	}

}