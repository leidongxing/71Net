package com.tlyy.client;

public class TCPClient {
	private String ip;
	private int port;
	private Enum<IOWay> ioWay;
	private String doSomething;

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
  
	public Enum<IOWay> getIoWay() {
		return ioWay;
	}

	public TCPClient(String ip, int port, Enum<IOWay> ioWay) {
		this.ip = ip;
		this.port = port;
		this.ioWay = ioWay;
	}

}
