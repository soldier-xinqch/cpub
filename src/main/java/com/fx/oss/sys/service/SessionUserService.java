package com.fx.oss.sys.service;

import com.fx.oss.domain.User;
/**
 * 会话用户服务。
 * <p>
 * 会话场景由实现者实现。
 * 
 * @author huangweiyong
 * @version 1.0 2013/6/22
 */
public interface SessionUserService {
	Long getUserId();
	String getUserName();
	String getUserRealName();
	/**
	 * 获取当前用户账号。
	 * <p>
	 * 
	 * @return 返回用户实体。
	 */
	User getUserAccount();
}
