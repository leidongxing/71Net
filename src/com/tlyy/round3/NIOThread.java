package com.tlyy.round3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.tlyy.log.LogUtil;

public class NIOThread  implements Runnable {
	public NIOThread(SelectionKey selectKey){
		this.selectKey=selectKey;
	}
	private SelectionKey selectKey;
	private static final Set<SelectionKey> selectKeys = new HashSet<SelectionKey>(); 
	
	@Override
	public void run() {
		selectKeys.add(selectKey);
		LogUtil.debug("add new slectkey ",selectKey);
		
		while (selectKeys.iterator().hasNext()) {
			SelectionKey key =selectKeys.iterator().next();
			if (key.isValid() && key.isReadable()) {
				 
			}else if(key.isValid() && key.isWritable()){
				
			}
				
				
			}

			
			keyIter.remove();
		}
		
	}
	
	
	
	private void handleRead(SelectionKey key){
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buf = (ByteBuffer) key.attachment();
		try{
		long bytesRead = sc.read(buf);
		if (bytesRead == -1) {
			sc.close();
		} else if (bytesRead > 0) {
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			if(!buf.hasRemaining()) {
				LogUtil.warn("the buffer is fill");
			}
			buf.flip();
			buf.get(receiveClient, 0,buf.limit());
			LogUtil.info("client send to server ",new String(receiveClient));
		    buf.clear();
		}
	}
	
	private void handleWrite(){
		ByteBuffer buf = (ByteBuffer) key.attachment();
		buf.flip();
		SocketChannel sc = (SocketChannel) key.channel();
		sc.write(buf);
		if (!buf.hasRemaining()) {
			key.interestOps(SelectionKey.OP_READ);
		} else {
		
		}
		buf.compact();
	}
      
}
