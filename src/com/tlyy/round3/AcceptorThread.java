package com.tlyy.round3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import com.tlyy.log.LogUtil;

public class AcceptorThread extends Thread {
	private static final int TIMEOUT = 3000;
	private String ip;
	private int port;
	private Selector selector;
	private ServerSocketChannel listenChannel;
	
	public AcceptorThread(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	private void initServer() {
		try {
			selector = Selector.open();
			listenChannel = ServerSocketChannel.open();
			listenChannel.socket().bind(new InetSocketAddress(ip, port));
			listenChannel.configureBlocking(false);
			listenChannel.register(selector, SelectionKey.OP_ACCEPT);
			LogUtil.info("init acceptor thread success");
		} catch (IOException e) {
			LogUtil.error("init acceptor thread failed");
			LogUtil.error(e);
		}
	}
	
	private void dispatcher(){
		
	}
	
	public void run(){
		initServer();
		while(true){
		  try{	
			if (selector.select(TIMEOUT) == 0) {
				LogUtil.info("wait for client");
				continue;
			}
		    Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			
			
			
			
		  }catch(IOException e){
			  LogUtil.error(e);
		  }
		}
	}
	   
}
