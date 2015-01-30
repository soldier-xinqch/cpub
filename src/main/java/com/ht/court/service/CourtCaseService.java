package com.ht.court.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.court.model.CourtCaseWithBLOBs;
import com.hx.util.Page;

/**
 * 案件信息服务接口。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/20
 *
 */
public interface CourtCaseService {
	
	/**
	 * 分页获取案件信息。
	 *
	 * @return 返回分页记录。
	 */
	public Page<CourtCaseWithBLOBs> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys);
	
	/**
	 * 新建案件信息。
	 * 
	 * @param courtCase 新增的案件信息对象。
	 */
	public void create(CourtCaseWithBLOBs courtCase);
	
	/**
	 * 根据ID获取案件信息实体。
	 * 
	 * @param id 案件信息 ID。
	 */
	public CourtCaseWithBLOBs get(String id);
	
	/**
	 * 更新案件信息。
	 * 
	 * @param courtCase 变更的案件信息对象。
	 */
	public void update(CourtCaseWithBLOBs courtCase);
	
	/**
	 * 根据ID删除案件信息实体。
	 * 
	 * 
	 * @param id 案件信息 ID。
	 */
	public void delete(String id);
	
	/*
	 * *************************************************************************************
	 */
	/**
	 * 根据分页获得所有案件信息 权限中最高院将用此方法获得案件列表
	 * 
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CourtCaseWithBLOBs> getMetaDevByPage(int begin, int size) ;
	
	/**
	 * 获取所有案件数量 (指案件表中所有的未删除状态的案件)
	 * 
	 * @return
	 */
	int getAllMetaDevCount()  ;
	
	/**
	 * 通过法院ID来获取的案件数
	 * 
	 * @param courtIds
	 * @return
	 */
	int getTotalNumBycourtId(List<String> courtIds)  ;
	
	/**
	 * 根据法院ID获取数据并分页展示
	 * 
	 * @param courtIds
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CourtCaseWithBLOBs> findCaseByCourtId(List<String> courtIds,int begin,int size)  ;
	
	/**
	 *  条件查询 案件的条数
	 *   
	 * @param courtIds
	 * @param caseNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getTotalPageByCaseNum(List<String> courtIds,String caseNum,String caseType,Date startTime,Date endTime) ;
	
	/**
	 *  条件查询 案件 并分页显示
	 * 
	 * @param courtIds
	 * @param caseNum
	 * @param begin
	 * @param size
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<CourtCaseWithBLOBs> findByCaseNum(List<String> courtIds,String caseNum,String castType,int begin,int size,Date startTime,Date endTime)  ;
	
	/**
	 * 分页展示 条件查询的案件
	 * 
	 * @param courtIds
	 * @param startIndex
	 * @param pageSize
	 * @param caseNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Page<CourtCaseWithBLOBs> findPageByCaseNum(List<String> courtIds,int startIndex, int pageSize,String caseNum,String caseType,String startTime,String endTime) ;

	/**
	 * 分页显示 根据法院Id来获取的案件
	 * @param startIndex
	 * @param pageSize
	 * @param courtIds
	 * @return
	 */
	Page<CourtCaseWithBLOBs> findAllByPageByCourtIds(int startIndex, int pageSize,List<String> courtIds)  ;
	
	/**
	 * 根据案号查询案件信息
	 * 
	 * @param caseNum
	 * @return
	 */
	CourtCaseWithBLOBs getByCaseNum(String caseNum) ;
	//条件查询封装的逻辑
	public List<CourtCaseWithBLOBs> encapsulatQuery(List<String> courtIds,String caseNum,String caseType,int begin,int size,String startTime,String endTime);
	
}