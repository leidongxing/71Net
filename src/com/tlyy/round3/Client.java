package com.tlyy.round3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.tlyy.log.LogUtil;

public class Client {
    private String ip;
    private int port;
    private String id;
    private SocketChannel sc;
    private BufferedReader keyBoardInput;
    private int MAX_SIZE = 1024;
    private byte[] c2sBytes;
    private String c2sStr;
	private ByteBuffer writeBuf;
	private ByteBuffer readBuf;
	public Client(String ip,int port){
		this.ip=ip;
		this.port=port;
	}
	private void init(){
		try {
		    sc = SocketChannel.open();
			sc.configureBlocking(false);    
			keyBoardInput = new BufferedReader(new InputStreamReader(System.in));
			c2sBytes = new byte[MAX_SIZE];
			readBuf = ByteBuffer.allocate(MAX_SIZE);
			LogUtil.info("init client success");
		} catch (IOException e) {
			LogUtil.error("init client failed");
			LogUtil.error(e);
		}
	}
    
	private void connect(){
		try {
			if (!sc.connect(new InetSocketAddress(ip, port))) {
				while (!sc.finishConnect()) {
					LogUtil.info("wait for connect ");
				}
			}
			id=sc.toString();
			LogUtil.info("connect the server",id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
    public void run(){
    	init();
    	connect();
    	while (true) {
    		try{
    			c2sStr = keyBoardInput.readLine();
    			c2sBytes = c2sStr.getBytes();
    			writeBuf = ByteBuffer.allocate(c2sBytes.length);
    		    writeBuf.put(c2sBytes);
    		    LogUtil.debug("send to server: ",c2sStr,"buf size",writeBuf.capacity());
    		    writeBuf.clear();
    		    if(sc.read(readBuf)!=-1){
    		    	LogUtil.debug("received the server: ",new String(readBuf.array()));
    		    	readBuf.clear();
    		    }	
    		}catch(IOException e){
    			LogUtil.error(e);
    		}
		}
    	
    }
	public static void main(String[]args){
		new Client("127.0.0.1",7171).run();
	}
}
