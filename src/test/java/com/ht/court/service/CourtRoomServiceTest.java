package com.ht.court.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.court.model.CourtRoom;
import com.hx.test.HxSpringTestCase;
import com.hx.util.Page;

/**
 * 法庭信息服务单元测试。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/23
 *
 */
public class CourtRoomServiceTest extends HxSpringTestCase {

	public static final String MODEL_ID="1";
	
	@Autowired
	private CourtRoomService courtRoomService;

	/**
	 * 测试查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindByPage() throws Exception {
		Page<CourtRoom> page=courtRoomService.findByPage(0, Integer.MAX_VALUE, null);
		assertNotNull(page);
		assertEquals(1,page.getList().size());
	}

	/**
	 * 测试新增。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		CourtRoom courtRoom=new CourtRoom();
		courtRoom.genUUIDIfNeeded();
		String uid=courtRoom.getId();
		courtRoomService.create(courtRoom);
		courtRoom=courtRoomService.get(uid);
		assertNotNull(courtRoom);
		assertEquals(uid,courtRoom.getId());
	}

	/**
	 * 测试按主键查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {
		CourtRoom courtRoom=courtRoomService.get(MODEL_ID);
		assertNotNull(courtRoom);
		assertEquals(MODEL_ID,courtRoom.getId());
	}

	/**
	 * 测试更新。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		CourtRoom courtRoom=courtRoomService.get(MODEL_ID);
		String updateUser="somebody";
		courtRoom.setUpdateUser(updateUser);
		courtRoomService.update(courtRoom);
		courtRoom=courtRoomService.get(MODEL_ID);
		assertEquals(updateUser,courtRoom.getUpdateUser());
	}

	/**
	 * 测试删除。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		CourtRoom courtRoom=courtRoomService.get(MODEL_ID);
		assertNotNull(courtRoom);
		courtRoomService.delete(MODEL_ID);
		courtRoom=courtRoomService.get(MODEL_ID);
		assertNull(courtRoom);
	}

}