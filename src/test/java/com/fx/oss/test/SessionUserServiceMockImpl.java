package com.fx.oss.test;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.SessionUserService;

/**
 * 为测试提供测试会话mock服务。
 * 
 * @author Weiyong Huang
 * @version 1.0 2013/6/22
 */
public class SessionUserServiceMockImpl implements SessionUserService{

	@Override
	public Long getUserId() {
		return 1l;
	}

	@Override
	public String getUserName() {
		return "admin";
	}

	@Override
	public String getUserRealName() {
		return "管理员";
	}

	@Override
	public User getUserAccount() {
		User user=new User();
		user.setId(getUserId());
		user.setUserName(getUserName());
		user.setRealName(getUserRealName());
		return user;
	}

}
