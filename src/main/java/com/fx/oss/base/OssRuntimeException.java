package com.fx.oss.base;

/**
 * 运营系统运行时异常基类。
 * 
 * @author huangwy
 *
 */
public class OssRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public OssRuntimeException(){
		
	}

	public OssRuntimeException(String message){
		super(message);
	}
	
	public OssRuntimeException(String message, Throwable cause){
		super(message,cause);
	}
}
