package com.tlyy.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.tlyy.client.TCPClient;
import com.tlyy.log.LogUtil;

public class NIOTCPClient extends TCPClient {

	public NIOTCPClient(String ip, int port) {
		super(ip, port);
	}

	public void start() {
		try {
			SocketChannel sc = SocketChannel.open();
			sc.configureBlocking(false);
			if (!sc.connect(new InetSocketAddress(getIp(), getPort()))) {
				while (!sc.finishConnect()) {
					LogUtil.info("wait for connect ",sc);
				}
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			ByteBuffer writeBuf;
			ByteBuffer readBuf;
			String sendStr;
			byte[] sendToServer = new byte[1024];
			int totalSize = 0;
			int currentSize;
			while (true) {
				sendStr = br.readLine();
				LogUtil.debug(sc,"  send to server: ", sendStr);
				sendToServer = sendStr.getBytes();
				writeBuf = ByteBuffer.wrap(sendToServer);
				readBuf = ByteBuffer.allocate(sendToServer.length);
				if (writeBuf.hasRemaining()) {
					sc.write(writeBuf);
					LogUtil.debug("is sending to server");
				}
				LogUtil.info("the message is receiving");
				if((currentSize = sc.read(readBuf))==-1){
				   LogUtil.error("Connection closed prematurely");
				}
				totalSize +=currentSize;
			    if(totalSize>=sendStr.length()){
			    	LogUtil.info("Received: " + new String(readBuf.array(),0,totalSize));
			    	totalSize=0;
			    }			
			}
		} catch (IOException e) {
			LogUtil.error(e);
		}
	}

	public void close() {

	}
}
