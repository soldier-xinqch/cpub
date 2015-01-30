package com.ht.court.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author xinqichao
 *
 * @version 0.0.1 
 *	
 * @介绍 ：本程序用来验证SMTP服务器身份。程序集成Authenticator 类，它主要用来实现登陆邮件服务器时的认证。它包含两个属性，
 * 		      用户名username 密码password ，分别用来存储认证时所需的用户名和密码信息。
 *         程序重写了Authenticator类的getPasswordAuthentication方法，该方法返回PasswordAuthenticator对象，此对象包含通过STMP
 *         服务器身份验证所需的用户名和密码，供认证时session调用。
 */
public class Auth extends Authenticator{

	private String userName="";
	private String password="";
	
	public Auth(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
