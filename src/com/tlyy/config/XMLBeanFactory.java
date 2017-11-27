package com.tlyy.config;

import com.tlyy.log.LogUtil;

public class XMLBeanFactory{
	public final XMLBeanFactory xbf = new XMLBeanFactory(); 
	public XMLBeanFactory getInstance(){
		return xbf;
	}

	public Object createBean(String className) {
		try {
			Object o =Class.forName(className);			
			return o;
		} catch (ClassNotFoundException e) {
			LogUtil.error(e);
			return null;
		}
	}
	

   
}
