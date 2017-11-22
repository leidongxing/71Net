package com.tlyy.round3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.tlyy.log.LogUtil;
import com.tlyy.annotation.ThreadSafe;
@ThreadSafe
public class AcceptorThread extends Thread {
	private static final int TIMEOUT = 3000;
    private int MAX_SIZE = 1024;
	private String ip;
	private int port;
	private Selector selector;
	private ServerSocketChannel listenChannel;
	
	public AcceptorThread(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.setName("AcceptorThread");
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
	
	private void dispatcher(Set<SelectionKey> selectionKeys){
		Iterator<SelectionKey> keyIter = selectionKeys.iterator();
		while (keyIter.hasNext()) {
			SelectionKey key =keyIter.next();
			if (key.isAcceptable()) {
				try {
					SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
					sc.configureBlocking(false);
					sc.register(key.selector(), SelectionKey.OP_WRITE,ByteBuffer.allocate(MAX_SIZE));
					LogUtil.info("accept a SelectionKey");
				} catch (ClosedChannelException e) {
					LogUtil.error(e);
				} catch(IOException e){
					LogUtil.error(e);
				} finally{
					keyIter.remove();	
				}
			}	
		}
		if(!selectionKeys.isEmpty()){
			int i=0;
			for(SelectionKey key:selectionKeys){
			     NIOThreadManager.getThreadById(i).add(key);
			     i++;
			     if(i>5){
			    	i=0; 
			     }	   
			}
			selectionKeys.clear();
		}
	}
	
	public void run(){
		initServer();
		while(true){
		  try{	
			if (selector.select(TIMEOUT) == 0) {
				LogUtil.info("wait for client");
				continue;
			}
			dispatcher(selector.selectedKeys());	
		  }catch(IOException e){
			  LogUtil.error(e);
		  }
		}
	}  
}
