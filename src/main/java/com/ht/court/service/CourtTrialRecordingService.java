package com.ht.court.service;

import java.util.List;
import java.util.Map;

import com.hx.util.Page;
import com.ht.court.model.CourtTrialRecording;

/**
 * 录像信息服务接口。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/09
 *
 */
public interface CourtTrialRecordingService {
	
	/**
	 * 分页获取录像信息。
	 *
	 * @return 返回分页记录。
	 */
	public Page<CourtTrialRecording> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys);
	
	/**
	 * 新建录像信息。
	 * 
	 * @param courtTrialRecording 新增的录像信息对象。
	 */
	public void create(CourtTrialRecording courtTrialRecording);
	
	/**
	 * 根据ID获取录像信息实体。
	 * 
	 * @param id 录像信息 ID。
	 */
	public CourtTrialRecording get(String id);
	
	/**
	 * 更新录像信息。
	 * 
	 * @param courtTrialRecording 变更的录像信息对象。
	 */
	public void update(CourtTrialRecording courtTrialRecording);
	
	/**
	 * 根据ID删除录像信息实体。
	 * 
	 * 
	 * @param id 录像信息 ID。
	 */
	public void delete(String id);
	
	public List<CourtTrialRecording> getByCaseIdAndPlanId(String caseId,String planId);
}