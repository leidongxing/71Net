package com.tlyy.main;

import java.util.HashSet;
import java.util.Set;

import com.tlyy.client.BIOTCPClient;
import com.tlyy.client.TCPClient;

public class ClientThread implements Runnable  {
    private Set<TCPClient> tcpContainer = new HashSet<TCPClient>();
    
    @Override
	public void run() {
		
		
	}
    
    
    
    
    public static void main(String[]args) {
    	BIOTCPClient biotcpClient =new  BIOTCPClient("127.0.0.1",8080);
    	biotcpClient.start();
    }
     
}
