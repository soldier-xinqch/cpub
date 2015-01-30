package com.ht.court.service;

import com.hx.util.Page;
import com.ht.court.model.CourtTrialRecording;
import com.ht.court.model.CourtTrialRecordingExample;
import com.ht.court.mapper.CourtTrialRecordingMapper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 录像信息服务实现。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/09
 *
 */
@Service("courtTrialRecordingService")
@Transactional
public class CourtTrialRecordingServiceImpl implements CourtTrialRecordingService {
	
	@Autowired
	private CourtTrialRecordingMapper mapper;

	public Page<CourtTrialRecording> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys){
		CourtTrialRecordingExample qbe=new CourtTrialRecordingExample();
		qbe.createCriteria().andDelFlagEqualTo(false);
		List<CourtTrialRecording> courtTrialRecordingList=mapper.selectByExample(qbe);
		Page<CourtTrialRecording> page=new Page<CourtTrialRecording>();
		page.setResults(courtTrialRecordingList);
		return page;
	}

	public void create(CourtTrialRecording courtTrialRecording) {
		courtTrialRecording.genUUIDIfNeeded();
		mapper.insert(courtTrialRecording);
	}

	public CourtTrialRecording get(String id) {
		CourtTrialRecordingExample qbe=new CourtTrialRecordingExample();
		qbe.createCriteria().andIdEqualTo(id).andDelFlagEqualTo(false);
		List<CourtTrialRecording> list=mapper.selectByExample(qbe);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	public void update(CourtTrialRecording courtTrialRecording) {
		courtTrialRecording.setUpTime();
		mapper.updateByPrimaryKey(courtTrialRecording);
	}

	public void delete(String id) {
		CourtTrialRecording courtTrialRecording=this.get(id);
		courtTrialRecording.setDelFlag(true);
		courtTrialRecording.setUpTime();
		this.update(courtTrialRecording);
	}

	public List<CourtTrialRecording> getByCaseIdAndPlanId(String caseId,String planId){
		CourtTrialRecordingExample qbe=new CourtTrialRecordingExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andCaseIdEqualTo(caseId).andPlanIdEqualTo(planId);
		List<CourtTrialRecording> list=mapper.selectByExample(qbe);
		return list;
	}
	
}