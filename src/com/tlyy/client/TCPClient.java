package com.tlyy.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	protected boolean flag;
	protected Socket client;

	public String toString() {
		return client.getInetAddress() + " : " + client.getPort() + "  " + this.toString();
	}

	public TCPClient(String ip,int port){
		try {
			client = new Socket(ip,port);
			client.setSoTimeout(0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void start() {
        flag=true;
	}

	public void close() throws IOException {
		flag=false;
		client.close();
	}
}
