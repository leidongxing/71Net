package com.tlyy.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.tlyy.log.LogUtil;

public class NIOTCPClient extends TCPClient {

	public NIOTCPClient(String ip, int port) {
		super(ip, port);
		// TODO Auto-generated constructor stub
	}

	public void start(){
		try{
		SocketChannel clntChan = SocketChannel.open();  
        clntChan.configureBlocking(false);  
        //�����˷�������  
        if (!clntChan.connect(new InetSocketAddress("127.0.0.1", 7171))){  
            //���ϵ���ѯ����״̬��ֱ���������  
            while (!clntChan.finishConnect()){  
                //�ڵȴ����ӵ�ʱ�������ִ�����������Գ�ַ��ӷ�����IO���첽����  
                //����Ϊ����ʾ�÷�����ʹ�ã�ֻ��һֱ��ӡ"."  
                System.out.print(".");    
            }  
        }  
        //Ϊ��������ӡ��"."������������������з�  
        System.out.print("\n");  
        byte[] argument = new String("hello world").getBytes();
        //�ֱ�ʵ����������д�Ļ�����  
        ByteBuffer writeBuf = ByteBuffer.wrap(argument);  
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);  
        //���յ����ܵ��ֽ���  
        int totalBytesRcvd = 0;   
        //ÿһ�ε���read�����������յ����ֽ���  
        int bytesRcvd;   
        //ѭ��ִ�У�ֱ�����յ����ֽ����뷢�͵��ַ������ֽ������  
        while (totalBytesRcvd < argument.length){  
            //���������ͨ����д���ݵĻ������л���ʣ����ֽڣ������������д���ŵ�  
            if (writeBuf.hasRemaining()){  
                clntChan.write(writeBuf);  
            }  
            //���read�������յ�-1����������˹رգ��׳��쳣  
            if ((bytesRcvd = clntChan.read(readBuf)) == -1){  
                LogUtil.error("Connection closed prematurely");  
            }  
            //������յ������ֽ���  
            totalBytesRcvd += bytesRcvd;  
            //�ڵȴ�ͨ����ɵĹ����У��������ִ���������������ַ�����IO���첽����  
            //����Ϊ����ʾ�÷�����ʹ�ã�ͬ��ֻ��һֱ��ӡ"."  
            System.out.print(".");   
        }  
        //��ӡ�����յ�������  
        System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));  
        //�ر��ŵ�  
        clntChan.close();
		}
		catch (Exception e){
			LogUtil.error(e);
		}
	}
}
