package com.fx.oss.sys.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fx.oss.domain.User;
import com.fx.oss.exception.PasswordMismatchException;
import com.fx.oss.exception.UserNotFoundException;
import com.hx.util.Page;

/**
 * 用户服务。
 * <p>
 * 用户是系统使用主体，提供用户验证、用户信息编辑等服务。
 * 
 * @author huangwy
 *
 */
public interface UserService extends UserDetailsService{
	/**
	 * 用户登录。
	 * 
	 * @param userName 登录用户名。
	 * @param password 登录密码。
	 * @return
	 * @throws UserNotFoundException 用户名不存在时抛出。
	 * @throws PasswordMismatchException 用户密码不匹配时抛出。
	 */
	User login(String userName,String password) throws UserNotFoundException, PasswordMismatchException;
	/**
	 * 根据用户名获取用户。
	 * 
	 * @param userName 用户名。
	 * @return 返回用户实体。
	 */
	User getUserByUserName(String userName);
	/**
	 * 根据用户id获取用户。
	 * 
	 * @param id 用户id。
	 * @return 返回用户实体。
	 */
	User getUserById(Long id);
	
	/**
	 * 根据法院ID来获取用户列表
	 * @param courtId
	 * @return
	 * @ 
	 */
	List<User> getUserListByCourtId(String courtId)  ;
	/**
	 * 查询所有的用户
	 * @return
	 * @ 
	 */
	List<User> findAll()  ;
	/**
	 * 分页显示用户列表
	 * @param request
	 * @return
	 * @ 
	 */
	Page<User> findUserByPage (List<String> courtIds,int startIndex,int  pageSize) ;
	/**
	 * 得到所有用户的数量
	 * @return
	 * @ 
	 */
	public int getUserNum()  ;
	/**
	 * 分页显示用户列表
	 * @param begin
	 * @param size
	 * @return
	 * @ 
	 */
	List<User> getUserByPage(int begin,int size)  ;
	/**
	 * 新增一条用户信息
	 * @ 
	 */
	void insert(User user) ;
	/**
	 *  更改用户
	 * @param user
	 */
	void update(User user)  ;
	/**
	 * 删除用户
	 * @param id
	 */
	void delete(Long id) ;
	
	/**
	 *  显示合议庭成员列表
	 * @param courtId  法院ID
	 * @param roles  用户角色
	 * @param begin  开始行数
	 * @param size  显示条数
	 * @return
	 */
	List<User> getHEYIPersons(List<String> courtIds,String roles,int begin,int size);
	/**
	 * 分页显示合议庭成员列表
	 * @param courtId 法院ID
	 * @param roles 用户角色
	 * @param startIndex  开始行数
	 * @param pageSize  显示条数
	 * @return
	 */
	Page<User> findHEYIByPage (List<String> courtIds,String roles,int startIndex,int  pageSize) ;
	/**
	 *  分页显示合议庭成员记录条数
	 * @param courtId
	 * @param roles
	 * @return
	 */
	int getHEYINums(List<String> courtIds,String roles);
	
	/**
	 * 分页获得模糊查询的用户数
	 * @param courtId
	 * @param userName
	 * @return
	 */
	int getUserNumLikeName(String courtId,String userName,String roles) ;
	
	/**
	 *  显示模糊查询的用户列表
	 * @param courtId
	 * @param userName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<User> findUserLikeName(String courtId,String userName,String roles,int startIndex,int  pageSize) ;
	
	/**
	 *  分页显示模糊查询的用户列表
	 * @param courtId
	 * @param userName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	Page<User> getUserByPage(String courtId,String userName,String roles,int startIndex,int  pageSize) ;
	
	/**
	 * 权限表示是否可以删除点播信息
	 * 
	 * @param courtId
	 * @param roles
	 * @return
	 */
	int privilege(String courtId,String roles);
}
