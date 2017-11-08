package com.tlyy.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.tlyy.log.LogUtil;

public class NIOServerThread extends Thread{
     private static final int BUFSIZE =256;
     private static final int TIMEOUT =3000;
     private String ip;
     private int port;
     public NIOServerThread (String ip,int port){
           this.ip =ip;
           this.port =port;
     }
   
     public void run(){
    	 try {
    		LogUtil.info("server start");
			Selector selector = Selector.open();
			ServerSocketChannel listChannel =ServerSocketChannel.open();
			listChannel.socket().bind(new InetSocketAddress(ip,port));
			listChannel.configureBlocking(false);
			listChannel.register(selector, SelectionKey.OP_ACCEPT);
			while(true){
				if(selector.select(TIMEOUT)==0){
				   LogUtil.info("wait for client");
				   continue;
				}
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while(keyIter.hasNext()){
				SelectionKey key = keyIter.next();
				if(key.isAcceptable()){
				    SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
			        sc.configureBlocking(false);
			        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUFSIZE));
				}
				
				if(key.isReadable()){
					SocketChannel sc = (SocketChannel) key.channel();
			        ByteBuffer buf = (ByteBuffer) key.attachment();
			        long bytesRead = sc.read(buf);
			        if (bytesRead == -1) {
			            sc.close();
			        } else if (bytesRead > 0) {
			            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			        }
				}
				
				if(key.isValid() && key.isWritable()){
				    ByteBuffer buf = (ByteBuffer) key.attachment();
			        buf.flip();
			        SocketChannel sc = (SocketChannel) key.channel();
			        sc.write(buf);
			        if (!buf.hasRemaining()) {
			            key.interestOps(SelectionKey.OP_READ);
			        }
			        buf.compact();
				}
				keyIter.remove();
			}
			
			}
	
		} catch (IOException e) {
			LogUtil.error(e);
		}
     }
}
