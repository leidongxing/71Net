package com.tlyy.round2;

import com.tlyy.log.LogCatchExceptionHandler;

public class NIOServer {
    public static void main(String[]args){
 	   Thread.setDefaultUncaughtExceptionHandler(new LogCatchExceptionHandler());
 	   new NIOServerThread("127.0.0.1",7171).start();
    }
}
