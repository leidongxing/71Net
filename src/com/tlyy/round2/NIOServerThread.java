package com.tlyy.round2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.tlyy.log.LogUtil;

public class NIOServerThread extends Thread {
	private static final int BUFSIZE = 256;
	private static final int TIMEOUT = 3000;
	private String ip;
	private int port;
	private Selector selector;
	private ServerSocketChannel listenChannel;
    private byte[] receiveClient = new byte[BUFSIZE];

	public NIOServerThread(String ip, int port) {
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
			LogUtil.info("init server success");
		} catch (IOException e) {
			LogUtil.error("init server failed");
			LogUtil.error(e);
		}
	}
	
	public void run() {
		initServer();
		while (true) {
			try {
				if (selector.select(TIMEOUT) == 0) {
					LogUtil.info("wait for client");
					continue;
				}
				Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
				while (keyIter.hasNext()) {
					LogUtil.info("accept a persion");
					SelectionKey key = keyIter.next();
					if (key.isAcceptable()) {
						SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
						sc.configureBlocking(false);
						sc.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(BUFSIZE));
					}

					if (key.isReadable()) {
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
						}}
						catch(IOException e){
							LogUtil.error(e);
							try {
								sc.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}

					if (key.isValid() && key.isWritable()) {
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
					keyIter.remove();
				}
			} catch (IOException e) {
				LogUtil.error(e);
		    }
		}	
	}
}