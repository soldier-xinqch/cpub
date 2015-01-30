package com.ht.court.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.GetOrgList;
import com.fx.oss.utils.PaginateUtil;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.service.CourtCaseService;
import com.ht.court.service.CourtRoomService;
import com.ht.court.service.PauseAndGoService;
import com.ht.court.util.FunctionUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Controller
@RequestMapping("/courtPub")
public class BillboardController {

	private static final Logger logger = LoggerFactory
			.getLogger(BillboardController.class);
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
	
	private List<String>  getTpOrgIds(HttpServletRequest request,String courtId){
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		if(CollectionUtils.isEmpty(orgIds)){
			 orgIds = getOrgList.getOrgId(courtId);
			 request.getSession().setAttribute("orgIds", orgIds);
		}
		return orgIds;
	}
	
	private List<TpOrg>  getTpOrgNames(HttpServletRequest request,String courtId){
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgNames)){
			 orgNames = getOrgList.getOrgName(courtId);
			 request.getSession().setAttribute("orgNames", orgNames);
		}
		return orgNames;
	}

	/**
	 * 直播预告列表
	 */
	@RequestMapping(value = "/vod/pubBillboard", method = RequestMethod.GET)
	public String pubBillboardPage(HttpServletRequest request, Model model) {
		User user = (User) FunctionUtil.getUserDetails();
		logger.info("<<<<根据法庭Id得到直播预告所有信息。>>>>>");
		String courtId = user.getCourtId();
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		List<String> orgIds = this.getTpOrgIds(request, courtId);
		List<TpOrg> orgNames = this.getTpOrgNames(request, courtId);
		Page<CourtCasePlan> page = courtCasePlanService.findCaseAndPlanByPage(
				startIndex, pageSize, orgIds, new Timestamp(System.currentTimeMillis()), null, null);
		if (page == null) {
			logger.info("<<<<直播预告：page数据为空！返回空页面>>>>>");
			 return "/cpub/plan/pubBillboard";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/plan/pubBillboard";
	}

	/**
	 * 跳转到新增预告信息页面
	 */
	@RequestMapping(value = "/vod/toAddBillboard", method = RequestMethod.GET)
	public String toAddBillboard(Model model) {
		 return "/cpub/plan/addBillboard";
	}

	/**
	 * 进行新增预告信息页面中
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/vod/addBillboard", method = RequestMethod.POST)
	public String addBillboard(CourtCasePlan courtCasePlan,
			@RequestParam String planStartTimed,
			@RequestParam String planEndTimed,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		User user = (User) FunctionUtil.getUserDetails();
		String userId = user.getId().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		byte liveFlag;
		try {
			liveFlag = courtCasePlan.getAllowLiveFlag();
		} catch (NullPointerException e1) {
			liveFlag = 1;
		}
		try {
			Date toPlanStartTime = sdf.parse(planStartTimed);
			Date toPlanEndTime = null;
			if(!StringUtils.isEmpty(planEndTimed)){
				toPlanEndTime = sdf.parse(planEndTimed);
			}
//			String pubUrl = courtRoomService.get(courtCasePlan.getCourtRoomId()).getWwwLiveUrl();
			courtCasePlan.setCourtId(courtRoomService.get(
					courtCasePlan.getCourtRoomId()).getOrgId());
			courtCasePlan.setPlanStartTime(toPlanStartTime);
			courtCasePlan.setPlanEndTime(toPlanEndTime);
			courtCasePlan.setPubAccessUrl(courtRoomService.get(
					courtCasePlan.getCourtRoomId()).getWwwLiveUrl());
			logger.debug("添加排期的直播地址为={}",
					courtRoomService.get(courtCasePlan.getCourtRoomId())
							.getWwwLiveUrl());
			courtCasePlan.setAllowLiveFlag(liveFlag);
			courtCasePlan.setCreateUser(user.getUsername());
			courtCasePlan.setCreateUserId(userId);
			courtCasePlan.setCreateTime(new Timestamp(System.currentTimeMillis()));
			courtCasePlanService.create(courtCasePlan);
		} catch (ParseException e) {
			logger.debug("<<增加预告-时间格式转换错误！>>");
			e.printStackTrace();
		}
		logger.debug("新增直播的预告时间为,{}{}",userId,new Date());
		//测试是否需要存储
		courtCasePlanService.storeState(courtCasePlan);
		return "redirect:pubBillboard";
	}

	/**
	 * 进行删除直播预告的操作
	 */
	@RequestMapping(value = "/vod/deletePlan", method = RequestMethod.GET)
	public String deleteByPlanId(@RequestParam String id) {
		logger.debug("<<<<直播预告删除操作>>>>>");
		courtCasePlanService.delete(id);
		logger.debug("删除直播预告的时间为,{}{}",id+new Date());
		return "redirect:pubBillboard";
	}

	/**
	 * 跳转到更改预告信息的页面中
	 */
	@RequestMapping(value = "/vod/toModifyBillboard", method = RequestMethod.GET)
	public String toModifyBillboard(HttpServletRequest request, Model model,
			@RequestParam String id) {
		CourtCasePlan courtCasePlan = courtCasePlanService.get(id);
		CourtCaseWithBLOBs courtCase = courtCaseService.get(courtCasePlan
				.getCaseId());
		String collegialName =courtCasePlanService.getHytPersons(courtCasePlan.getCollegialId());
		model.addAttribute("collegialName", collegialName);
		model.addAttribute("planId", id);
		model.addAttribute("courtCasePlan", courtCasePlan);
		model.addAttribute("courtCase", courtCase);
		 return "/cpub/plan/modifyBillboard";
	}

	/**
	 * 进行更改预告信息的操作
	 */
	@RequestMapping(value = "/vod/modifyBillboard", method = RequestMethod.POST)
	public String modifyBillboard(CourtCasePlan courtCasePlan,
			@RequestParam String createTimed,
			@RequestParam String planStartTimed,
			@RequestParam String planEndTimed) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User user = (User) FunctionUtil.getUserDetails();
		String userId = user.getId().toString();
		byte liveFlag;
		try {
			liveFlag = courtCasePlan.getAllowLiveFlag();
		} catch (NullPointerException e1) {
			liveFlag = 1;
		}
		logger.debug("更改预告信息所得到的时间：[{}]", planStartTimed, planEndTimed);
		Date toPlanStartTime = null;
		Date toPlanEndTime = null;
		Date toCreateTime = null;
		try {
			toPlanStartTime = sdf.parse(planStartTimed);
			if(!StringUtils.isEmpty(planEndTimed)){
				toPlanEndTime = sdf.parse(planEndTimed);
			}
			toCreateTime = sdf.parse(createTimed);
		} catch (ParseException e) {
			logger.debug("时间格式转换错误！请确认输入的时间格式");
			e.printStackTrace();
		}
		courtCasePlan.setCourtId(courtRoomService.get(
				courtCasePlan.getCourtRoomId()).getOrgId());
		courtCasePlan.setPlanStartTime(toPlanStartTime);
		courtCasePlan.setPlanEndTime(toPlanEndTime);
		courtCasePlan.setPubAccessUrl(courtRoomService.get(
				courtCasePlan.getCourtRoomId()).getWwwLiveUrl());
		courtCasePlan.setAllowLiveFlag(liveFlag);
		courtCasePlan.setCreateTime(toCreateTime);
		courtCasePlan.setUpdateUser(user.getUsername());
		courtCasePlan.setUpdateUserId(userId);
		courtCasePlanService.update(courtCasePlan);
		logger.debug("更改直播预告的时间为,{}{}",userId,new Date());
		//检测时间是否要调用存储接口
		courtCasePlanService.storeState(courtCasePlan);
		
		return "redirect:pubBillboard";
	}
	
}
