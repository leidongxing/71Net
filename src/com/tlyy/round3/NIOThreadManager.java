package com.tlyy.round3;

import java.util.HashMap;
import java.util.Map;

public class NIOThreadManager {
	  public static final int NIOThreadSize=5;
      private static Map<Integer,NIOThread> id2ThreadMap = new HashMap<Integer,NIOThread>();
      public static void init(){
    	  for(int i=0;i<NIOThreadSize;i++){
    		  NIOThread t=new NIOThread();
    		  t.setName("NIOThread-"+i);
    		  t.start();
    		  id2ThreadMap.put(i,t);
    	  }
      }
      public static NIOThread getThreadById(int id){
    	  return id2ThreadMap.get(id);
      }
}
