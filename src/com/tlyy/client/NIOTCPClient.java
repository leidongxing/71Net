package com.tlyy.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.tlyy.log.LogUtil;

public class NIOTCPClient extends TCPClient {

    public NIOTCPClient(String ip, int port) {
        super(ip, port);
    }

    public void start() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            if (!sc.connect(new InetSocketAddress(getIp(),getPort()))) {
                while (!sc.finishConnect()) {
                    LogUtil.info("wait for connect");
                }
            }
            byte[] argument = new String("hello world").getBytes();
            ByteBuffer writeBuf = ByteBuffer.wrap(argument);
            ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
            int totalBytes = 0;
            int bytesRcvd;
            while (totalBytes < argument.length) {
                if (writeBuf.hasRemaining()) {
                    sc.write(writeBuf);
                }
                if ((bytesRcvd = sc.read(readBuf)) == -1) {
                    LogUtil.error("Connection closed prematurely");
                }
                totalBytes += bytesRcvd;
                LogUtil.info("the message is receiving");
            }
            LogUtil.info("Received: " + new String(readBuf.array(), 0, totalBytes));
            sc.close();
        } catch (IOException e) {
            LogUtil.error(e);
        }
    }

    public void close() {

    }
}
