package com.ht.court.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ht.court.vo.CasePlanVO;
import com.ht.court.vo.CasePlansVO;
import com.hx.util.Page;

/**
 * 案件公网直点播REST服务。
 * 
 * 
 * @author huangweiyong
 * @version 2.0.0.0 2014/4/9
 *
 */
public interface CasePubRestService {
	/**
	 * 获取公网直点播案件记录。
	 * 
	 * @param startDate 起始日期时间。
	 * @param endDate 结束日期时间。
	 * @param limitSize 记录数大小限制。
	 * 
	 * @return 返回案件和排期值对象列表。
	 */
	List<CasePlanVO> findPubCases(Date startDate, Date endDate, int limitSize,String courtId);
	
	/**
	 * 根据案件id和排期id获取案件记录。
	 * 
	 * @param caseId 案件id。
	 * @param planId 排期id。
	 * @return 返回案件和排期值对象。
	 */
	CasePlanVO getCasePlan(String caseId,String planId);
	
	/**
	 * 根据案件id获取案件记录。
	 * 
	 * @param caseId 案件id。
	 * 
	 * @return 返回案件和排期值对象。
	 */
	CasePlansVO getCasePlans(String caseId);
	
	/**
	 * 查询最新直播案件记录。
	 * 
	 * @param limitSize 限制查询结果条数。
	 * @return 返回直播案件列表。
	 */
	List<CasePlanVO> findLiveNow(Integer limitSize);
	
	/**
	 * 根据查询条件搜索案件。
	 * 
	 * @param conditions 查询条件。
	 * @return 返回案件列表。
	 */
	List<CasePlansVO> searchByQueryConditions(Map<String,Object> conditions);
	
	/**
	 *  根据排期ID来获取案件信息及其点播地址
	 * 
	 * @param planId 排期ID
	 * @return
	 */
	CasePlanVO findCaseAndPlanById(String planId);
	
	/**
	 *  获取直播信息并分页显示
	 * @param startIndex
	 * @param pageSize
	 * @param courtIds
	 * @param BillboardTime
	 * @param pubTime
	 * @param vodTime
	 * @return
	 */
	Page<CasePlanVO> findCaseAndPlanByPage(int startIndex, int pageSize,
			List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime);

	/**
	 *  根据分页参数获取直播列表
	 * @param courtIds
	 * @param BillboardTime
	 * @param pubTime
	 * @param vodTime
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CasePlanVO> getCaseAndPlanByCourtId(List<String> courtIds,
			Date BillboardTime, Date pubTime, Date vodTime,
			int begin, int size);
	
	/**
	 *  根据时间条件获取直播预告列表或点播记录并分页显示
	 * @param startTime
	 * @param endTime
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CasePlanVO> getLiveBillboards(Date startTime, Date endTime,Date beginTime, Date stopTime,String caseType,String courtName,int begin, int size);

	/**
	 * 得到根据时间查询的直播预告或点播记录数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int getLiveBillboardNum(Date startTime, Date endTime,Date beginTime, Date stopTime,String caseType,String courtName);
	
	/**
	 * 分页显示直播预告或点播信息列表
	 * @param startIndex
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Page<CasePlanVO> findLiveBillboardByTime(int startIndex, int pageSize,String caseType,String courtName,
			Date startTime,Date endTime,Date beginTime, Date stopTime);

	/**
	 *  search接口 模糊查询获得案件和排期的列表
	 * @param startTime
	 * @param endTime
	 * @param courtName
	 * @param caseName
	 * @param caseType
	 * @param courtId
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CasePlanVO> getCaseAndPlanBySearch
		(Date startTime, Date endTime,String courtName,String caseName,String caseType,String courtId,String trialLevel,int begin,int size);
	/**
	 *  得到search接口查询的记录总条数
	 * @param startTime
	 * @param endTime
	 * @param courtName
	 * @param caseName
	 * @param caseType
	 * @param courtId
	 * @return
	 */
	int getCaseAndPlanNumBySearch
		(Date startTime, Date endTime,String courtName,String caseName,String caseType,
				String courtId,String trialLevel);

	/**
	 * 查询接口--分页显示由条件来查询案件和排期信息
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param courtName
	 * @param caseName
	 * @param caseType
	 * @param courtId
	 * @param trialLevel
	 * @return
	 */
	Page<CasePlanVO> findCaseAndPlanBySearch
		(int startIndex, int pageSize,Date startTime, Date endTime,String courtName,
			String caseName,String caseType,String courtId,String trialLevel);
	
	/**
	 *  根据法院名称 以及庭审状态和时间获取本级院以及下级院的排期信息
	 * @param paramInt1
	 * @param paramInt2
	 * @param paramList
	 * @param paramDate1
	 * @param paramDate2
	 * @param paramDate3
	 * @return
	 */
	Page<CasePlanVO> findlocalAndInferiorPlansByCourtId
		(int paramInt1, int paramInt2, List<String> paramList, Date paramDate1, 
				Date paramDate2, Date paramDate3);

	List<String> getOrgNames(String courtName);

	List<CasePlanVO> findCaseAndPlanByCourtIds(List<String> courtIds,
			Date BillboardTime, Date pubTime, Date vodTime, int begin, int size);
}

