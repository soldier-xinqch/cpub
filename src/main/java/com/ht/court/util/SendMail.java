package com.ht.court.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author xinqichao
 * 
 * @说明： 邮件的发送工具类，其中SendMail构造方法实现对发送邮件身份的认证，获得Session对象，该方法的SMTPHost，Port等发送参数，分别为
 * 		    使用的SMTP服务器。服务器端口，用户名和密码，sendingMimeMail方法为发送邮件的方法
 *        发送邮件时限调用构造方法创建一个邮件发送对象，然后调用sendingMimeMail方法发送；
 *
 */
public class SendMail {

	private Properties props;// 系统属性
	private Session mailSession;// 邮件会话对象
	private MimeMessage mimeMsg;// MIME 邮件对象
	
	public SendMail(String SMTPHost,String Port,String MailUsername,String MailPassword){
		Auth au = new Auth(MailUsername,MailPassword);
		props = java.lang.System.getProperties();// 获得系统属性对象
		props.put("mail.smtp.host", SMTPHost);// 设置SMTP主机
		props.put("mail.smtp.post", Port);// 设置服务端口 号
		props.put("mail.smtp.auth", "true");// 通过通过验证
		// 获得邮件会话对象
		mailSession = Session.getInstance(props, au);
	}
	
	/**
	 * @param MailFrom
	 * @param MailTo
	 * @param MailCopyTo
	 * @param MailBCopyTo
	 * @param MailSubject
	 * @param MailBody
	 * @return
	 */
	/**
	 * @param MailFrom
	 * @param MailTo
	 * @param MailCopyTo
	 * @param MailBCopyTo
	 * @param MailSubject
	 * @param MailBody
	 * @return
	 */
	public boolean sendingMimeMail(String MailFrom,String MailTo,String MailCopyTo,String MailBCopyTo,String MailSubject,String MailBody){
		try{
			//创建MIME邮件对象
			mimeMsg = new MimeMessage(mailSession);
			//设置发信人
			mimeMsg.setFrom(new InternetAddress(MailFrom));
			//设置收件人
			if (MailTo != null) {
				mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailTo));
			}
			//设置抄送人
			if (MailCopyTo != null) {
				mimeMsg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(MailBCopyTo));
			}
			//设置密送人（暗送人）
			if (MailTo != null) {
				mimeMsg.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(MailBCopyTo));
			}
			//设置邮件主题
			mimeMsg.setSubject(MailSubject, "UTF-8");
			// 设置邮件内容，将邮件body部分转化为HTML格式
			mimeMsg.setContent(MailBody,"text/html;charset=utf-8");
			//发送邮件
			Transport.send(mimeMsg);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * @param mailTo
	 * @param userName
	 * @return
	 */
	public boolean sendMail(String mailTo,String userName){
//		String MailTo = mailTo;
		String MailSubject = "Westlake International - Application Received";
		String MailBCopyTo = "";
		String MailCopyTo = "";
		String MailBody = "<p>恭喜您注册成功！<br> <a src='www.baidu.com'>百度一下</a></p>";
		
		//FIXME（从web.xml文件配置的参数中获取数据）
//		String SMTPHost = ServletConfig.getInitParameter("smtphost");//服务器
//		String Port = ServletConfig.getInitParameter("port");//服务器监听的端口
//		String MailUserName = ServletConfig.getInitParameter("mailusername");//邮箱登陆的用户名
//		String MailPassWord = ServletConfig.getInitParameter("mailpassword");//邮箱登陆的密码
//		String MailFrom = ServletConfig.getInitParameter("mailfrom");//邮箱地址
		
		String SMTPHost = "smtp.163.com";//服务器
		String Port = "25";//服务器监听的端口
		String MailUserName = "xinchao32119";//邮箱登陆的用户名
		String MailPassWord = "xqc193414qq";//邮箱登陆的密码
		String MailFrom = "xinchao32119@163.com";//邮箱地址
		
		if(SMTPHost == null || SMTPHost == "" || MailUserName == null || MailUserName =="" ||
				MailPassWord == null || MailPassWord == "" || MailFrom == null || MailFrom == ""){
			System.out.println("Servlet parameter Wrongs!");
		}
		SendMail send = new SendMail(SMTPHost, Port, MailUserName, MailPassWord);
		if(send.sendingMimeMail(MailFrom, MailFrom, MailCopyTo, MailBCopyTo, MailSubject, MailBody)){
			return true;
		}else{
			return false;
		}
	}
}

