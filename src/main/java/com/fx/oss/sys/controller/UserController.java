package com.fx.oss.sys.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.SessionUserService;
import com.fx.oss.sys.service.UserService;
import com.fx.oss.utils.OssEnum;

/**
 * 用户控制器。
 * <p>
 * 负责用户登录、信息修改等。
 * 
 * @author huangweiyong
 * @version 1.0 2013/6/22
 */
@Controller
@RequestMapping(value="/sys/user")
public class UserController{
	
	private static Logger logger=LoggerFactory.getLogger(UserController.class);
    public static final String LOGIN_PAGE = "login";
    public static final String KEI_PAGE = "kei";
    public static final String DEFAULT_ACCESS_PAGE = "loginSuccess";
    public static final String ACCESS_DENIED_PAGE = "accessDenied";
	
	@Autowired
	private UserService userService;
	@Autowired
	private SessionUserService sessionUserService;
	
	
	   /**
     * 处理并返回登录页面。
     *
     * @return 登录页面。
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(HttpServletRequest request, 
        @RequestParam(value = "error", required = false) boolean error, Model model) {
        if (error == true) {
            String  userName = (String) request.getSession()
            		.getAttribute("SPRING_SECURITY_LAST_USERNAME");
            if(userName!=null){
            	User user=userService.getUserByUserName(userName);
            	String errMsg="密码错误。";
            	model.addAttribute("errType", "pwdErr");
            	if(user==null){
            		errMsg=MessageFormat.format("用户{0}不存在。",userName);
            		model.addAttribute("errType", "unameErr");
            	}
            	logger.error("用户{}尝试登录，{}",userName,errMsg);
            	//设置错误消息
            	model.addAttribute(OssEnum.MSG.MSG_ERROR.name(), errMsg);
            	model.addAttribute("userName", userName);
            	
            }
        }else{
        	//获取session用户信息
        	String userName=sessionUserService.getUserName();
        	if(userName!=null){
        		logger.info("用户{}已登录，重定向到首页",userName);
        		return "redirect:/courtPub/vod/pubBillboard";

        	}
        }
        return LOGIN_PAGE;
    }

    /**
     * 处理并返回拒绝jsp页面。
     *  <p> 
     *  当已登录用户访问非授权资源时调用。
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return ACCESS_DENIED_PAGE;
    }
    
    /**
     * 密码修改。
     * 
     * @param id 用户id。
     * @param modal 视图层数据。
     * @param user
     * @return
     */
    @RequestMapping(value = "/{id}/changePwd", method = RequestMethod.PUT)
    public String changePwd(@PathVariable Long id, Model modal, @RequestParam String newPassword){
    	
    	return null;
    }
}
