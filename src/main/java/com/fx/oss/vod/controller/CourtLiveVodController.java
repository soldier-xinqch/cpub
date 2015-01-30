package com.fx.oss.vod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.oss.utils.Page;
import com.fx.oss.utils.PaginateUtil;
import com.fx.oss.vod.model.CourtLiveVod;
import com.fx.oss.vod.model.CourtLiveVodWithBLOBs;
import com.fx.oss.vod.service.CourtLiveVodService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/courtPub/")
public class CourtLiveVodController {

	private static Logger logger = LoggerFactory.getLogger(CourtLiveVodController.class);

	@Autowired
	private CourtLiveVodService courtLiveVodService;

	@RequestMapping(value = "/vod/pub")
	public String pubShowPage(HttpServletRequest request ,Model model) throws SQLException  {
		logger.info("根据直播分页获取所有信息。");
		Page<CourtLiveVodWithBLOBs> page = courtLiveVodService.getPage(request);
	    PaginateUtil.addPager(model, page);
//	    CourtLiveVodWithBLOBs ss = page.getList().get(0);
	    model.addAttribute("total", page.getTotalCount());
	  	model.addAttribute("lis_dev", page.getList());
		return "pub_main";
	}
	/*
	 * 点播列表--分页
	 */
	@RequestMapping(value = "/vod/list")
	public String showVodList(HttpServletRequest request ,Model model) throws SQLException {
		logger.info("根据点播分页获取所有信息。");
		Page<CourtLiveVodWithBLOBs> page = courtLiveVodService.getPage(request);
	    PaginateUtil.addPager(model, page);
//	    CourtLiveVodWithBLOBs ss = page.getList().get(0);
	    model.addAttribute("total", page.getTotalCount());
	  	model.addAttribute("lis_dev", page.getList());
//		List<CourtLiveVodWithBLOBs> clvList = courtLiveVodService.findAll();
//	    model.addAttribute("clvList", clvList);
		return "vod_main";
	}
	// 增加
	@RequestMapping(value = "/vod/toAdd")
	public String toAddPage(Model model) throws SQLException {
		logger.debug("跳转到新增操作的页面中>>>>>>>");
		List<CourtLiveVodWithBLOBs> clvList = courtLiveVodService.findAll();
		model.addAttribute("clvList", clvList);
		return "add";
	}
	// 增加操作处理
	@Transactional
	@RequestMapping(value = "/vod/add")
	public String addMethod(@RequestParam String courtName,
			@RequestParam String courtRoomName, @RequestParam String caseNum,
			@RequestParam String caseName, @RequestParam String caseTypeName,
			@RequestParam String accuser, @RequestParam String accuserLawer,
			@RequestParam String accused, @RequestParam String accuserdLawer,
			@RequestParam String registerTime,
			@RequestParam String trailStatus, @RequestParam String liveUrl,
			@RequestParam String vodUrl, @RequestParam String vodFilePath,
			@RequestParam String caseDesc,HttpServletRequest request) throws SQLException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(registerTime);
			System.out.println(date + ">>>>>>>>>>>");
			logger.debug("进行增加直点播信息操作+++");
			String parentCourtId = request.getParameter("parentCourtId");
			CourtLiveVodWithBLOBs courtLiveVod = new CourtLiveVodWithBLOBs();
			courtLiveVod.setId(String.valueOf(System.currentTimeMillis()));
			courtLiveVod.setParentCourtId(parentCourtId);
			courtLiveVod.setCourtId(String.valueOf(System.currentTimeMillis())+'2');
			courtLiveVod.setCourtName(courtName);
			courtLiveVod.setCourtRoomId(String.valueOf(System.currentTimeMillis())+'3');
			courtLiveVod.setCourtRoomName(courtRoomName);
			courtLiveVod.setJudgeId(String.valueOf(System.currentTimeMillis())+'4');
			courtLiveVod.setCaseNum(caseNum);
			courtLiveVod.setCaseName(caseName);
			courtLiveVod.setCaseTypeId(String.valueOf(System.currentTimeMillis())+'5');
			courtLiveVod.setCaseTypeName(caseTypeName);
			courtLiveVod.setAccuser(accuser);
			courtLiveVod.setAccuserLawer(accuserLawer);
			courtLiveVod.setAccused(accused);
			courtLiveVod.setAccuserdLawer(accuserdLawer);
			courtLiveVod.setRegisterTime(date);
			courtLiveVod.setTrailStatus(trailStatus);
			courtLiveVod.setLiveUrl(liveUrl);
			courtLiveVod.setVodUrl(vodUrl);
			courtLiveVod.setVodFilePath(vodFilePath);
			courtLiveVod.setCaseDesc(caseDesc);
			courtLiveVodService.createCourtLiveVod(courtLiveVod);
			logger.debug(">>>>>>开始转发到列表页面>>>>>>");
		} catch (ParseException e) {
			logger.debug("日期格式转换出错");
			System.out.println(e.getMessage());
		}
		return "redirect:list";
	}
	// 删除
	@Transactional
	@RequestMapping(value = "/vod/delete")
	public String delete(@RequestParam String id, Model model,
			HttpServletResponse response) throws SQLException, IOException {
		courtLiveVodService.deleteCourtLiveVodById(id);
		// response.sendRedirect("vod_main");
		return "redirect:list";
	}
	// 到更改页面---通过ID来查询相关的数据
	@RequestMapping(value = "/vod/toModify")
	public String mainPage(@RequestParam String id, Model model)
			throws SQLException {
		CourtLiveVodWithBLOBs courtLiveVod = courtLiveVodService
				.getCourtLiveVodById(id);
		logger.debug("测试getById方法是否有效" + courtLiveVod.getCourtId() + ">>>>>>");
		model.addAttribute("courtLiveVod", courtLiveVod);
		// // request.setAttribute("courtId", courtId);
		return "modify";
	}
	// 更改
	@Transactional
	@RequestMapping(value = "/vod/modify")
	public String ModifyPage(@RequestParam String id,@RequestParam String courtName,
			@RequestParam String courtRoomName, @RequestParam String caseNum,
			@RequestParam String caseName, @RequestParam String caseTypeName,
			@RequestParam String accuser, @RequestParam String accuserLawer,
			@RequestParam String accused, @RequestParam String accuserdLawer,
			@RequestParam String registerTime,
			@RequestParam String trailStatus, @RequestParam String liveUrl,
			@RequestParam String vodUrl, @RequestParam String vodFilePath,
			@RequestParam String caseDesc) throws SQLException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(registerTime);
			System.out.println(date + ">>>>>>>>>>>");
			logger.debug(">>>>>进行更改直点播信息操作>>>>>");
			CourtLiveVodWithBLOBs courtLiveVod = new CourtLiveVodWithBLOBs();
			courtLiveVod.setId(id);
			courtLiveVod.setCourtName(courtName);
			courtLiveVod.setCourtRoomName(courtRoomName);
			courtLiveVod.setCaseNum(caseNum);
			courtLiveVod.setCaseName(caseName);
			courtLiveVod.setCaseTypeName(caseTypeName);
			courtLiveVod.setAccuser(accuser);
			courtLiveVod.setAccuserLawer(accuserLawer);
			courtLiveVod.setAccused(accused);
			courtLiveVod.setAccuserdLawer(accuserdLawer);
			courtLiveVod.setRegisterTime(date);
			courtLiveVod.setTrailStatus(trailStatus);
			courtLiveVod.setLiveUrl(liveUrl);
			courtLiveVod.setVodUrl(vodUrl);
			courtLiveVod.setVodFilePath(vodFilePath);
			courtLiveVod.setCaseDesc(caseDesc);
			courtLiveVodService.updateCourtLiveVod(courtLiveVod);
			logger.debug(">>>>>>开始转发到列表页面>>>>>>");
		} catch (ParseException e) {
			logger.debug("日期格式转换出错");
			System.out.println(e.getMessage());
		}
		return "redirect:list";
	}
	/*
	 * 跳转到播放页面 其中有列表传过来一个id，这里通过id来查询相应的播放URL
	 */
	@RequestMapping(value = "/vod/toPlay")
	public String vodShow(@RequestParam String id, Model model)
			throws SQLException {
		logger.debug(">>>>>跳转到播放页面中>>>>>");
		CourtLiveVod courtLiveVod = courtLiveVodService.getCourtLiveVodById(id);
		String vodUrl = courtLiveVod.getVodUrl();
		String caseName= courtLiveVod.getCaseName();
		model.addAttribute("caseName", caseName);
		model.addAttribute("vodUrl", vodUrl);
		return "vod_toPlay";
	}
	/*根据条件查询*/
	@RequestMapping(value = "/vod/byExample")
	public String findByCase(HttpServletRequest request ,Model model) throws SQLException  {
		logger.info("根据直播分页获取所有信息。");
		Page<CourtLiveVodWithBLOBs> page = courtLiveVodService.findByPage(request);
	    PaginateUtil.addPager(model, page);
	    CourtLiveVodWithBLOBs ss = page.getList().get(0);
	    model.addAttribute("total", page.getTotalCount());
	  	model.addAttribute("lis_dev", page.getList());
		return "getCaseNumPage";
	}
	
	 
	/**
	 * 
	 * 获得法院的树形结构
	 * @return
	 */
	@RequestMapping(value = "/vod/leftTree")
	public void getTreeDataByAjax(HttpServletRequest request,HttpServletResponse response) {
		try {
//			HttpServletResponse response = ServletActionContext.getResponse();
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			Gson g = new Gson();
			// 本地法院及子法院json数据整理取得
			String nodes = getCourtJsonData();
			nodes = "".equals(nodes) ? "" : nodes.substring(0,
					nodes.length() - 1);
			String zNodes = g.toJson("[" + nodes + "]");
			System.out.println(">>>>>>>>>>>>>>"+zNodes);
			out.print(zNodes);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("获取法院树形结构失败！",e);
		}
	}
	/**
	 * 
	 * 获得法院的信息，并转换成json对象
	 * @return
	 */
	private String getCourtJsonData() throws Exception {
		System.out.println("123456789>>>>>>>>>");
		//List<Court> list = courtService.getCourt();
		List<CourtLiveVodWithBLOBs> clvList = courtLiveVodService.findAll();
		String iconOpen = "../../public/images/imgs/folder-closed.gif";
		String iconClose = "../../public/images/imgs/folder.gif";
		String icon = "../../public/images/imgs/folder.gif";
		StringBuilder sb = new StringBuilder();
		if (null != clvList) {
			for (CourtLiveVod courtdispose : clvList) {
				sb.append("{" + "id:\"" + courtdispose.getCourtId() + "\","
						+ "pId:\"" + courtdispose.getParentCourtId() + "\"," + "name:\""
						+ courtdispose.getCourtName() + "\"," + "courtIp:\""
						+ courtdispose.getCourtRoomName() + "\"," +
						 "open:"+"false"+","+ "iconOpen:\""+ iconOpen +"\","+
						"iconClose:\"" + iconClose + "\","+
						"icon:\"" + icon + "\"},");
			}
		}
		return sb.toString().trim();
	}
}
