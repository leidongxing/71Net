package com.tlyy.round1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOThreadPool {
	 public static final int poolSize=5;
     public static final ExecutorService pool =Executors.newFixedThreadPool(poolSize);
}
