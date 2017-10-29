package com.tlyy.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tlyy.server.BIOThread;

public class MainServer {
	public static void main(String[]args) throws Exception {
		   ServerSocket serverSocket = new ServerSocket(8080);
		   Logger logger = LogManager.getLogger("71Net"); 
		   logger.debug("debug level");  
	    while(true){
	    	Socket socket =serverSocket.accept();
	    	System.out.println("new client accept..");
	    	BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	System.out.println(buf.readLine());
	    	new BIOThread(socket).start();
	    }
	}
    
}
