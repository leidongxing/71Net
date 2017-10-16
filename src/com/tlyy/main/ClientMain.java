package com.tlyy.main;

import com.tlyy.client.IOWay;
import com.tlyy.client.TCPClient;

public class ClientMain {
     public static void main(String[]args){
    	 TCPClient tcpClient = new TCPClient("127.0.0.1",7171,IOWay.BIO);
//       SocketChannel clntChan = SocketChannel.open();  

     }
}
