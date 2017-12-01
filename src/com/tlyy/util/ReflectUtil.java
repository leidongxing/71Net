package com.tlyy.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tlyy.log.LogUtil;

public class ReflectUtil {
    private static Object castArg(String arg,Class<?> classType){
    	if(classType.equals(java.lang.String.class)){
    		return arg;
    	}else if(classType.equals(byte.class)){
    		return Byte.valueOf(arg);
    	}else if(classType.equals(boolean.class)){
    		return Boolean.valueOf(arg);
        }else if(classType.equals(char.class)){
    		return Character.valueOf(arg.toCharArray()[0]);
    	}else if(classType.equals(short.class)){
    		return Short.valueOf(arg);
    	}else if(classType.equals(int.class)){
    		return Integer.valueOf(arg);
    	}else if(classType.equals(float.class)){
    		return Float.valueOf(arg);
    	}else if(classType.equals(double.class)){
    	    return Double.valueOf(arg);
    	}else if(classType.equals(long.class)){
    		return Long.valueOf(arg);
    	}else{
    		return null;
    	}  
    }
    
    public static Object newObjByClassName(String className){
    	Object o = null;
		try {
			Class<?> clazz = Class.forName(className);
			o =clazz.newInstance();
		}  catch(ClassNotFoundException e){
			LogUtil.error(e);
		}  catch (InstantiationException e) {
			LogUtil.error(e);
		} catch (IllegalAccessException e) {
			LogUtil.error(e);
		}
		return o;	
    }
    
    
    public static Object initializeObj(Object o,String setAttributeMethodName,String attributeName,String attributeValue){
		try {
			Field field = o.getClass().getDeclaredField(attributeName);
			Method setAttributeMethod = o.getClass().getDeclaredMethod(setAttributeMethodName,field.getType());
		    setAttributeMethod.invoke(o,castArg(attributeValue, field.getType()));	
		} catch (IllegalAccessException e) {
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
	    return o;
    }
    
    
}
