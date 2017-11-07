package com.tlyy.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import com.tlyy.log.LogUtil;

public class NIOServerThread extends Thread{
     private static final int BUFSIZE =256;
     private static final int TIMEOUT =3000;
     public static void main(String[]args){
    	 new NIOServerThread().start();
     }
     
     public void run(){
    	 try {
			Selector selector = Selector.open();
			ServerSocketChannel listChannel =ServerSocketChannel.open();
			listChannel.socket().bind(new InetSocketAddress("127.0.0.1",7171));
			listChannel.configureBlocking(false);
			listChannel.register(selector, SelectionKey.OP_ACCEPT);
			TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE); 
			while(true){
				if(selector.select(TIMEOUT)==0){
				   LogUtil.info("wait for client");
				   continue;
				}
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while(keyIter.hasNext()){
				SelectionKey key = keyIter.next();
				if(key.isAcceptable()){
				    protocol.handleAccept(key);
				}
				
				if(key.isReadable()){
					protocol.handleRead(key);
				}
				
				if(key.isValid() && key.isWritable()){
					protocol.handleWrite(key);
				}
				keyIter.remove();
			}
			
			}
	
		} catch (IOException e) {
			LogUtil.error(e);
		}
     }
}
