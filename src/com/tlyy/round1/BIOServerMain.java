package com.tlyy.round1;

import java.net.ServerSocket;
import java.net.Socket;

import com.tlyy.log.LogCatchExceptionHandler;
import com.tlyy.log.LogUtil;

public class BIOServerMain {
	public static void main(String[]args) throws Exception {
		   Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
		   ServerSocket serverSocket = new ServerSocket(7171);
	    while(true){
	    	Socket socket =serverSocket.accept();
	    	LogUtil.debug("new client accept..",socket.getInetAddress(),":",socket.getPort());
//	    	new BIOServerThread(socket).start();
	    	BIOThreadPool.pool.execute(new BIOServerThread(socket));
	    }
	}
    
}
