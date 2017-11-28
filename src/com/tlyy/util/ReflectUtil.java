package com.tlyy.util;

public class ReflectUtil {
    public static Object castArg(String arg,Class<?> classType){
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
}
