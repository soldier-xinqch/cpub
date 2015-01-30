package com.fx.oss.exception;

import com.fx.oss.base.OssException;

/**
 * 密码不匹配异常。
 * 
 * @author huangweiyong
 * @version 1.0 2012/6/22
 *
 */
public class PasswordMismatchException extends OssException{

	private static final long serialVersionUID = 1L;
	
	public PasswordMismatchException(){
		super();
	}
	
	public PasswordMismatchException(String message){
		super(message);
	}
	
	public PasswordMismatchException(String message,Throwable t){
		super(message,t);
	}

}
