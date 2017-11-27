package com.tlyy.util;

public class StringUtil {
    public static boolean isEmpty(String str){
    	return null==str || "".equals(str);
    }
    
    public static String camelCaseSetMethod(String attribute){
		StringBuilder sb = new StringBuilder();
		sb.append("set");
		sb.append(Character.toUpperCase(attribute.charAt(0)));
		sb.append(attribute.substring(1,attribute.length()));
		return sb.toString();
    }
    
}
