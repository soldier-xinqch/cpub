package com.ht.court.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.court.mapper.CourtCaseMapper;
import com.ht.court.model.CourtCaseExample;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

/**
 * 案件信息服务实现。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/20
 * 
 */
@Service("courtCaseService")
@Transactional
public class CourtCaseServiceImpl implements CourtCaseService {

	private static final Logger logger = LoggerFactory
			.getLogger(CourtCaseServiceImpl.class);

	@Autowired
	private CourtCaseMapper mapper;
	@Autowired
	private TpOrgService tpOrgService;

	@Autowired
	private CourtCasePlanService courtCasePlanService;

	public Page<CourtCaseWithBLOBs> findByPage(int startIndex, int pageSize,
			Map<String, Object> queryParams, String... orderBys) {
		CourtCaseExample qbe = new CourtCaseExample();
		qbe.createCriteria().andDelFlagEqualTo(false);
		List<CourtCaseWithBLOBs> courtCaseList = mapper.selectByExample(qbe);
		Page<CourtCaseWithBLOBs> page = new Page<CourtCaseWithBLOBs>();
		page.setResults(courtCaseList);
		return page;
	}

	public void create(CourtCaseWithBLOBs courtCase) {
		courtCase.genUUIDIfNeeded();
		mapper.insert(courtCase);
	}

	// public CourtCaseWithBLOBs get(String id) {
	// CourtCaseExample qbe=new CourtCaseExample();
	// qbe.createCriteria().andIdEqualTo(id).andDelFlagEqualTo(false);
	// List<CourtCaseWithBLOBs> list=mapper.selectByExample(qbe);
	// if(list!=null && !list.isEmpty()){
	// return list.get(0);
	// }else{
	// return null;
	// }
	// }

	@Transactional
	public CourtCaseWithBLOBs get(String id) {
		CourtCaseWithBLOBs courtCaseWithBLOBs = mapper.selectByPrimaryKey(id);
		// List<CourtCasePlan> plans = courtCasePlanService.getByCaseId(id);
		// courtCaseWithBLOBs.setPlans(plans);
		return courtCaseWithBLOBs;
	}

	public void update(CourtCaseWithBLOBs courtCase) {
		courtCase.setUpTime();
		mapper.updateByPrimaryKeyWithBLOBs(courtCase);
	}

	public void delete(String id) {
		CourtCaseWithBLOBs courtCase = this.get(id);
		courtCase.setDelFlag(true);
		courtCase.setUpTime();
		this.update(courtCase);
	}

	/*
	 * *******************************************************************************************
	 * 代码分界线-以上是自动生成代码，以下是手写代码
	 * *******************************************************************************************
	 */

	/**
	 * 获取数据的条数
	 * 
	 * @return
	 */
	@Override
	public int getAllMetaDevCount() {
		return mapper.getAllMetaDevCount();
	}

	/**
	 * 根据分页获得所有案件信息 权限中最高院将用此方法获得案件列表
	 * 
	 * @param begin
	 * @param size
	 * @return
	 */
	@Override
	public List<CourtCaseWithBLOBs> getMetaDevByPage(int begin, int size) {
		return mapper.getMetaDevByPage(begin, size);
	}

	/**
	 * 通过法院ID来获取的案件数
	 */
	@Override
	public int getTotalNumBycourtId(List<String> courtIds) {
		int totalNum = mapper.getTotalNumByCourtId(courtIds);
		return totalNum;
	}

	/**
	 * 根据法院ID获取案件列表+分页
	 */
	@Override
	public List<CourtCaseWithBLOBs> findCaseByCourtId(List<String> courtIds,
			int begin, int size) {
		logger.debug("查询案件信息的参数：法院ID={}", courtIds.toString());
		List<CourtCaseWithBLOBs> courtCase = mapper.getCaseByCourtId(courtIds,
				begin, size);
		return courtCase;
	}

	/**
	 * 案件列表+分页
	 */
	@Override
	public Page<CourtCaseWithBLOBs> findAllByPageByCourtIds(int startIndex,
			int pageSize, List<String> courtIds) {
		int totalCount = this.getTotalNumBycourtId(courtIds);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtCaseWithBLOBs> lis_dev = this.findCaseByCourtId(courtIds,
				startIndex, pageSize);
		Page<CourtCaseWithBLOBs> page = new Page<CourtCaseWithBLOBs>(
				startIndex, pageSize, totalCount, lis_dev);
		return page;
	}

	/**
	 * 条件查询所获得的案件信息数
	 */
	@Override
	public int getTotalPageByCaseNum(List<String> courtIds, String caseNum,String caseType,
			Date startTime, Date endTime) {
		return mapper.getTotalNumByCaseNum(courtIds, caseNum,caseType, startTime, endTime);
	}

	/**
	 * 条件查询所获得的案件列表，分页显示
	 */
	@Override
	public List<CourtCaseWithBLOBs> findByCaseNum(List<String> courtIds,
			String caseNum,String caseType, int begin, int size, Date startTime, Date endTime) {
		return mapper.findCourtCaseByCaseNum(courtIds, caseNum,caseType, begin, size,
				startTime, endTime);
	}

	/**
	 * 由案号及时间段来获取该法院的案件信息
	 * 
	 */
	@Override
	public Page<CourtCaseWithBLOBs> findPageByCaseNum(List<String> courtIds,
			int startIndex, int pageSize, String caseNum,String caseType, String startTime,
			String endTime) {
		logger.debug("所查询的法院是{}", courtIds);
		Date toStartTime=this.processDate(startTime);
		Date toEndTime=this.processDate(endTime);
		if(StringUtils.isEmpty(caseNum)||caseNum.equals("输入你要查询的案号……")){
			caseNum = null;
		}
		int totalCount = this.getTotalPageByCaseNum(courtIds, caseNum, caseType,
				toStartTime, toEndTime);
		logger.debug("列表总条数为{} ", totalCount);
		if (startIndex < 0) {
			startIndex = 1;
		}
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtCaseWithBLOBs> lis_dev = this.encapsulatQuery(courtIds, caseNum,caseType,
				startIndex, pageSize, startTime, endTime);
		Page<CourtCaseWithBLOBs> page = new Page<CourtCaseWithBLOBs>(
				startIndex, pageSize, totalCount, lis_dev);
		return page;
	}

	@Override
	public CourtCaseWithBLOBs getByCaseNum(String caseNum) {
		CourtCaseExample qbe = new CourtCaseExample();
		qbe.createCriteria().andCaseNumEqualTo(caseNum)
				.andDelFlagEqualTo(false);
		List<CourtCaseWithBLOBs> list = mapper.selectByExample(qbe);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}
	/*
	 *  封装处理时间的方法
	 */
	private Date processDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date tpDate = null;
		if(StringUtils.isEmpty(date)||date == ""){
			tpDate = null;
		}else{
			try {
				tpDate = sdf.parse(date);
			} catch (ParseException e) {
				logger.debug("分页调取的时间格式转换错误！");
				e.printStackTrace();
			}
		}
		return tpDate;
		
	}

	
	/**
	 * 封装条件查询的方法
	 */
	@Override
	public List<CourtCaseWithBLOBs> encapsulatQuery(List<String> courtIds,
			String caseNum,String caseType,int begin,int size, String startTime, String endTime) {
		Date nowTime = new Date();
		Date toStartTime=this.processDate(startTime);
		Date toEndTime=this.processDate(endTime);
		if (StringUtils.isEmpty(caseNum) || caseNum.equals("输入你要查询的案号……")
				|| caseNum == "") {
			caseNum = null;
		}
		if ((StringUtils.isEmpty(startTime) || startTime == "")
				&& (StringUtils.isEmpty(endTime) || endTime == "")) {
			logger.debug("案件条件查询--分页获取的时间为空！");
		} else {
				if (StringUtils.isEmpty(startTime) || startTime == ""
						&& !(StringUtils.isEmpty(endTime) || endTime == "")) {
					toStartTime = nowTime;
				}
				if (!(StringUtils.isEmpty(startTime) || startTime == "")
						&& StringUtils.isEmpty(endTime) || endTime == "") {
					toEndTime = nowTime;
				}
		}
		List<CourtCaseWithBLOBs> courtCase = this.findByCaseNum(courtIds, caseNum,caseType, begin, size, toStartTime, toEndTime);
		return courtCase;
	}
}