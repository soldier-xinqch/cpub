package com.ht.court.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.UserService;
import com.ht.court.mapper.CourtCasePlanMapper;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCasePlanExample;
import com.ht.court.model.CourtTrialRecording;
import com.ht.court.util.DateUtil;
import com.hx.util.Page;


/**
 * 案件排期信息服务实现。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/30
 *
 */
@Service("courtCasePlanService")
@Transactional
public class CourtCasePlanServiceImpl implements CourtCasePlanService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CourtCasePlanServiceImpl.class);
	@Autowired
	private CourtCasePlanMapper mapper;
	@Autowired
	private UserService userService;
	private DateUtil dateUtil;
	
	@Autowired
	private CourtTrialRecordingService courtTrialRecordingService;
	
	@Autowired
	private PauseAndGoService pauseAndGo;

	public Page<CourtCasePlan> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys){
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false);
		List<CourtCasePlan> courtCasePlanList=mapper.selectByExample(qbe);
		Page<CourtCasePlan> page=new Page<CourtCasePlan>();
		page.setResults(courtCasePlanList);
		return page;
	}

	public void create(CourtCasePlan courtCasePlan) {
		courtCasePlan.genUUIDIfNeeded();
		this.setTrialLevel(courtCasePlan);
		mapper.insert(courtCasePlan);
	}

	private CourtCasePlan setTrialLevel(CourtCasePlan courtCasePlan){
		List<CourtCasePlan> courtCasePlans = this.getPlanByCaseId(courtCasePlan.getCaseId());
		if(CollectionUtils.isEmpty(courtCasePlans)){
			logger.debug("此案件没有排过期");
			courtCasePlan.setTrialLevel(1+"审");
		}else{
			courtCasePlan.setTrialLevel(courtCasePlans.size()+1+"审");
		}
		//计划开庭时间以及计划闭庭时间的时间转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String planStartTimed = sdf.format(courtCasePlan.getPlanStartTime());
		Date toPlanEndTime = courtCasePlan.getPlanEndTime();
		if(courtCasePlan.getPlanEndTime() == null){
			String planEndTimed = null;
			if(!StringUtils.isEmpty(planStartTimed)&&StringUtils.isEmpty(planEndTimed)||planEndTimed == ""){
				String[] segment = planStartTimed.split(" ");
				planEndTimed = segment[0];
				DateTime sdt = new DateTime(planEndTimed);
				toPlanEndTime = sdt.plusDays(1).plusHours(23).toDate();
				logger.debug("结束时间为空，默认时间为明天凌晨零点{}",toPlanEndTime);
			}
			courtCasePlan.setPlanEndTime(toPlanEndTime);
		}
		
		return courtCasePlan;
		
	}
	
	public CourtCasePlan get(String id) {
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andIdEqualTo(id).andDelFlagEqualTo(false);
		List<CourtCasePlan> list=mapper.selectByExample(qbe);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	public void update(CourtCasePlan courtCasePlan) {
		courtCasePlan.setUpTime();
		mapper.updateByPrimaryKey(courtCasePlan);
	}

	public void delete(String id) {
		CourtCasePlan courtCasePlan=this.get(id);
		courtCasePlan.setDelFlag(true);
		courtCasePlan.setUpTime();
		this.update(courtCasePlan);
	}

	public List<CourtCasePlan> findAll(){
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false);
		return mapper.selectByExample(qbe);
	}
	
	
	//此方法用于查询所有排期记录，用于调度时使用
	public List<CourtCasePlan> findAllMatchRecord(){
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andPlanEndTimeGreaterThan(new Date());
		return mapper.selectByExample(qbe);
	}

	@Override
	public List<CourtCasePlan> findByCourtId(String courtId)
			  {
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andCourtIdEqualTo(courtId);
		List<CourtCasePlan> list=mapper.selectByExample(qbe);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		for(CourtCasePlan courtCasePlan :list){
			List<CourtTrialRecording> courtTrialRecordings = 
					courtTrialRecordingService.getByCaseIdAndPlanId(courtCasePlan.getCaseId(),courtCasePlan.getId());
			courtCasePlan.setCourtTrialRecordings(courtTrialRecordings);
		}
		return list;
	}
	@Override
	public List<CourtCasePlan> getPlanByCaseId(String caseId)
			  {
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andCaseIdEqualTo(caseId);
		List<CourtCasePlan> list=mapper.selectByExample(qbe);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		for(CourtCasePlan courtCasePlan :list){
			List<CourtTrialRecording> courtTrialRecordings = 
					courtTrialRecordingService.getByCaseIdAndPlanId(courtCasePlan.getCaseId(),courtCasePlan.getId());
			courtCasePlan.setCourtTrialRecordings(courtTrialRecordings);
		}
		return list;
	}

	/**
	 *  根据法院Id获取案件和排期的所有数据
	 */
	@Override
	public List<CourtCasePlan> getCaseAndPlanByCourtId(List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime, int begin, int size)   {
		List<CourtCasePlan> courtCasePlan = mapper.showCaseAndPlanData(courtIds, BillboardTime,pubTime,vodTime, begin, size);
		return courtCasePlan;
	}

	/**
	 * 获取根据法院ID得到的案件及排期的所有数据条数
	 */
	@Override
	public  int getCaseAndPlanNum(List<String> courtIds, Date BillboardTime,
			Date pubTime, Date vodTime,String vodSuccessFlag)
			  {
		return mapper.getCaseAndPlanNum(courtIds,BillboardTime,pubTime,vodTime, vodSuccessFlag);
	}

	@Override
	public Page<CourtCasePlan> findCaseAndPlanByPage(int startIndex, int pageSize,
			List<String> courtIds,Date BillboardTime,Date pubTime,Date vodTime)
			  {
		int totalCount = this.getCaseAndPlanNum(courtIds,BillboardTime,pubTime,vodTime, null);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtCasePlan> courtCasePlans = this.getCaseAndPlanByCourtId(courtIds,BillboardTime,pubTime,vodTime, startIndex, pageSize);
		Page<CourtCasePlan> page = new Page<CourtCasePlan>(
				startIndex, pageSize, totalCount, courtCasePlans);
		return page;
	}

	@Override
	public CourtCasePlan getCaseMessageByPlanId(String id)
			  {
		CourtCasePlan courtCasePlan = mapper.getCaseMessageByPlanId(id);
		return courtCasePlan;
	}

	@Override
	public List<CourtCasePlan> getCaseAndPlanByLike(List<String> courtIds,
			Date BillboardTime,Date pubTime,Date vodTime, String caseLikeName, int begin, int size,
			Date startTime, Date endTime)   {
		List<CourtCasePlan> courtCasePlan = mapper.showCaseAndPlanDataByLike
				(courtIds,BillboardTime,  pubTime,  vodTime, caseLikeName, begin, size, startTime, endTime);
		return courtCasePlan;
	}	
	@Override
	public Page<CourtCasePlan> findCaseAndPlanByLike(int startIndex, int pageSize ,List<String> courtIds, 
			Date BillboardTime,Date pubTime,Date vodTime,String caseLikeName,Date startTime,Date endTime)
			  {
		int totalCount = this.getCaseAndPlanNums(courtIds,   BillboardTime,  pubTime,  vodTime, caseLikeName, startTime, endTime);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtCasePlan> courtCasePlans = this.getCaseAndPlanByLike
				(courtIds,   BillboardTime,  pubTime,  vodTime, caseLikeName, startIndex, pageSize, startTime, endTime);
		Page<CourtCasePlan> page = new Page<CourtCasePlan>(
				startIndex, pageSize, totalCount, courtCasePlans);
		return page;
	}

	@Override
	public int getCaseAndPlanNums(List<String> courtIds, Date BillboardTime,Date pubTime,Date vodTime,
			String caseLikeName, Date startTime, Date endTime)
			  {
		return mapper.getCaseAndPlanNums(courtIds,BillboardTime,  pubTime,  vodTime, caseLikeName, startTime, endTime);
	}

	@Override
	public List<CourtCasePlan> findPlansByCaseNumAndStartTime(Date vodTime) {
		return mapper.getCaseNumAndStartTime(vodTime);
	}

	@Override
	public List<CourtCasePlan> findLivesByTime(Date startTime, Date endTime)
	  {
	    return this.mapper.getliveByTime(startTime, endTime);
	  }
	
	@Override
	public String getHytPersons(String collegialId){
		String[] collegial = null;
		if(StringUtils.isEmpty(collegialId)){
			collegialId = " ";
		}else{
		   collegial = collegialId.split(" ");
		   String collegialNames = "";
		   String collegialName=null;
		   for(int i=1;i<collegial.length;i++){
				Long userId = Long.valueOf(collegial[i]);
				User user;
				try {
					user = userService.getUserById(userId);
					collegialName = user.getRealName();
				} catch (NullPointerException e) {
					logger.debug("用户没有找到，可能已经被删除");
				}
				if(StringUtils.isEmpty(collegialName)){
					collegialName=" ";
				}
				collegialNames = collegialNames+" "+collegialName;
			}
		   return collegialNames;
		}
		return collegialId;
		
	}

	private List<CourtCasePlan> casess(String trailStatus,String caseNums,String startTime ,String endTime){
		
		Timestamp BillboardTime = null;
		Timestamp pubTime = null;
		Timestamp vodTime = null;
		Date toStartTime = null;
		Date toEndTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		try {
			if (StringUtils.isEmpty(trailStatus)) {
				return null;
			} else {
				if (trailStatus.equals("SHEDULE") || trailStatus.equals("排期")) {
					trailStatus = "排期";
					caseNums = null;
//				if (StringUtils.isEmpty(endTime)) {
//					return "getBillboardByTime"; 
//				}
					BillboardTime = new Timestamp(System.currentTimeMillis());
					if ((StringUtils.isEmpty(startTime) || startTime == "")
							&& (StringUtils.isEmpty(endTime) || endTime == "")) {
						toStartTime = null;
						toEndTime = null;
					} else {
						if (StringUtils.isEmpty(startTime)
								|| startTime == ""
								&& !(StringUtils.isEmpty(endTime) || endTime == "")) {
							toStartTime = nowTime;
							toEndTime = sdf.parse(endTime);
						}
						if (!(StringUtils.isEmpty(startTime) || startTime == "")
								&& StringUtils.isEmpty(endTime)
								|| endTime == "") {
							toStartTime = sdf.parse(startTime);
							toEndTime = nowTime;
						}
						if (!StringUtils.isEmpty(startTime)
								&& !StringUtils.isEmpty(endTime)) {
							toStartTime = sdf.parse(startTime);
							toEndTime = sdf.parse(endTime);
						}
					}
				}
				if (trailStatus.equals("STARTCOURT")
						|| trailStatus.equals("开庭")) {
					trailStatus = "开庭";
					pubTime = new Timestamp(System.currentTimeMillis());
					if (StringUtils.isEmpty(caseNums)
							|| caseNums.equals("输入你要查询的案号……") || caseNums == "") {
						caseNums = null;
					}
					if ((StringUtils.isEmpty(startTime) || startTime == "")
							&& (StringUtils.isEmpty(endTime) || endTime == "")) {
						toStartTime = null;
						toEndTime = null;
					} else {
						if (StringUtils.isEmpty(startTime)
								|| startTime == ""
								&& !(StringUtils.isEmpty(endTime) || endTime == "")) {
							toStartTime = nowTime;
							toEndTime = sdf.parse(endTime);
						}
						if (!(StringUtils.isEmpty(startTime) || startTime == "")
								&& StringUtils.isEmpty(endTime)
								|| endTime == "") {
							toStartTime = sdf.parse(startTime);
							toEndTime = nowTime;
						}
						if (!StringUtils.isEmpty(startTime)
								&& !StringUtils.isEmpty(endTime)) {
							toStartTime = sdf.parse(startTime);
							toEndTime = sdf.parse(endTime);
						}
					}
				}
				if (trailStatus.equals("ENDCOURT") || trailStatus.equals("闭庭")) {
					trailStatus = "闭庭";
					vodTime = new Timestamp(System.currentTimeMillis());
					if (StringUtils.isEmpty(caseNums)
							|| caseNums.equals("输入你要查询的案号……") || caseNums == "") {
						caseNums = null;
					}
					if ((StringUtils.isEmpty(startTime) || startTime == "")
							&& (StringUtils.isEmpty(endTime) || endTime == "")) {
						toStartTime = null;
						toEndTime = null;
					} else {
						if (StringUtils.isEmpty(startTime)
								|| startTime == ""
								&& !(StringUtils.isEmpty(endTime) || endTime == "")) {
							toStartTime = nowTime;
							toEndTime = sdf.parse(endTime);
						}
						if (!(StringUtils.isEmpty(startTime) || startTime == "")
								&& StringUtils.isEmpty(endTime)
								|| endTime == "") {
							toStartTime = sdf.parse(startTime);
							toEndTime = nowTime;
						}
						if (!StringUtils.isEmpty(startTime)
								&& !StringUtils.isEmpty(endTime)) {
							toStartTime = sdf.parse(startTime);
							toEndTime = sdf.parse(endTime);
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<CourtCasePlan> getToDatePlan() {
		DateTime sdt = new DateTime().withTimeAtStartOfDay().plusHours(6);
		Date edt = sdt.plusDays(1).toDate();
		logger.debug("显示查看当前天排期数据的查询参数：{},{}",sdt.toDate(),edt);
		
		CourtCasePlanExample qbe=new CourtCasePlanExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andPlanEndTimeGreaterThan(sdt.toDate()).andPlanEndTimeLessThan(edt);
		List<CourtCasePlan> courtCasePlanList=mapper.selectByExample(qbe);
		
		return courtCasePlanList;
	}

	@Override
	public void storeState(CourtCasePlan courtCasePlan) {
		logger.debug("进入检测是否存储接口");
		dateUtil = new DateUtil();
		long nowTimeNum = new Date().getTime();
		long createTimeNum = courtCasePlan.getPlanStartTime().getTime();
		long endTimeNum = courtCasePlan.getPlanEndTime().getTime();
		logger.debug("开始时间与当前时间的时间差：,{}--和--{}",(nowTimeNum-createTimeNum),(endTimeNum - nowTimeNum)+"day："+new Date().getDay()+"id->day:"+courtCasePlan.getPlanStartTime().getDay());
		System.out.println(">>>?>>>" + dateUtil.getDay(new Date()));
		if(dateUtil.getDay(new Date()).equals(dateUtil.getDay(courtCasePlan.getPlanStartTime()))){
			if((nowTimeNum-createTimeNum<10*60*1000) &&(endTimeNum - nowTimeNum >0) ){
				logger.debug("开始存");
				pauseAndGo.toGo(courtCasePlan.getId());
			}
			if(endTimeNum - nowTimeNum <0){
				logger.debug("停止存");
				pauseAndGo.toPause(courtCasePlan.getId());
			}
		}
	
	}
	
}