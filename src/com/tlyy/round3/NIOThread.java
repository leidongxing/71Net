package com.tlyy.round3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.tlyy.log.LogUtil;

public class NIOThread  extends Thread {
	private final Set<SelectionKey> ownSelectKeys = new HashSet<SelectionKey>(); 
	private final Set<SelectionKey> runSelectionKeys = new HashSet<SelectionKey>();
	public void add(SelectionKey key){
		ownSelectKeys.add(key);
	}
	@Override
	public void run() {
		while(true){
			Iterator<SelectionKey> keyIter =runSelectionKeys.iterator();
			while (keyIter.hasNext()) {
				LogUtil.info("deal nio selectionKeys: ",runSelectionKeys.size());
				SelectionKey key =keyIter.next();
				if(key.isValid() && key.isWritable()){
					 handleWrite(key);
				}
				if (key.isValid() && key.isReadable()) {
					 handleRead(key);
				}
			}
			runSelectionKeys.clear();
			runSelectionKeys.addAll(ownSelectKeys);	
			ownSelectKeys.clear();
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
				LogUtil.info("client send to server ",new String(buf.array()));	
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
        buf.put(new String("hello world").getBytes());
        key.interestOps(SelectionKey.OP_READ);
		try {
			sc.write(buf);
		} catch (IOException e) {
			LogUtil.error(e);
		}
	}
      
}
