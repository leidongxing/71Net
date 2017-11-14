package com.tlyy.round3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

import com.tlyy.log.LogUtil;

public class NIOThread  extends Thread {
	private SelectionKey selectKey;
	private static final Set<SelectionKey> selectKeys = new HashSet<SelectionKey>(); 
	
	@Override
	public void run() {
		while(true){
			while (selectKeys.iterator().hasNext()) {
				SelectionKey key =selectKeys.iterator().next();
				if (key.isValid() && key.isReadable()) {
					 handleRead(key);
				}else if(key.isValid() && key.isWritable()){
					 handleWrite(key);
				}
				selectKeys.iterator().remove();
			}
			LogUtil.debug("add new slectkey ",selectKey);
			selectKeys.add(selectKey);
		}
	}
	
	private void handleRead(SelectionKey key){
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		try {
			long bytesRead = sc.read(buf);
			if (bytesRead == -1){
				sc.close();
			}else if(bytesRead > 0){
			    byte[] receiveClient = new byte[1024];
			    LogUtil.info(buf.array().length);
				buf.get(receiveClient, 0,buf.limit());
				LogUtil.info("client send to server ",new String(receiveClient));	
			}
		} catch (IOException e) {
		    try {
				sc.close();
			} catch (IOException e1) {
				LogUtil.error(e1);
			}
			LogUtil.error(e);
		}
	}
	
	private void handleWrite(SelectionKey key){
		ByteBuffer buf = (ByteBuffer) key.attachment();
		SocketChannel sc = (SocketChannel) key.channel();
        buf.clear();
        buf.put(new String("hello world").getBytes());
		try {
			sc.write(buf);
		} catch (IOException e) {
			LogUtil.error(e);
		}
	}
      
}
