package com.ht.court.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.GetOrgList;
import com.fx.oss.utils.PaginateUtil;
import com.ht.court.common.CourtCaseStatusEnum.TRAIL_STATUS;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.service.CourtCaseService;
import com.ht.court.service.CourtRoomService;
import com.ht.court.service.PauseAndGoService;
import com.ht.court.util.FunctionUtil;
import com.ht.court.util.ScanFilesUtil;
import com.ht.court.util.VodUrlsUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Controller
@Transactional
@RequestMapping("/courtPub")
public class CourtPubAndVodController {

	private static final Logger logger = LoggerFactory
			.getLogger(CourtPubAndVodController.class);
	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private CourtCasePlanService courtCasePlanService;
	@Autowired
	private TpOrgService tpOrgService;
	@Autowired
	private CourtRoomService courtRoomService;
	@Autowired
	private GetOrgList getOrgList;
	@Autowired
	private PauseAndGoService pauseAndGo;
	

	private Date nowTime = new Date();

	@RequestMapping(value = "/vod/caseAndPlan", method = RequestMethod.GET)
	public String findPlansByCaseIdAndTime(Model model,
			HttpServletRequest request) {
		Timestamp BillboardTime = null;
		Timestamp pubTime = null;
		Timestamp vodTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = (User) FunctionUtil.getUserDetails();
		String courtId = user.getCourtId();
		String trailStatus = null;
		try {
			String caseNums = request.getParameter("qp_caseNum");
			String startTime = request.getParameter("qp_startTime");
			String endTime = request.getParameter("qp_endTime");
			trailStatus = request.getParameter("qp_trailStatus");
			String orgId = request.getParameter("qp_orgId");
			if("请选择法院".equals(orgId)){
				orgId = null;
			}
			Date toStartTime = null;
			Date toEndTime = null;
			if (StringUtils.isEmpty(trailStatus)) {
				return null;
			} else {
				if (trailStatus.equals("SHEDULE") || trailStatus.equals("排期")) {
					trailStatus = "排期";
					caseNums = null;
//					if (StringUtils.isEmpty(endTime)) {
//						return "getBillboardByTime"; 
//					}
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

			int startIndex = PaginateUtil.getStartIndex(request);
			int pageSize = PaginateUtil.getPageSize(request, 10);
			List<String> orgIds = null;
			if(!StringUtils.isEmpty(orgId)){
				orgIds = new ArrayList<String>();
				orgIds.add(orgId);
			}
			boolean operateFlag = false;
			if("1400555664002".equals(user.getId().toString())||"1".equals(user.getId().toString()) ){
				operateFlag = true;
			}
			List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
			Page<CourtCasePlan> page = courtCasePlanService
					.findCaseAndPlanByLike(startIndex, pageSize, orgIds,
							BillboardTime, pubTime, vodTime, caseNums,
							toStartTime, toEndTime);
			PaginateUtil.addPager(model, page);
			model.addAttribute("operateFlag", operateFlag);
			model.addAttribute("orgNames", orgNames);
			model.addAttribute("courtIds", courtId);
			model.addAttribute("trailStatus", trailStatus);
			model.addAttribute("total", page.getTotalCount());
			model.addAttribute("lis_dev", page.getList());
			System.out.println("调取直播预告数据所用时间为：");
		} catch (ParseException e) {
			logger.debug("<<<时间转换错误>>>");
			e.printStackTrace();
		}
		if (trailStatus.equals("排期")) {
			return "/cpub/plan/getBillboardByTime";
		}
		if (trailStatus.equals("开庭")) {
			 return "/cpub/live/pubByCaseNum";
		}
		if (trailStatus.equals("闭庭")) {
			 return "/cpub/vod/vodByCaseNum";
		}
		return null;
	}

	// TODO 封装模糊查询的判断方法！

	@RequestMapping(value = "/vod/pubList", method = RequestMethod.GET)
	public String pubList(HttpServletRequest request, Model model) throws SchedulerException {
		//调用查询接口并且调用存储接口
		logger.info("<<<<<进入直播！调用查找数据的接口！查找可以直播的案件！>>>>>");
		User user = (User) FunctionUtil.getUserDetails();
		Timestamp nowTimes = new Timestamp(System.currentTimeMillis());
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<CourtCasePlan> page = courtCasePlanService.findCaseAndPlanByPage(
				startIndex, pageSize, orgIds, null, nowTimes, null);
		if (page == null) {
			return "/courtPub/pub/pub";
		}
		TRAIL_STATUS trailStatus = TRAIL_STATUS.STARTCOURT;
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("trailStatus", trailStatus);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/live/pub";
	}

	/**
	 * 进行删除直播的操作
	 */
	@RequestMapping(value = "/vod/deleteLive", method = RequestMethod.GET)
	public String deleteByPlanId(@RequestParam String id,HttpServletRequest request) {
		String trailStatus = request.getParameter("qp_trailStatus");
		logger.debug(">>>>>直播预告删除操作>>>>>");
		courtCasePlanService.delete(id);
		if (trailStatus.equals("闭庭")) {
			return "redirect:vodList/";
		}else if (trailStatus.equals("开庭")) {
			return "redirect:pubList";
		}
		return null;
		
	}

	@RequestMapping(value = "/vod/toModifyLive", method = RequestMethod.GET)
	public String toModifyLive(@RequestParam String id, Model model) {
		if (StringUtils.isEmpty(id) || id == "") {
			logger.debug("<<<<得到的id为空！返回空！>>>>>");
			return "/courtPub/pub/modifyBillboard";
		} else {
			CourtCasePlan courtCasePlan = courtCasePlanService.get(id);
			CourtCaseWithBLOBs courtCase = courtCaseService.get(courtCasePlan
					.getCaseId());
			String collegialName = courtCasePlanService.getHytPersons(courtCasePlan.getCollegialId());
			logger.info("更改排期时获得的直播地址={}", courtCasePlan.getPubAccessUrl());
			String courtId = courtCasePlan.getCourtId();
			model.addAttribute("collegialName", collegialName);
			model.addAttribute("planId", id);
			model.addAttribute("courtCasePlan", courtCasePlan);
			model.addAttribute("courtCase", courtCase);
			model.addAttribute("courtIds", courtId);
		}
		return "/courtPub/pub/modifyLive";
	}

	@RequestMapping(value = "/vod/modifyLive", method = RequestMethod.POST)
	public String modifyLive(CourtCasePlan courtCasePlan ,@RequestParam String createTimed,
			@RequestParam String planStartTimed,
			@RequestParam String planEndTimed)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = (User) FunctionUtil.getUserDetails();
		String userId = user.getId().toString();
		logger.debug("更改预告信息所得到的时间：[{}]", planStartTimed, planEndTimed);
		Date toPlanStartTime = null;
		Date toPlanEndTime = null;
		Date toCreateTime = null;
		try {
			toPlanStartTime = Timestamp.valueOf(planStartTimed);
			if(!StringUtils.isEmpty(planStartTimed)&&StringUtils.isEmpty(planEndTimed)||planEndTimed == ""){
				String[] segment = planStartTimed.split(" ");
				planEndTimed = segment[0];
				DateTime sdt = new DateTime(planEndTimed).withTimeAtStartOfDay();
				toPlanEndTime = sdt.plusDays(1).plusHours(23).toDate();
				logger.debug("结束时间为空，默认时间为明天凌晨零点{}",toPlanEndTime);
			}else{
				toPlanEndTime = Timestamp.valueOf(planEndTimed);
			}
			toCreateTime = sdf.parse(createTimed);
		} catch (Exception e) {
			logger.debug("时间格式转换错误！请确认输入的时间格式");
			e.printStackTrace();
		}
		byte liveFlag;
		try {
			liveFlag = courtCasePlan.getAllowLiveFlag();
		} catch (NullPointerException e1) {
			liveFlag = 1;
		}
		courtCasePlan.setPlanStartTime(toPlanStartTime);
		courtCasePlan.setPlanEndTime(toPlanEndTime);
		courtCasePlan.setPubAccessUrl(courtRoomService.get(
				courtCasePlan.getCourtRoomId()).getWwwLiveUrl());
		courtCasePlan.setAllowLiveFlag(liveFlag);
		courtCasePlan.setCreateTime(toCreateTime);
		courtCasePlan.setUpdateUser(user.getUsername());
		courtCasePlan.setUpdateUserId(userId);
		courtCasePlanService.update(courtCasePlan);
		//检测时间是否要调用存储接口
		logger.debug("更改直播时间开始调用检测是否存储");
		courtCasePlanService.storeState(courtCasePlan);
		return "redirect:pubList";
	}

	@RequestMapping(value = "/vod/vodList", method = RequestMethod.GET)
	public String vodList(HttpServletRequest request, Model model) {
		logger.info(">>>>>>根据法庭Id得到直播预告所有信息。>>>>>");
		User user = (User) FunctionUtil.getUserDetails();
		boolean operateFlag = false;
		Timestamp nowTimes = new Timestamp(System.currentTimeMillis());
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<CourtCasePlan> page = courtCasePlanService.findCaseAndPlanByPage(
				startIndex, pageSize, orgIds, null, null, nowTimes);
		if (page == null) {
			return "/courtPub/vod/vod";
		}
		if("1400555664002".equals(user.getId().toString())||"1".equals(user.getId().toString()) ){
			operateFlag = true;
		}
		TRAIL_STATUS trailStatus = TRAIL_STATUS.ENDCOURT;
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("operateFlag", operateFlag);
		model.addAttribute("trailStatus", trailStatus);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		return "/cpub/vod/vod";
	}

	/**
	 * 跳转到直播页面
	 * 
	 * @param id
	 * @param model
	 * @return @
	 */
	@RequestMapping(value = "/vod/toPub", method = RequestMethod.GET)
	public String toPubPage(@RequestParam String id, Model model) {
		logger.debug(">>>到直播界面>>>");
		CourtCasePlan courtCasePlan = courtCasePlanService
				.getCaseMessageByPlanId(id);
		if(courtCasePlan == null){
			logger.debug("查询出来的案件排期信息为空！");
		}
		String wwwLiveUrl = courtRoomService
				.get(courtCasePlan.getCourtRoomId()).getWwwLiveUrl();
		if(StringUtils.isEmpty(wwwLiveUrl)){
			logger.debug("查询得到的法庭直播地址为空！");
		}
		model.addAttribute("wwwLiveUrl", wwwLiveUrl);
		model.addAttribute("courtCase", courtCasePlan.getCourtCase());
		model.addAttribute("courtCasePlan", courtCasePlan);
		 return "/cpub/live/pubMainPage";
	}

	/**
	 * 跳转到点播页面中
	 * 
	 * @param id
	 * @param model
	 * @return @
	 */
	@RequestMapping(value = "/vod/toVod", method = RequestMethod.GET)
	public String vodMainPage(@RequestParam String id, Model model) {
		try{
		logger.debug(">>>到点播界面>>>");
		if(StringUtils.isEmpty(id)){
			logger.debug("接收的ID为空！");
			return "/courtPub/vod/vod";
		}
		CourtCasePlan courtCasePlan = null;
		int flag = 1;
		out:for(int i=0;i<flag ;i++){
			courtCasePlan = courtCasePlanService
					.getCaseMessageByPlanId(id);
			if(StringUtils.isEmpty(courtCasePlan.getVodAccessUrl())){
				logger.debug("查询出来的案件点播路径信息为空！,重新更新记录");
				ScanFilesUtil sanFiles = new ScanFilesUtil();
				List<String> vodUrl = sanFiles.findFiles(courtCasePlan.getCourtCase().getCaseNum(),courtCasePlan.getPlanStartTime());
				courtCasePlan.setVodAccessUrl(vodUrl.toString());
				logger.debug("更新后-扫描出的文件为：{}",vodUrl.toString());
				courtCasePlanService.update(courtCasePlan);
				vodUrl.clear();
				flag=2;
				continue out;
			}
		}
		VodUrlsUtil vodUrl = new VodUrlsUtil();
		logger.debug("获得点播流地址--得到的查询参数：=[{}],[{}]",courtCasePlan.getCourtCase().getCaseNum(), courtCasePlan.getPlanStartTime()+"点播文件："+courtCasePlan.getVodAccessUrl());
		List<String> vodUrls = vodUrl.createVodUrl(courtCasePlan.getCourtCase().getCaseNum(), courtCasePlan.getPlanStartTime(), courtCasePlan.getVodAccessUrl());
		logger.info("得到的流地址：{}",vodUrls.get(0));
		int c = vodUrls.get(0).lastIndexOf("streams/");
		String rtmpUrl = vodUrls.get(0).substring(0, c + 7);
		logger.debug(">>>所用点播地址是:{}<<<" ,rtmpUrl);
		model.addAttribute("vodUrl", vodUrls);
		model.addAttribute("rtmpUrl", rtmpUrl);
		model.addAttribute("courtCase", courtCasePlan.getCourtCase());
		model.addAttribute("courtCasePlan", courtCasePlan);
		}catch(Throwable t){
			return "/courtPub/vod/vod?error=true";
		}
		 return "/cpub/vod/vodMainPage";
	}
	


//	/**
//	 * 根据法院名字模糊查询法院信息
//	 */
//	@RequestMapping(value = "/tpOrg/orgNameStr/{str}", method = RequestMethod.GET)
//	public @ResponseBody
//	RestResponse<List<TpOrg>> getTpOrgsByStr(@PathVariable String str) {
//		logger.debug("获取系统组织str={}", str);
//		List<TpOrg> tpOrgs = tpOrgService.getTpOrgsByStr(str);
//		logger.debug("系统组织获取成功。");
//		return RestResponse.success(tpOrgs);
//	}

	/**
	 * 根据案件类型查询案件信息(点播)
	 */
	// @RequestMapping(value="/courtCase/caseTypeName/{caseTypeName}",method=RequestMethod.GET)
	// public @ResponseBody RestResponse< List<CourtCaseWithBLOBs>>
	// getByCaseTypeName(@PathVariable String caseTypeName){
	// logger.debug("获取案件记录caseTypeName={}");
	// List<CourtCaseWithBLOBs>
	// courtCaseWithBLOBsList=courtCaseService.findByCaseType(caseTypeName);
	// logger.debug("获取案件记录获取成功。");
	// return RestResponse.success(courtCaseWithBLOBsList);
	// }

}
