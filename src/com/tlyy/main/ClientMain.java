package com.tlyy.main;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import com.tlyy.client.TCPClient;
import com.tlyy.server.IOWay;

public class ClientMain {
     public static void main(String[]args){
       TCPClient tcpClient = new TCPClient("127.0.0.1",7171);
       try {
		SocketChannel clntChan = SocketChannel.open();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
     }
}
