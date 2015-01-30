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
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.service.CourtCaseService;
import com.ht.court.util.FunctionUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Controller
@Transactional
@RequestMapping("/courtPub")
public class CaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(CaseController.class);

	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private TpOrgService tpOrgService;
	@Autowired
	private GetOrgList getOrgList;

	/**
	 * 案件列表页面
	 */
	@RequestMapping(value = "/vod/list", method = RequestMethod.GET)
	public String casePageByCourtId(Model model, HttpServletRequest request) {
		User user = (User) FunctionUtil.getUserDetails();
		logger.info("<<<"+user.getCourtName()+"的"+user.getUsername()+"进入案件列表>>>");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		List<String> orgIds = (List<String>) request.getSession().getAttribute(
				"orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute(
				"orgNames");
		if (CollectionUtils.isEmpty(orgIds)
				|| CollectionUtils.isEmpty(orgNames)) {
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<CourtCaseWithBLOBs> page = courtCaseService
				.findAllByPageByCourtIds(startIndex, pageSize, orgIds);
		if (page == null) {
			logger.info("<<<<<直播页面数据为空！返回空页面>>>>>");
			return "/courtPub/vod/main";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/courtCase/caseList";
	}

	/**
	 * 条件查询-得到想要得到的案件
	 */
	@RequestMapping(value = "/vod/byCaseNum", method = RequestMethod.GET)
	public String pageByCaseNum(Model model, HttpServletRequest request) {
		logger.info("<<<案件条件查询Begin>>>");
		String caseNums = request.getParameter("qp_caseNum");
		String startTime = request.getParameter("qp_startTime");
		String endTime = request.getParameter("qp_endTime");
		String outCase = request.getParameter("qp_outCase");
		String courtId = request.getParameter("qp_orgId");
		String caseType = request.getParameter("qp_casetype");
		if ("请选择法院".equals(courtId)) {
			courtId = null;
		}
		if("请选择案件类型".equals(caseType)){
			caseType= null;
		}
		logger.info("<<<案件条件查询得到的参数是：" + caseNums + "---" + startTime + "---"
				+ endTime + "---" + outCase +"--type--"+caseType+">>>");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 9);
		List<String> orgIds = null;
		if (!StringUtils.isEmpty(courtId)) {
			orgIds = new ArrayList<String>();
			orgIds.add(courtId);
		}
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute(
				"orgNames");
		Page<CourtCaseWithBLOBs> page = courtCaseService.findPageByCaseNum(
				orgIds, startIndex, pageSize, caseNums,caseType, startTime, endTime);
		if (page == null) {
			logger.info("<<<page为空！页面没有数据！>>>");
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("caseNums", caseNums);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		if (StringUtils.isEmpty(outCase)) {
			logger.debug("<<<案件条件查询>>>");
			return "/cpub/courtCase/byCaseNum";
		} else {
			logger.debug("<<<预告案件条件查询>>>");
			return "/cpub/plan/outByCaseNum";
		}
	}

	/**
	 * 跳转到案件新增页面
	 */
	@RequestMapping(value = "/vod/toAdd", method = RequestMethod.GET)
	public String toAddCourtCase(HttpServletRequest request, Model model) {
		User user = (User) FunctionUtil.getUserDetails();
		logger.info("<<<"+user.getCourtName()+"的"+user.getUsername()+"进入增加案件信息页面>>");
		TpOrg tpOrg = tpOrgService.get(user.getCourtId());
		model.addAttribute("courtIds", tpOrg.getId());
		model.addAttribute("courtName", tpOrg.getOrgName());
		return "/cpub/courtCase/addCourtCase";
	}

	/**
	 * 进行新增案件信息的操作
	 */
	@RequestMapping(value = "/vod/add", method = RequestMethod.POST)
	public String addCourtCase(CourtCaseWithBLOBs courtCase,
			@RequestParam String registerTimed) {
		User user = (User) FunctionUtil.getUserDetails();
		String userId = user.getId().toString();
		Timestamp toRegisterTime = Timestamp.valueOf(registerTimed);
		courtCase.setRegisterTime(toRegisterTime);
		courtCase.setCreateUser(user.getUsername());
		courtCase.setCreateUserId(userId);
		courtCase.setCreateTime(new Timestamp(System.currentTimeMillis()));
		courtCaseService.create(courtCase);
		logger.info("<<<"+user.getCourtName()+"的"+user.getUsername()+"增加了一个案件，案件名为："+courtCase.getCaseName()+new Date());
		return "redirect:list";
	}

	/**
	 * 进行删除案件信息的操作
	 * 
	 * @param id
	 *            案件ID
	 */
	@RequestMapping(value = "/vod/delete", method = RequestMethod.GET)
	public String deleteCaseById(@RequestParam String id, Model model) {
		logger.debug(">>进行删除案件信息操作>>");
		courtCaseService.delete(id);
		return "redirect:list";
	}

	/**
	 * 跳转到更改案件信息的页面
	 * 
	 * @param id
	 *            案件ID
	 */
	@RequestMapping(value = "/vod/toModify", method = RequestMethod.GET)
	public String toModifyCourtCase(@RequestParam String id, Model model,
			HttpServletRequest request) {
		User user = (User) FunctionUtil.getUserDetails();
		logger.info("<<<"+user.getCourtName()+"的"+user.getUsername()+"进入更改案件信息页面>>" );
		if (StringUtils.isEmpty(id) || id == "") {
			logger.debug("<<<得到的ID为空！返回到主页面>>>");
			return "redirect:list";
		}
		CourtCaseWithBLOBs courtCase = courtCaseService.get(id);
		TpOrg tpOrg = tpOrgService.get(courtCase.getCourtId());
		model.addAttribute("courtIds", user.getCourtId());
		model.addAttribute("courtCase", courtCase);
		model.addAttribute("courtName", tpOrg.getOrgName());
		 return "/cpub/courtCase/modifyCourtCase";
	}

	/**
	 * 进行更改案件信息的操作
	 */
	@RequestMapping(value = "/vod/modify", method = RequestMethod.POST)
	public String ModifyCourtCase(CourtCaseWithBLOBs courtCase,
			@RequestParam String registerTimed, @RequestParam String createTimed) {
		User user = (User) FunctionUtil.getUserDetails();
		String userId = user.getId().toString();
		// 可能会出现时间转换问题
		Timestamp toRegisterTime = Timestamp.valueOf(registerTimed);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date toCreateTime = null;
		try {
			toCreateTime = format.parse(createTimed);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		courtCase.setRegisterTime(toRegisterTime);
		courtCase.setCreateTime(toCreateTime);
		courtCase.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		courtCase.setUpdateUser(user.getUsername());
		courtCase.setUpdateUserId(userId);
		courtCaseService.update(courtCase);
		logger.info("<<<"+user.getCourtName()+"的"+user.getUsername()+"更改了案件"+courtCase.getCaseName() + new Date());
		return "redirect:list";
	}

	/**
	 * 跳转到详细信息页面中
	 * 
	 * @param id
	 *            案件ID
	 */
	@RequestMapping(value = "/vod/caseDesc", method = RequestMethod.GET)
	public String caseDesc(@RequestParam String id, Model model) {
		CourtCaseWithBLOBs courtCase = courtCaseService.get(id);
		String orgName = tpOrgService.get(courtCase.getCourtId()).getOrgName();
		model.addAttribute("courtCase", courtCase);
		model.addAttribute("courtName", orgName);
		return "/cpub/courtCase/caseDesc";
	}

	/**
	 * 弹出的案件列表
	 */
	@RequestMapping(value = "/vod/caseList", method = RequestMethod.GET)
	public String caseList(HttpServletRequest request, Model model) {
		logger.info("<<<<<根据法庭Id得到直播预告所有信息。>>>>>");
		List<String> orgIds = (List<String>) request.getSession().getAttribute(
				"orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute(
				"orgNames");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 9);
		Page<CourtCaseWithBLOBs> page = courtCaseService
				.findAllByPageByCourtIds(startIndex, pageSize, orgIds);
		if (page == null) {
			logger.info("<<<page为空！页面没有数据！>>>");
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		  return "/cpub/plan/caseDialog";
	}
	//测试用
	/*@RequestMapping(value = "/case/caseList", method = RequestMethod.GET)
	public String testPage() {
		return "/courtPub/case/main";
		
	}*/
	
}
