package com.xinhuanet.app.tool;

public class ConverterNull {

	public  static Object converternull(Object obj){
		
		if(obj==null){
			obj="";
		}
		
		return obj;
	}
	
}
