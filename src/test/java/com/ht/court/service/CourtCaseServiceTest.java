package com.ht.court.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.court.model.CourtCaseWithBLOBs;
import com.hx.test.HxSpringTestCase;
import com.hx.util.Page;

/**
 * 案件信息服务单元测试。
 * 
 * @author huangweiyong
 * @version 1.0 2014/03/14
 *
 */
public class CourtCaseServiceTest extends HxSpringTestCase {

	public static final String MODEL_ID="1";
	
	@Autowired
	private CourtCaseService courtCaseService;

	/**
	 * 测试查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindByPage() throws Exception {
		Page<CourtCaseWithBLOBs> page=courtCaseService.findByPage(0, 10, null);
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
		CourtCaseWithBLOBs courtCase=new CourtCaseWithBLOBs();
		courtCase.genUUIDIfNeeded();
		String uid=courtCase.getId();
		courtCaseService.create(courtCase);
		courtCase=courtCaseService.get(uid);
		assertNotNull(courtCase);
		assertEquals(uid,courtCase.getId());
	}

	/**
	 * 测试按主键查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {
		CourtCaseWithBLOBs courtCase=courtCaseService.get(MODEL_ID);
		assertNotNull(courtCase);
		assertEquals(MODEL_ID,courtCase.getId());
	}

	/**
	 * 测试更新。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		CourtCaseWithBLOBs courtCase=courtCaseService.get(MODEL_ID);
		String updateUser="somebody";
		courtCase.setUpdateUser(updateUser);
		courtCaseService.update(courtCase);
		courtCase=courtCaseService.get(MODEL_ID);
		assertEquals(updateUser,courtCase.getUpdateUser());
	}

	/**
	 * 测试删除。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		CourtCaseWithBLOBs courtCase=courtCaseService.get(MODEL_ID);
		assertNotNull(courtCase);
		courtCaseService.delete(MODEL_ID);
		courtCase=courtCaseService.get(MODEL_ID);
		assertNull(courtCase);
	}

}