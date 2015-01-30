package com.ht.court.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ht.court.vo.CasePlanVO;
import com.ht.court.vo.CasePlansVO;
import com.hx.test.HxSpringTestCase;

public class CasePubRestServiceTest  extends HxSpringTestCase{
	
	@Autowired
	private CasePubRestService casePubRestService;

	@Test
	@Ignore
	public void testFindPubCases() throws Exception {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime startDate=fmt.parseDateTime("2010-04-01");
		DateTime endDate=fmt.parseDateTime("2034-04-30");
		List<CasePlanVO> list=casePubRestService.findPubCases(startDate.toDate(), endDate.toDate(), Integer.MAX_VALUE,"1");
		assertTrue(list.size()>0);
		int size=list.size();
		System.out.println("a=========" + size);
	}

	@Test
	@Ignore
	public void testGetCasePlan() throws Exception {
		CasePlanVO vo=casePubRestService.getCasePlan("1396365034451", "0013754722b443f285cf5923192baec6");
		assertNotNull(vo.getCourtCase());
		assertNotNull(vo.getCasePlan());
	}

	@Test
	@Ignore
	public void testGetCasePlans() throws Exception {
		CasePlansVO vo=casePubRestService.getCasePlans("1396365034451");
		assertNotNull(vo.getCourtCase());
		assertTrue(vo.getCasePlans().size()>0);
	}

	@Test
	@Ignore
	public void testFindLiveNow() throws Exception {
		List<CasePlanVO> vos=casePubRestService.findLiveNow(null);
		assertTrue(vos.size()==1);
	}

	@Test
	public void testSearchByQueryConditions() throws Exception {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime startDate=fmt.parseDateTime("2014-04-01");
		
		Map<String,Object> conds=new HashMap<String,Object>();
		conds.put("startIndex", 0);
		conds.put("pageSize", 100);
		conds.put("startTime", startDate.toDate());
		//conds.put("endTime", startDate.toDate());
		//conds.put("courtId", "1");
		//conds.put("courtName", "不懂");
		//conds.put("caseName", "少年");
		//conds.put("caseType", "法检赔偿");
		List<CasePlansVO> vos=casePubRestService.searchByQueryConditions(conds);
		
		assertNotNull(vos);
	}

}
