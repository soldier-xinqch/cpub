package com.ht.court.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.oss.sys.service.GetOrgList;
import com.fx.oss.sys.service.UserService;
import com.fx.oss.upg.AppConfig;
import com.ht.court.mapper.CourtCaseMapper;
import com.ht.court.mapper.CourtCasePlanMapper;
import com.ht.court.model.CourtCaseExample;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCasePlanExample;
import com.ht.court.model.CourtCasePlanExample.Criteria;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.util.VodUrlsUtil;
import com.ht.court.vo.CasePlanVO;
import com.ht.court.vo.CasePlansVO;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.HxCollectionUtils;
import com.hx.util.Page;

@Service("casePubRestService")
@Transactional
public class CasePubRestServiceImpl implements CasePubRestService{
	
	private static final Logger logger=LoggerFactory.getLogger(CasePubRestServiceImpl.class);
	@Autowired
	private CourtCasePlanMapper planMapper;
	@Autowired
	private CourtCaseMapper caseMapper;
	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourtCasePlanService courtCasePlanService;
	@Autowired
	private TpOrgService tpOrgService;
	@Autowired
	private GetOrgList getOrgList;

	private VodUrlsUtil vodUrls = new VodUrlsUtil();
	
	
	@Override
	public List<CasePlanVO> findPubCases(Date startDate, Date endDate,
			int limitSize,String courtId) {
		//查找排期
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		Criteria c=qbe.createCriteria();
		c.andDelFlagEqualTo(false)
			.andPlanStartTimeBetween(startDate, endDate);
		if(courtId!=null){
			c.andCourtIdEqualTo(courtId);
		}
		qbe.setLimitStart(0);
//		qbe.setLimitEnd(limitSize);
//		qbe.setOrderByClause("plan_start_time");
		List<CourtCasePlan> courtCasePlans=planMapper.selectByExample(qbe);
		return _findCaseByPlans(courtCasePlans);
//		List<CasePlanVO> vos=new ArrayList<CasePlanVO>();
//		if(CollectionUtils.isEmpty(courtCasePlans)){
//			return vos;
//		}
//		//查找案件
//		List<String> caseIds=HxCollectionUtils.extractByPropertyAsList(courtCasePlans, "caseId", String.class);
//		CourtCaseExample qbe2=new CourtCaseExample();
//		qbe2.createCriteria().andDelFlagEqualTo(false).andIdIn(caseIds);
//		List<CourtCaseWithBLOBs> cases=caseMapper.selectByExample(qbe2);
//		Map<String,CourtCaseWithBLOBs> caseMap=new HashMap<String,CourtCaseWithBLOBs>();
//		for(CourtCaseWithBLOBs courtCase:cases){
//			caseMap.put(courtCase.getId(), courtCase);
//		}
//		
//		//生成返回结果
//		for(CourtCasePlan plan:courtCasePlans){
//			vos.add(new CasePlanVO(caseMap.get(plan.getCaseId()),plan));
//		}
//		return vos;
	}

	@Override
	public CasePlanVO getCasePlan(String caseId, String planId) {
		CasePlanVO vo=new CasePlanVO();
		CourtCaseWithBLOBs courtCase=courtCaseService.get(caseId);
		vo.setCourtCase(courtCase);
		CourtCasePlan plan=courtCasePlanService.get(planId);
		vo.setCasePlan(this.setVodUrls(plan,courtCase.getCaseNum()));
		return vo;
	}

	@Override
	public CasePlansVO getCasePlans(String caseId) {
		CasePlansVO vo=new CasePlansVO();
		CourtCaseWithBLOBs courtCase=courtCaseService.get(caseId);
		vo.setCourtCase(courtCase);
		List<CourtCasePlan> plans=courtCasePlanService.getPlanByCaseId(caseId);
		List<CourtCasePlan> casePlans = new ArrayList<CourtCasePlan>();
		if(!CollectionUtils.isEmpty(plans)){
			for(CourtCasePlan plan : plans){
				casePlans.add(this.setVodUrls(plan,courtCase.getCaseNum()));
			}
		}
		
		vo.setCasePlans(casePlans);
		return vo;
	}

	@Override
	public List<CasePlanVO> findLiveNow(Integer limitSize) {
		//最新排期查找
		Date now=new Date();
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		Criteria c=qbe.createCriteria();
		c.andDelFlagEqualTo(false)
			.andPlanStartTimeLessThanOrEqualTo(now)
			.andPlanEndTimeGreaterThanOrEqualTo(now);
//		if(limitSize==null){
//			limitSize=1;
//		}
		qbe.setLimitStart(0);
//		qbe.setLimitEnd(limitSize);
//		qbe.setOrderByClause("plan_start_time");
		List<CourtCasePlan> plans=planMapper.selectByExample(qbe);
		return _findCaseByPlans(plans);
	}
	
	private List<CasePlanVO> _findCaseByPlans(List<CourtCasePlan> plans){
		List<CasePlanVO> vos=new ArrayList<CasePlanVO>();
		if(CollectionUtils.isEmpty(plans)){
			return vos;
		}
		//根据排期查找案件
		List<String> caseIds=HxCollectionUtils.extractByPropertyAsList(plans, "caseId", String.class);
		CourtCaseExample qbe2=new CourtCaseExample();
		qbe2.createCriteria().andDelFlagEqualTo(false).andIdIn(caseIds);
		List<CourtCaseWithBLOBs> cases=caseMapper.selectByExample(qbe2);
		Map<String,CourtCaseWithBLOBs> caseMap=new HashMap<String,CourtCaseWithBLOBs>();
		for(CourtCaseWithBLOBs courtCase:cases){
			caseMap.put(courtCase.getId(), courtCase);
		}
		//生成返回结果
		for(CourtCasePlan plan:plans){
			vos.add(new CasePlanVO(caseMap.get(plan.getCaseId()),this.setVodUrls(plan,(caseMap.get(plan.getCaseId()).getCaseNum()))));
		}
		return vos;
	}

	@Override
	public List<CasePlansVO> searchByQueryConditions(
			Map<String, Object> conditions) {
		String caseName=(String)conditions.get("caseName");
		if(caseName!=null){
			conditions.put("caseName", "%" + caseName + "%");
		}
		String courtName=(String)conditions.get("courtName");
		if(courtName!=null){
			conditions.put("courtName", "%" + courtName + "%");
		}
		if(emptyConds(conditions,"startTime","endTime")){
			conditions.put("orderByClause", "c.register_time desc");
		}else{
			conditions.put("orderByClause", "p.plan_start_time desc");
		}
		List<CourtCaseWithBLOBs> cases=caseMapper.searchByQueryConditions(conditions);
		return _findPlanByCases(cases);
	}
	
	private List<CasePlansVO> _findPlanByCases(List<CourtCaseWithBLOBs> cases){
		List<CasePlansVO> vos=new ArrayList<CasePlansVO>();
		if(CollectionUtils.isEmpty(cases)){
			return vos;
		}
		//根据案件查找排期
		List<String> caseIds=HxCollectionUtils.extractByPropertyAsList(cases, "id", String.class);
		CourtCasePlanExample qbe2=new CourtCasePlanExample();
		qbe2.createCriteria().andDelFlagEqualTo(false).andCaseIdIn(caseIds);
		List<CourtCasePlan> plans=planMapper.selectByExample(qbe2);
		
		//根据案件id分拣排期
		Map<String,List<CourtCasePlan>> planMap=new HashMap<String,List<CourtCasePlan>>();
		for(CourtCasePlan plan:plans){
			String caseId=plan.getCaseId();
			List<CourtCasePlan> innerPlans=planMap.get(caseId);
			if(innerPlans==null){
				innerPlans=new ArrayList<CourtCasePlan>();
				planMap.put(caseId, innerPlans);
			}
			innerPlans.add(this.setVodUrls(plan,caseMapper.selectByPrimaryKey(caseId).getCaseNum()));
		}
		//组装返回结果
		for(CourtCaseWithBLOBs c:cases){
			vos.add(new CasePlansVO(c,planMap.get(c.getId())));
		}
		return vos;
	}
	
	private boolean emptyConds(Map<String,Object> map,String... condKeys){
		boolean result=true;
		for(String condKey:condKeys){
			if(!result){
				return false;
			}
			result&=map.get(condKey)==null;
		}
		return result;
	}
	
	@Override
	public CasePlanVO findCaseAndPlanById(String planId) {
		CasePlanVO vo=new CasePlanVO();
		CourtCasePlan plans=courtCasePlanService.get(planId);
		if(plans != null){
			CourtCaseWithBLOBs courtCase=courtCaseService.get(plans.getCaseId());
			vo.setCourtCase(courtCase);
			vo.setCasePlan(this.setVodUrls(plans,courtCase.getCaseNum()));
		}
		return vo;
	}
	
	//封装的将完整点播地址插入排期信息的方法
	private CourtCasePlan setVodUrls(CourtCasePlan courtCasePlan,String caseNum){
		List<String> vodUrl = vodUrls.createVodUrl(caseNum,courtCasePlan.getPlanStartTime(),courtCasePlan.getVodAccessUrl());
		List<String> collegialNames = new ArrayList<String>(); 
		AppConfig appConfig = new AppConfig();
		courtCasePlan.setVodAccessUrl(vodUrl.toString());
		String collegialId = courtCasePlan.getCollegialId();
		String[] collegial = null;
		if(StringUtils.isEmpty(collegialId)){
			collegialId = " ";
		}else{
		   collegial = collegialId.split(" ");
		   System.out.println("接口获得合议庭成员Id： " +collegial.toString() );
		   String collegialName=null;
		   String collegialPhoto = null;
		   String host = appConfig.cfg().getString("host");
		   Integer cpubPort = appConfig.cfg().getInt("cpubPort");
		   String photoPath = appConfig.cfg().getString("photoPath");
 		   for(int i=1;i<collegial.length;i++){
				Long userId=null;
				try {
					userId = Long.valueOf(collegial[i]);
					collegialName = userService.getUserById(userId).getRealName();
					collegialPhoto = userService.getUserById(userId).getPhoto();
				} catch (NumberFormatException e1) {
					logger.debug("数据转换错误：合议庭成员ID类型转换错误！");
				}
				String photo = "";
					 try {
						photo = collegialPhoto.substring(2);
					} catch (Exception e) {
						logger.debug("collegialPhoto 为空：数组越界{}",collegialPhoto);
					} 
				collegialNames.add("http://"+host+cpubPort+photoPath+photo+"*"+collegialName);
			}
		}
		courtCasePlan.setCollegialId(collegialNames.toString());
		return courtCasePlan;
	}
	
	@Override
	public List<CasePlanVO> getCaseAndPlanByCourtId(List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime, int begin, int size)   {
		List<CourtCasePlan> courtCasePlan = planMapper.showCaseAndPlanData(courtIds, BillboardTime,pubTime,vodTime, begin, size);
		return _findCaseByPlans(courtCasePlan);
	}
	
	/**
	 * 获取直播预告，直点播案件信息
	 */
	@Override
	public Page<CasePlanVO> findCaseAndPlanByPage(int startIndex, int pageSize,
			List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime)
			  {
		int totalCount = courtCasePlanService.getCaseAndPlanNum(courtIds,BillboardTime,pubTime,vodTime);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CasePlanVO> courtCasePlans = this.getCaseAndPlanByCourtId(courtIds,BillboardTime,pubTime,vodTime, startIndex, pageSize);
		Page<CasePlanVO> page = new Page<CasePlanVO>(
				startIndex, pageSize, totalCount, courtCasePlans);
		return page;
	}

	@Override
	public List<CasePlanVO> getLiveBillboards(Date startTime, Date endTime,Date beginTime, Date stopTime,String caseType,String courtName,
			int begin, int size) {
		List<CourtCasePlan> courtCasePlans = planMapper.findLiveBillboard(startTime, endTime, beginTime, stopTime, caseType, courtName, begin, size);
		return _findCaseByPlans(courtCasePlans);
	}

	@Override
	public int getLiveBillboardNum(Date startTime, Date endTime,Date beginTime, Date stopTime,String caseType,String courtName) {
		return planMapper.getLiveBillboardNum(startTime, endTime, beginTime, stopTime, caseType, courtName);
	}

	@Override
	public Page<CasePlanVO> findLiveBillboardByTime(int startIndex,
			int pageSize,String caseType,String courtName, Date startTime, Date endTime,Date beginTime, Date stopTime) {
		int totalCount = this.getLiveBillboardNum(startTime, endTime, beginTime, stopTime, caseType, courtName);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CasePlanVO> courtCasePlans = this.getLiveBillboards(startTime, endTime, beginTime, stopTime, caseType, courtName, startIndex, pageSize);
		Page<CasePlanVO> page = new Page<CasePlanVO>(
				startIndex, pageSize, totalCount, courtCasePlans);
		return page;
	}

	@Override
	public List<CasePlanVO> getCaseAndPlanBySearch(Date startTime,
			Date endTime, String courtName, String caseName, String caseType,
			String courtId,String trialLevel, int begin, int size) {
		List<CourtCasePlan> courtCasePlan = planMapper.findCaseBySearch(startTime, endTime, courtName, caseName, caseType, courtId, trialLevel, begin, size);
		return _findCaseByPlans(courtCasePlan);
	}

	@Override
	public int getCaseAndPlanNumBySearch(Date startTime, Date endTime,
			String courtName, String caseName, String caseType, String courtId,String trialLevel) {
		return planMapper.getCaseNumBySearch(startTime, endTime, courtName, caseName, caseType, courtId, trialLevel);
	}

	@Override
	public Page<CasePlanVO> findCaseAndPlanBySearch(int startIndex,
			int pageSize, Date startTime, Date endTime, String courtName,
			String caseName, String caseType, String courtId,String trialLevel) {
		int totalCount = this.getCaseAndPlanNumBySearch(startTime, endTime, courtName, caseName, caseType, courtId, trialLevel);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CasePlanVO> courtCasePlans = this.getCaseAndPlanBySearch(startTime, endTime, courtName, caseName, caseType, courtId, trialLevel, startIndex, pageSize);
		Page<CasePlanVO> page = new Page<CasePlanVO>(
				startIndex, pageSize, totalCount, courtCasePlans);
		return page;
	}
	
	@Override
	public Page<CasePlanVO> findlocalAndInferiorPlansByCourtId(int startIndex, int pageSize, List<String> courtIds, Date BillboardTime, Date pubTime, Date vodTime){
	    int totalCount = this.courtCasePlanService.getCaseAndPlanNum(courtIds, BillboardTime, pubTime, vodTime);
	    logger.debug("列表总条数为： " + totalCount);
	    logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
	    List<CasePlanVO> courtCasePlans = this.findCaseAndPlanByCourtIds(courtIds, BillboardTime, pubTime, vodTime, startIndex, pageSize);
	    Page<CasePlanVO> page = new Page<CasePlanVO>(
	      startIndex, pageSize, totalCount, courtCasePlans);
	    return page;
	  }
	
	@Override
	public List<String> getOrgNames(String courtName) {
	    List<TpOrg> tpOrg = tpOrgService.getTpOrgsByStr(courtName);
	    return this.getOrgList.getOrgId(((TpOrg)tpOrg.get(0)).getId());
	  }
	
	@Override
	public List<CasePlanVO> findCaseAndPlanByCourtIds(List<String> courtIds, Date BillboardTime, Date pubTime, Date vodTime, int begin, int size) {
	    List<CourtCasePlan> courtCasePlans = this.courtCasePlanService.getCaseAndPlanByCourtId(courtIds, BillboardTime, pubTime, vodTime, begin, size);
	    return _findCaseByPlans(courtCasePlans);
	  }
	
}
