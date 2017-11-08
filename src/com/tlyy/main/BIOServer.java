package com.tlyy.main;

import java.net.ServerSocket;
import java.net.Socket;

import com.tlyy.log.LogCatchExceptionHandler;
import com.tlyy.log.LogUtil;
import com.tlyy.server.BIOServerThread;

public class BIOServer {
	public static void main(String[]args) throws Exception {
		   Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
		   ServerSocket serverSocket = new ServerSocket(7171);
	    while(true){
	    	Socket socket =serverSocket.accept();
	    	LogUtil.debug("new client accept..",socket.getInetAddress(),":",socket.getPort());
	    	new BIOServerThread(socket).start();
	    }
	}
    
}
