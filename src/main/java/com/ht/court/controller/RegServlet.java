package com.ht.court.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ht.court.util.SendMail;

/**
 * @author xinqichao
 * 
 * @说明：servlet类主要是获取用户提交的注册信息，并且发送邮件到注册用户的邮箱中。
 *        获取ServletConfig对象，并将其复制给Servletconfig,再通过Servletcong.getInitParameter方法从web.xml配置文件中获取变量
 *        改发送邮件部分只用邮件服务器信息全部写入web.xml中，发送到注册用户的邮箱中！
 *
 */
public class RegServlet extends HttpServlet {

	private static final long serialVersionUID = 8912038161357456734L;
//	private ServletConfig ServletConfig;
	private String username;
	private String email;
	private String msg;
	
	/**
	 *  初始化方法
	 */
//	public void init(ServletConfig config) throws ServletException{
//		super.init(config);
//		this.ServletConfig = config;
//	}
	/**
	 *  Servlet 接收GET方法
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		this.doPost(request, response);
	} 
	/**
	 * Servlet 接收POST方法
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		System.out.println("有方法进来了！");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("编译异常：JDK版本不同 ");
			e.printStackTrace();
		}
		username = request.getParameter("username");
		email = request.getParameter("email");
		//TODO 做些其他的处理
		if(sendMail(email, username)){
			msg ="邮件发送成功！";
		}else{
			msg = "邮件发送失败！";
		}
		try {
			toResponse(response, msg);
		} catch (IOException e) {
			System.out.println("流发送失败！");
			e.printStackTrace();
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
	
	private void toResponse(HttpServletResponse response,String toString) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(toString);
	}

}
