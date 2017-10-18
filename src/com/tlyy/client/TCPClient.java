package com.tlyy.client;

import java.net.Socket;

public class TCPClient {
	private Socket socket;

	public String toString() {
		return socket.getInetAddress() + " : " + socket.getPort() + "  " + this.toString();
	}

	public TCPClient() {
		socket = new Socket();
	}

	public void start() {

	}

	public void close() {

	}
}
