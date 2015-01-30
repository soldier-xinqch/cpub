package com.ht.court.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.court.model.CourtCasePlan;
import com.hx.test.HxSpringTestCase;
import com.hx.util.Page;

/**
 * ������Ϣ����Ԫ���ԡ�
 * 
 * @author xinqichao
 * @version 1.0 2014/03/18
 *
 */
public class CourtCasePlanServiceTest extends HxSpringTestCase {

	public static final String MODEL_ID="1";
	
	@Autowired
	private CourtCasePlanService courtCasePlanService;

	/**
	 * ���Բ�ѯ��
	 * 
	 * @throws Exception
	 */
//	@Test
	public void testFindByPage() throws Exception {
		Page<CourtCasePlan> page=courtCasePlanService.findByPage(0, 5, null);
		assertNotNull(page);
		assertEquals(1,page.getList().size());
	}

	/**
	 * ����������
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreate() throws Exception {
		CourtCasePlan courtCasePlan=new CourtCasePlan();
		courtCasePlan.genUUIDIfNeeded();
		String uid=courtCasePlan.getId();
		courtCasePlanService.create(courtCasePlan);
		courtCasePlan=courtCasePlanService.get(uid);
		assertNotNull(courtCasePlan);
		assertEquals(uid,courtCasePlan.getId());
	}

	/**
	 * ���԰������ѯ��
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {
		CourtCasePlan courtCasePlan=courtCasePlanService.get("0013754722b443f285cf5923192baec6");
		assertNotNull(courtCasePlan);
		assertEquals("0013754722b443f285cf5923192baec6",courtCasePlan.getId());
	}

	/**
	 * ���Ը��¡�
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		CourtCasePlan courtCasePlan=courtCasePlanService.get("0013754722b443f285cf5923192baec6");
		String updateUser="somebody";
		courtCasePlan.setUpdateUser(updateUser);
		courtCasePlanService.update(courtCasePlan);
		courtCasePlan=courtCasePlanService.get("0013754722b443f285cf5923192baec6");
		assertEquals(updateUser,courtCasePlan.getUpdateUser());
	}

	/**
	 * ����ɾ��
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		CourtCasePlan courtCasePlan=courtCasePlanService.get("1397019035277");
		assertNotNull(courtCasePlan);
		courtCasePlanService.delete("1397019035277");
		courtCasePlan=courtCasePlanService.get("1397019035277");
		assertNull(courtCasePlan);
	}

}