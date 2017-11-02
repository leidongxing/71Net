package com.tlyy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.tlyy.log.LogUtil;

public class BIOThread extends Thread {
	 private Socket socket;
     public BIOThread(Socket socket) {
		this.socket=socket;
	 }
    
	 public void run() {
		 try {
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			while(true) {
				String str=buf.readLine();
				LogUtil.debug(str);
				out.print("server accept "+str);
			}
		} catch (IOException e) {
			LogUtil.error(e);
		}
	 }
}
