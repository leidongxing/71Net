package com.tlyy.client;

public class TCPClient {
	private String ip;
	private int port;

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

}
