package com.tlyy.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map.Entry;

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
	
     @Override
     public void run(){
    	try{
    		init();
    		xmlload();
    	}catch(FileNotFoundException e){
    		e.printStackTrace();
    	}catch(DocumentException e){
    		LogUtil.error(e);
    	} catch (InstantiationException e) {
			LogUtil.error(e);
		} catch (IllegalAccessException e) {
		    LogUtil.error(e);
		} catch (ClassNotFoundException e) {
		    LogUtil.error(e);
		} catch (IllegalArgumentException e) {
			LogUtil.error(e);
		} catch (InvocationTargetException e) {
			LogUtil.error(e);
		} catch (SecurityException e) {
			LogUtil.error(e);
		} catch (NoSuchFieldException e) {
			LogUtil.error(e);
		} catch (NoSuchMethodException e){
			LogUtil.error(e);
		}
    	hotUpdate();
    	for(Entry<String,Object> e :ConfigContext.getConfigInfo().entrySet()){
    		LogUtil.info(e.getKey(),e.getValue());
    		LogUtil.info("...");
    	}
     }
     
     
     
     private void init() throws FileNotFoundException, DocumentException{
    	File log4j2File = new File(log4j2Path);
     	ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4j2File),log4j2File);
 		Configurator.initialize(null, source);
     }
     
     private void xmlload() throws DocumentException,InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchFieldException, NoSuchMethodException{
		Document document  = XMLUtil.reader.read(configPath);
        Element root = document.getRootElement();
    	for(Iterator<Element> it = root.elementIterator();it.hasNext();){
			Element e =(Element)it.next();
			Attribute idAttribute =e.attribute("id");
			Attribute classAttribute=e.attribute("class");
			if(null==idAttribute||null==classAttribute){
				LogUtil.warn("element is invalid  line number: ",((XMLUtil.GokuElement) e).getLineNumber());
				continue;
			}else{
				String className=classAttribute.getValue();
				Class<?> clazz =Class.forName(className);
				Object o =clazz.newInstance();
				for(Element child:e.elements()){
					if("property".equals(child.getName())){
						String attributeName = child.attribute("name").getValue();
						String setAttributeMethodName = StringUtil.camelCaseSetMethod(attributeName);
						Field field = clazz.getDeclaredField(attributeName);
						Method setAttributeMethod =clazz.getDeclaredMethod(setAttributeMethodName,field.getType());
						setAttributeMethod.invoke(o,ReflectUtil.castArg(child.attribute("value").getValue(), field.getType()));	
					}
				}
				ConfigContext.add(idAttribute.getValue(),o);
			}

		}
     }
     
 
     
     
     public void hotUpdate(){
    	 
     }
     
     public static void main(String[] args){
    	new ConfigThread().start();
     }
     
     
     
}
