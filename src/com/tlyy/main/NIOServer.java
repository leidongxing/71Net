package com.tlyy.main;

import com.tlyy.log.LogCatchExceptionHandler;
import com.tlyy.server.NIOServerThread;

public class NIOServer {
    public static void main(String[]args){
 	   Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
 	   new NIOServerThread("127.0.0.1",7171).start();
    }
}
