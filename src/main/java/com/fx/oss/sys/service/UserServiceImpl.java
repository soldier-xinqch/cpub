package com.fx.oss.sys.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.oss.domain.User;
import com.fx.oss.exception.PasswordMismatchException;
import com.fx.oss.exception.UserNotFoundException;
import com.fx.oss.sys.persistence.UserMapper;
import com.fx.oss.utils.MD5;
import com.ht.court.service.CourtCaseServiceImpl;
import com.ht.court.util.FunctionUtil;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(CourtCaseServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GetOrgList getOrgList;
	@Autowired
	private TpOrgService tpOrgService;
	
	
 

	@Override
	public User login(String userName, String password) throws UserNotFoundException, PasswordMismatchException{
		User user=userMapper.getUserByUserName(userName);
		if(user==null){
			throw new UserNotFoundException(
					MessageFormat.format("用户{0}不存在。",userName));
		}
		MD5 md5=new MD5();
		String md5Pwd=md5.getMD5ofStr(password);
		if(!md5Pwd.equals(user.getUserPassword())){
			throw new PasswordMismatchException();
		}
		return user;
	}
	
    /**
     * 根据用户名获取用户实体对象。
     * <p>
     * 实现spring安全加载用户接口，同时加载用户角色列表。
     * 
     * @param userName 用户名。
     * @return <code>UserDetails</code>接口。
     * 
     * @throws UsernameNotFoundException 用户名不存在时掷出。 
     */
    public UserDetails loadUserByUsername(String userName) 
    									throws UsernameNotFoundException {
        User dbUser = this.getUserByUserName(userName);
        if (dbUser == null) {
            throw new UsernameNotFoundException(
            		MessageFormat.format("用户{0}不存在。",userName));
        }
        loadUserAuthorities(dbUser);
        return dbUser;
    }
    
    /*
     * 加载用户授权。
     * 
     * @param user 用户实体对象。
     * @return 授权列表。
     */
    private void loadUserAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String roles=user.getRoles();
        if(roles==null){
        	return;
        }
        String[] roleArray=roles.split(User.ROLE_SPLITTER);
        for(String role:roleArray){
        	authorities.add(new GrantedAuthorityImpl(role));
        }
        user.setAuthorities(authorities);
    }

	@Override
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

	@Override
	public User getUserById(Long id) {
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getUserListByCourtId(String courtId)   {
		List<User> users = userMapper.getUserByCourtId(courtId);
		return users;
	}

	@Override
	public List<User> findAll()   {
		List<User> users = userMapper.findAll();
		return users;
	}
	
	@Override
	public int getUserNum()   {
		return userMapper.getUserNum();
	}

	@Override
	public Page<User> findUserByPage(List<String> courtIds,int startIndex,int  pageSize)
			  {
	 
		long time = System.currentTimeMillis();
		
		int totalCount = this.getHEYINums(courtIds, null);
		System.out.println("user 查询总数所用时间：" + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<User> lis_dev = this.getHEYIPersons(courtIds,null,startIndex, pageSize);
		System.out.println("user 查询集合所用时间：" + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		Page<User> page = new Page<User>(startIndex, pageSize, totalCount, lis_dev);
		
		System.out.println("user 查询所用时间：" + (System.currentTimeMillis() - time));
		
		return page;
	}

	@Override
	public List<User> getUserByPage(int begin, int size)   {
		List<User> users = userMapper.findAllByPage(begin, size);
		return users;
	}
	
 

	@Override
	public void insert(User user)   {
		userMapper.insertUser(user);
	}
	@Override
	public void update(User user)   {
		userMapper.updateUser(user);
	}

	@Override
	public void delete(Long id) {
		userMapper.deleteUser(id);
	}

	@Override
	public List<User> getHEYIPersons(List<String> courtIds,String roles,int begin,int size ) {
		return userMapper.getHEYIPersons(courtIds, roles, begin, size);
	}

	@Override
	public int getHEYINums(List<String> courtIds, String roles) {
		return userMapper.getHEYINums(courtIds, roles);
	}
	
	@Override
	public Page<User> findHEYIByPage(List<String> courtIds, String roles,
			int startIndex, int pageSize) {
	 
		int totalCount = this.getHEYINums(courtIds, roles);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<User> lis_dev = this.getHEYIPersons(courtIds,roles,startIndex, pageSize);
		Page<User> page = new Page<User>(startIndex, pageSize, totalCount, lis_dev);
		return page;
	}

	@Override
	public int getUserNumLikeName(String courtId, String userName,String roles) {
		if("输入你要查询的用户名……".equals(userName)){
			userName=null;
		}
		return userMapper.getUserNumLikeName(courtId, userName, roles);
	}

	@Override
	public List<User> findUserLikeName(String courtId,String userName,String roles,int startIndex,int  pageSize){
		return userMapper.findUserLikeName(courtId, userName, roles, startIndex, pageSize);
	}

	@Override
	public Page<User> getUserByPage(String courtId,String userName,String roles,int startIndex, int pageSize) {
		int totalCount = this.getUserNumLikeName(courtId, userName, roles);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<User> lis_dev = this.findUserLikeName(courtId, userName, roles, startIndex, pageSize);
		Page<User> page = new Page<User>(startIndex, pageSize, totalCount, lis_dev);
		return page;
	}

	@Override
	public int privilege(String courtId ,String roles) {
		User user = (User) FunctionUtil.getUserDetails();
		int level= 0;
		if("789b267165d94776ab134a836daefa48".equals(user.getCourtId())){
			if(user.getCourtId().equals(courtId)&&"ROLE_ADMIN_HEYI".equals(roles)){
				level = 1;//可以操作合议庭成员
			}else if(user.getCourtId().equals(courtId)&&"ROLE_ADMIN".equals(roles)){
				level = 2;//不可以对同一法院的用户操作
			}
		}else{
			level = 3;//不是陕西高级人民法院的用户只可以创建合议庭用户，不能创建管理员账户
		}
		return level;
	}

	
}
