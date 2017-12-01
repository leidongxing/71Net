package com.tlyy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.tlyy.log.LogUtil;
import com.tlyy.util.ReflectUtil;
import com.tlyy.util.StringUtil;
import com.tlyy.util.XMLUtil;

public class ConfigThread extends Thread{
	
	 private final String configPath="config/config.xml";
	 private static final String log4j2Path="config/log4j2.xml";
	 
	 public ConfigThread(){
		 this.setName("ConfigThread");
	 }
	
     @Override
     public void run(){
        init();
    	hotUpdate();
    	
     }
     
     
     
     private void init(){
    	log4jLoad();
    	configLoad();
     }
     private void log4jLoad(){
    	try{
     		File log4j2File = new File(log4j2Path);
          	ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4j2File),log4j2File);
      		Configurator.initialize(null, source);
     	}catch(FileNotFoundException e){
     		LogUtil.error(e);
     		LogUtil.error("log4j load failed");
     	}
     }

     private void configLoad() {
		Document document = null;
		try {
			document = XMLUtil.reader.read(configPath);
		} catch (DocumentException e) {
			LogUtil.error(e);
	        LogUtil.info("config load failed");
		}
        Element root = document.getRootElement();
    	for(Iterator<Element> it = root.elementIterator();it.hasNext();){
			Element element =(Element)it.next();
			Attribute idAttribute =element.attribute("id");
			Attribute classAttribute=element.attribute("class");
			if(null==idAttribute||null==classAttribute){
				LogUtil.warn("element is invalid  line number: ",((XMLUtil.GokuElement) element).getLineNumber());
				continue;
			}else{
				String className=classAttribute.getValue();
				Object o = ReflectUtil.newObjByClassName(className);
				for(Element child:element.elements()){
					if("property".equals(child.getName())){
						String attributeName = child.attribute("name").getValue();
						String attributeValue = child.attribute("value").getValue();
						String setAttributeMethodName = StringUtil.camelCaseSetMethod(attributeName);
						ReflectUtil.initializeObj(o,setAttributeMethodName,attributeName,attributeValue);
					}
				}
				ConfigContext.add(idAttribute.getValue(),o);
				LogUtil.info("config load bean: ",idAttribute.getValue());
			}
		}
    	LogUtil.info("config load success");
     }
     
 
     
     
     public void hotUpdate(){
    	 
     }
     
     public static void main(String[] args){
    	new ConfigThread().start();
     }
     
     
     
}
