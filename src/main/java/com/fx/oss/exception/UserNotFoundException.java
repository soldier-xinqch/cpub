package com.fx.oss.exception;

import com.fx.oss.base.OssException;

/**
 * 用户不存在异常。
 * 
 * @author huangweiyong
 *
 */
public class UserNotFoundException extends OssException{
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(){
	}
	
	public UserNotFoundException(String message){
		super(message);
	}
	
	public UserNotFoundException(String message,Throwable t){
		super(message,t);
	}
}
