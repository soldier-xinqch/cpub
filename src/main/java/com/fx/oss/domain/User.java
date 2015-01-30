package com.fx.oss.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fx.oss.base.AbstractModel;

/**
 * 用户实体。
 * <p>
 * 系统账户实体，实现spring安全用户接口。
 * 
 * @author huangwy
 * @version 1.0 2013/6/11
 *
 */
public class User extends AbstractModel implements UserDetails{

	private static final long serialVersionUID = 6118107291432768670L;
	public static final String ROLE_SPLITTER=",";
	private String userName;
	private String userPassword;
	private String realName;
	private String roles;
	private String courtId;
	private String photo;
	private Date createTime;
	private Date updateTime;
	private String courtName;
	//安全控制，授权列表
	private List<GrantedAuthority> grantedAuthorities;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}
	
	public void setAuthorities(List<GrantedAuthority> grantedAuthorities){
		this.grantedAuthorities=grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		return this.getUserName();
	}
	
	/**
	 * 账户是否没有过期。
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账户是否没有被锁定。
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 账户是否没有过期。
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * 账户是否被激活。
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * 获取用户角色标识。
	 * <p>
	 * 角色标识以“,”分隔，每个角色标识形式为ROLE_XX，字母大写。
	 * 
	 * @return 返回角色标识字符串。
	 */
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	
	

}
