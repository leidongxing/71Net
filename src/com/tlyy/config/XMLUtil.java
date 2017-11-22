package com.tlyy.config;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.tlyy.log.LogUtil;

public class XMLUtil {
	public void readXMLByNIO(String filePath,String fileName) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(filePath+fileName,"rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(15);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				LogUtil.debug("Read ",bytesRead);
				buf.flip();
				while (buf.hasRemaining()) {
					LogUtil.debug((char) buf.get());
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();

		} catch (FileNotFoundException e) {
			LogUtil.error(e);
		} catch (Exception e) {
			LogUtil.error(e);
		}

	}

	public static void main(String[] args) {
//		new XMLUtil().readXMLByNIO();
		int i =1;
		int j =16;
		int k= i|j;
	}

}
