package com.tlyy.round3;

import java.util.HashMap;
import java.util.Map;

public class NIOThreadManager {
	  public static final int NIOThreadSize=5;
      private static Map<Integer,Thread> id2ThreadMap = new HashMap<Integer,Thread>();
      public static void init(){
    	  for(int i=0;i<NIOThreadSize;i++){
    		  Thread t=new NIOThread();
    		  t.start();
    		  id2ThreadMap.put(i,t);
    	  }
      }
      public static Thread getThreadById(int id){
    	  return id2ThreadMap.get(id);
      }
}
