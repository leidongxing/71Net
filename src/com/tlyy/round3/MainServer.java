package com.tlyy.round3;

public class MainServer {
	
      public static void main(String [] args) throws InterruptedException{  
    	  new AcceptorThread("127.0.0.1",7171).start();
    	  NIOThreadManager.init();
      }
}
