package com.fx.oss.sys.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器。
 * 
 * @author huangweiyong
 * @version 1.0 2013/6/22
 *
 */
@Controller
public class HomeController {
	/**
	 * 默认首页"/"。
	 * <p>
	 * 如果用户没有登录，转到登录页；否则跳转到主页面。
	 * 
	 * @return
	 */
	@RequestMapping(value="/")
	public String index(){
		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = "";
        if (userObj != null) {
            if (userObj instanceof org.springframework.security.core.userdetails.User) {
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) userObj;
                userName = user.getUsername();
            } else {
                //userName = userObj;
            	//do nothing
            }
        }
        if(StringUtils.isEmpty(userName)){
        	return "redirect:/sys/user/login";
        }else{
        	return "redirect:/courtPub/vod/pubBillboard";
        }
	}
	
}
