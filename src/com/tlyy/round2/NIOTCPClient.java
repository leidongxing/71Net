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
			byte[] sendToServer = new byte[]{};
			while (true) {
				sendStr = br.readLine();
				sendToServer = sendStr.getBytes();
				writeBuf = ByteBuffer.wrap(sendToServer);
				sc.write(writeBuf);
				LogUtil.debug(sc,"send to server: ", sendStr);
				
				readBuf = ByteBuffer.allocate(sendToServer.length);
				while(readBuf.hasRemaining()){
					sc.read(readBuf);
				}
				LogUtil.info("Received: " + new String(readBuf.array()));
			}
		} catch (IOException e) {
			LogUtil.error(e);
		}
	}

	public void close() {

	}
}
