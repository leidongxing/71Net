package com.tlyy.client;


public abstract  class TCPClient {
	private  String ip;
	private  int port;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String toString() {
		return "ip: " +ip +"port: " + port +  this.toString();
	}

	public TCPClient(String ip,int port){
		this.ip=ip;
		this.port=port;
	}

	public abstract void start();

	public abstract void close();



}
