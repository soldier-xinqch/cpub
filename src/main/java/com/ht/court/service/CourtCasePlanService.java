package com.ht.court.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.court.model.CourtCasePlan;
import com.hx.util.Page;

/**
 * 案件排期信息服务接口。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/30
 *
 */
public interface CourtCasePlanService {
	
	/**
	 * 分页获取案件排期信息。
	 *
	 * @return 返回分页记录。
	 */
	public Page<CourtCasePlan> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys);
	
	/**
	 * 新建案件排期信息。
	 * 
	 * @param courtCasePlan 新增的案件排期信息对象。
	 */
	public void create(CourtCasePlan courtCasePlan);
	
	/**
	 * 根据ID获取案件排期信息实体。
	 * 
	 * @param id 案件排期信息 ID。
	 */
	public CourtCasePlan get(String id);
	
	/**
	 * 更新案件排期信息。
	 * 
	 * @param courtCasePlan 变更的案件排期信息对象。
	 */
	public void update(CourtCasePlan courtCasePlan);
	
	/**
	 * 根据ID删除案件排期信息实体。
	 * 
	 * 
	 * @param id 案件排期信息 ID。
	 */
	public void delete(String id);
	
	/*
	 * ********************************************************************************************
	 */

	// 此方法用于查询所有排期记录用于调度时使用
	public List<CourtCasePlan> findAllMatchRecord();

	/**
	 * 根据法院ID获取排期信息
	 * 
	 * @param courtId
	 * @return
	 */
	public List<CourtCasePlan> findByCourtId(String courtId);

	/**
	 * 获取所有的排期信息(只有没被删除的)
	 * 
	 * @return @
	 */
	public List<CourtCasePlan> findAll();

	public List<CourtCasePlan> getCaseAndPlanByCourtId(List<String> courtIds,
			Date BillboardTime, Date pubTime, Date vodTime, int begin, int size);

	public int getCaseAndPlanNum(List<String> courtIds, Date BillboardTime,
			Date pubTime, Date vodTime,String vodSuccessFlag);

	Page<CourtCasePlan> findCaseAndPlanByPage(int startIndex, int pageSize,
			List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime);

	CourtCasePlan getCaseMessageByPlanId(String id);

	public List<CourtCasePlan> getCaseAndPlanByLike(List<String> courtIds,
			Date BillboardTime,Date pubTime,Date vodTime, String caseLikeName, int begin, int size,
			Date startTime, Date endTime);

	Page<CourtCasePlan> findCaseAndPlanByLike(int startIndex, int pageSize,
			List<String> courtIds, Date BillboardTime,Date pubTime,Date vodTime, String caseLikeName,
			Date startTime, Date endTime);

	int getCaseAndPlanNums(List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime,
			String caseLikeName, Date startTime, Date endTime);

	List<CourtCasePlan> getPlanByCaseId(String caseId);

	List<CourtCasePlan> findPlansByCaseNumAndStartTime(Date startTime);

	List<CourtCasePlan> findLivesByTime(Date startTime, Date endTime);
	
	String getHytPersons(String collegialId);
	
	List<CourtCasePlan> getToDatePlan();
	
	void storeState(CourtCasePlan courtCasePlan);
	 
} 