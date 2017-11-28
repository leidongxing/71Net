package com.tlyy.round3;

import com.tlyy.config.ConfigContext;
import com.tlyy.config.ServerConfig;

public class MainServer {
	
      public static void main(String [] args) throws InterruptedException{
          ServerConfig  serverConfig=(ServerConfig)ConfigContext.getBean("ServerConfig");
    	  new AcceptorThread(serverConfig.getIp(),serverConfig.getPort()).start();
    	  NIOThreadManager.init();
      }
}
