package com.tlyy.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[]args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("server start..");
	    while(true){
	    	Socket socket =serverSocket.accept();
	    	System.out.println("new client accept..");
	    	BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	System.out.println(buf.readLine());
//	    	new BIOThread(socket).start();
	    }
	}
    
}
