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
import java.util.concurrent.locks.ReentrantLock;

import com.tlyy.log.LogUtil;

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
		ReentrantLock lock = new ReentrantLock();
		Iterator<SelectionKey> keyIter = selectionKeys.iterator();
		while (keyIter.hasNext()) {
			try{
				lock.lock();
				SelectionKey key =keyIter.next();
				handleAccept(key);
				keyIter.remove();
			}finally{
				lock.unlock();
			}
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
	
	private void handleAccept(SelectionKey selectKey){
		if (selectKey.isAcceptable()) {
			try {
				SocketChannel sc = ((ServerSocketChannel) selectKey.channel()).accept();
				sc.configureBlocking(false);
				sc.register(selectKey.selector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE,ByteBuffer.allocate(MAX_SIZE));
			} catch (ClosedChannelException e) {
				LogUtil.error(e);
			} catch(IOException e){
				LogUtil.error(e);
			}
		}

	}
	   
}
