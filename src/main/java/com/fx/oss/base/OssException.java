package com.fx.oss.base;


/**
 * 运营系统异常基类。
 * 
 * @author huangwy
 *
 */
public class OssException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OssException(){
		super();
	}
	
	public OssException(String message){
		super(message);
	}
	
	public OssException(String message, Throwable cause){
		super(message,cause);
	}
}
