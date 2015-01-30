package com.ht.court.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.court.model.CourtTrialRecording;
import com.hx.test.HxSpringTestCase;
import com.hx.util.Page;

/**
 * 录像信息服务单元测试。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/16
 *
 */
public class CourtTrialRecordingServiceTest extends HxSpringTestCase {

	public static final String MODEL_ID="1";
	
	@Autowired
	private CourtTrialRecordingService courtTrialRecordingService;

	/**
	 * 测试查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindByPage() throws Exception {
		Page<CourtTrialRecording> page=courtTrialRecordingService.findByPage(0, Integer.MAX_VALUE, null);
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
		CourtTrialRecording courtTrialRecording=new CourtTrialRecording();
		courtTrialRecording.genUUIDIfNeeded();
		String uid=courtTrialRecording.getId();
		courtTrialRecordingService.create(courtTrialRecording);
		courtTrialRecording=courtTrialRecordingService.get(uid);
		assertNotNull(courtTrialRecording);
		assertEquals(uid,courtTrialRecording.getId());
	}

	/**
	 * 测试按主键查询。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {
		CourtTrialRecording courtTrialRecording=courtTrialRecordingService.get(MODEL_ID);
		assertNotNull(courtTrialRecording);
		assertEquals(MODEL_ID,courtTrialRecording.getId());
	}

	/**
	 * 测试更新。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		CourtTrialRecording courtTrialRecording=courtTrialRecordingService.get(MODEL_ID);
		String updateUser="somebody";
		courtTrialRecording.setUpdateUser(updateUser);
		courtTrialRecordingService.update(courtTrialRecording);
		courtTrialRecording=courtTrialRecordingService.get(MODEL_ID);
		assertEquals(updateUser,courtTrialRecording.getUpdateUser());
	}

	/**
	 * 测试删除。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		CourtTrialRecording courtTrialRecording=courtTrialRecordingService.get(MODEL_ID);
		assertNotNull(courtTrialRecording);
		courtTrialRecordingService.delete(MODEL_ID);
		courtTrialRecording=courtTrialRecordingService.get(MODEL_ID);
		assertNull(courtTrialRecording);
	}

}