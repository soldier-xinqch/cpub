package com.fx.oss.sys.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fx.oss.domain.User;

/**
 * 用户ORM。
 * 
 * @author huangwy
 *
 */
public interface UserMapper {
	
	/**
	 * 根据用户名称查询用户
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);
	
	/**
	 * 根据用户ID来查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
	
	/**
	 * 根据法院ID来获取用户列表
	 * @param courtId
	 * @return
	 */
	List<User> getUserByCourtId(String courtId);
	/**
	 * 获取所有的用户列表
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * 获取所有用户数
	 * @return
	 */
	int getUserNum();
	
	/**
	 * 分页显示用户列表
	 * @param begin
	 * @param size
	 * @return
	 */
	List<User> findAllByPage(@Param("begin") int begin,@Param("size")  int size);
	
	/**
	 * 新增一条用户信息
	 * @param user
	 */
	void insertUser(User user);
	
	/**
	 * 更改一条用户信息
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);
	
	/**
	 * 根据角色和法院ID来分页显示用户列表
	 * @param list
	 * @param roles
	 * @param begin
	 * @param size
	 * @return
	 */
	List<User> getHEYIPersons(@Param("list") List<String> list,@Param("roles") String roles,@Param("begin") int begin,@Param("size")  int size);

	/**
	 * 根据角色和法院ID来获得用户数量
	 * @param list
	 * @param roles
	 * @return
	 */
	int getHEYINums(@Param("list") List<String> list,@Param("roles") String roles);
	
	/**
	 * 模糊查询分页显示用户列表
	 * @param courtId 法院ID
	 * @param userName 模糊查询参数
	 * @return
	 */
	List<User> findUserLikeName(@Param("courtId")  String courtId,@Param("userName") String userName,@Param("roles") String roles,@Param("begin") int begin,@Param("size")  int size);
	
	/**
	 * 模糊查询分页显示用户数
	 * @param courtId 法院ID
	 * @param userName 模糊查询参数
	 * @return
	 */
	int getUserNumLikeName(@Param("courtId")  String courtId,@Param("userName") String userName,@Param("roles") String roles);
}
