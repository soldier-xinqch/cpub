package com.fx.oss.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fx.oss.domain.User;

/**
 * 会话用户服务的web实现。
 * <p>
 * 根据spring security实现。
 * 
 * @author huangweiyong
 * @version 1.0 2013/6/22
 *
 */
public class SessionUserServiceSpringSecImpl implements SessionUserService{
	
	@Autowired
    private UserService userService;

	@Override
	public Long getUserId() {
		User user=getSessionUser();
		if(user==null){
			return null;
		}
		return user.getId();
	}

	@Override
	public String getUserName() {
		User user=getSessionUser();
		if(user==null){
			return null;
		}
		return user.getUsername();
	}

	@Override
	public String getUserRealName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserAccount() {
		User user=getSessionUser();
		if(user==null){
			return null;
		}
		return userService.getUserById(user.getId());
	}
	
	/*
	 * 从spring security中获取用户对象。
	 */
	private static User getSessionUser(){
		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userObj != null && (userObj instanceof User)) {
            return (User)userObj;
        }
        return null;
	}

}
