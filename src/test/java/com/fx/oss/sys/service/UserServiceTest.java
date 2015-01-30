package com.fx.oss.sys.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.fx.oss.domain.User;
import com.fx.oss.exception.PasswordMismatchException;
import com.fx.oss.exception.UserNotFoundException;
import com.fx.oss.test.OssTransactionalUnitT;

public class UserServiceTest extends OssTransactionalUnitT{
	@Autowired
	private UserService userService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//加载数据到db
		super.loadFixture();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin1() {
		try {
			userService.login("sb", "sb");
			fail("it should not go here.");
		} catch (UserNotFoundException e) {
			assert(true);
		} catch (PasswordMismatchException e) {
			fail("it should not go here.");
		}
	}
	
	@Test
	public void testLogin2(){
		try {
			User user=userService.login("admin", "123456");
			assertNotNull(user);
		} catch (UserNotFoundException e) {
			fail("用户admin没有找到。");
			e.printStackTrace();
		} catch (PasswordMismatchException e) {
			fail("用户admin密码不匹配。");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserById(){
		User user=userService.getUserById(1l);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserByUserName(){
		User user=userService.getUserByUserName("admin");
		assertNotNull(user);
	}
	
	@Test
	public void testLoadUserByUsername(){
		UserDetails udet=userService.loadUserByUsername("admin");
		assertEquals(udet.getUsername(),"admin");
		assertEquals(1,udet.getAuthorities().size());
	}

}
