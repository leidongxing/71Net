package com.tlyy.client;

public abstract class TCPClient {
	protected String ip;
	protected int port;

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
  
	public TCPClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public abstract void start() throws Exception;
	
	public String toString() {
		return ip+" : "+port+"  "+this.toString();		
	}

}
