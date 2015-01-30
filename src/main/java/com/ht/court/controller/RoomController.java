package com.ht.court.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.GetOrgList;
import com.fx.oss.utils.PaginateUtil;
import com.ht.court.model.CourtRoom;
import com.ht.court.service.CourtRoomService;
import com.ht.court.util.FunctionUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Controller
@RequestMapping("/courtPub")
public class RoomController {

	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	private CourtRoomService courtRoomService;
	@Autowired
	private GetOrgList  getOrgList;
	@Autowired
	private TpOrgService tpOrgService;
	
	private Date nowTime = new Date(); 
	
	/**
	 * 通过法院ID来获得法庭列表
	 * @param courtId 法院ID
	 */
	@RequestMapping(value="vod/roomList" ,method=RequestMethod.GET)
	public String roomList(HttpServletRequest request,Model model)  {
		User user = (User)FunctionUtil.getUserDetails();
		logger.info("<<<"+user.getCourtId()+"的"+user.getUsername()+"进入法庭列表>>>");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 20);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<CourtRoom> page =  courtRoomService
				.getPage(startIndex,pageSize, orgIds);
		if (page == null) {
			logger.info("<<<<<法庭页面数据为空！返回空页面>>>>>");
			return "/courtPub/vod/main";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/courtRoom/roomList";
	}
	/**
	 * 跳转到增加法庭页面
	 * @param courtId 法院ID
	 */
	@RequestMapping(value="vod/toAddRoom" ,method=RequestMethod.GET)
	public String toAddRoom(Model model){
		User user = (User)FunctionUtil.getUserDetails();
		logger.info("<<<"+user.getCourtId()+"的"+user.getUsername()+"进入新增法庭页面>>>");
		model.addAttribute("courtIds", user.getCourtId());
		return "/cpub/courtRoom/addRoom";
	}
	/**
	 * 进行增加法庭的操作
	 */
	@RequestMapping(value="vod/addRoom" ,method=RequestMethod.POST)
	public String addRoom(CourtRoom courtRoom){
	     User user = (User) FunctionUtil.getUserDetails();
	     if(StringUtils.isEmpty(courtRoom.getOrgId())){
	    	 courtRoom.setOrgId(user.getCourtId());
	     }
		 String userId =user.getId().toString();//long型转换成string
		 courtRoom.setCreateTime(nowTime);
		 courtRoom.setCreateUser(user.getUsername());
		 courtRoom.setCreateUserId(userId);
		 courtRoomService.create(courtRoom);
		logger.info("<<<"+user.getCourtId()+"的"+user.getUsername()+"新增法庭>>>" +courtRoom.getRoomName()+ new Date());
		return "redirect:roomList";
	}
	/**
	 * 进行删除法庭的操作
	 * @param id 法庭ID
	 */
	@RequestMapping(value="vod/deleteRoom" ,method=RequestMethod.GET)
	public String deleteRoom(@RequestParam String id){
		logger.debug("<<<进行删除法庭操作>>");
		courtRoomService.delete(id);
		return "redirect:roomList";
	}
	/**
	 * 跳转到更改法庭页面中
	 * @param id 法庭ID
	 */
	@RequestMapping(value="vod/toModifyRoom" ,method=RequestMethod.GET)
	public String toModifyRoom(@RequestParam String id,Model model){
		logger.debug("<<<跳转到新增法庭页面中>>>");
		CourtRoom courtRoom = courtRoomService.get(id);
		TpOrg tpOrg = tpOrgService.get(courtRoom.getOrgId());
		model.addAttribute("courtIds",tpOrg.getId());
		model.addAttribute("courtName",tpOrg .getOrgName());
		model.addAttribute("courtRoom",courtRoom );
		return "/cpub/courtRoom/modifyRoom";
	}
	/**
	 * 进行更改法庭的操作
	 * @param id 法庭ID
	 */
	@RequestMapping(value="vod/modifyRoom" ,method=RequestMethod.POST)
	public String modifyRoom(CourtRoom courtRoom){
		logger.debug("<<<进行修改法庭的操作>>>");
		 User user = (User) FunctionUtil.getUserDetails();
		 String userId =user.getId().toString();
		 courtRoom.setUpdateUser(user.getUsername());
		 courtRoom.setUpdateUserId(userId);
		 courtRoomService.update(courtRoom);
		 logger.debug("修改法庭的时间为,{}{}",courtRoom.getId() + new Date());
		return "redirect:roomList";
	}
	/**
	 * 弹出的法庭列表
	 * @param courtId 法院ID
	 */
	@RequestMapping(value="vod/rooms" ,method=RequestMethod.GET)
	public String rooms(HttpServletRequest request,Model model)  {
		logger.debug("法庭信息查询...");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 14);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		Page<CourtRoom> page = courtRoomService
				.getPage(startIndex,pageSize, orgIds);
		PaginateUtil.addPager(model, page);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/plan/roomDialog";
	}
	
	@RequestMapping(value="vod/getRoom" ,method=RequestMethod.GET)
	public String getRoomByCourt(HttpServletRequest request,Model model)  {
		logger.debug("法庭信息查询...");
		List<String> orgIds = null;
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		String courtId = request.getParameter("qp_orgId");
		String outRoom = request.getParameter("qp_outRoom");
		if("请选择法院".equals(courtId)){
			courtId = null;
		}
		if(!StringUtils.isEmpty(courtId)){
			orgIds =  new ArrayList<String>();
			 orgIds.add(courtId);
		}
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 20);
		Page<CourtRoom> page = courtRoomService
				.getPage(startIndex,pageSize, orgIds);
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		if(StringUtils.isEmpty(outRoom)){
			return "/cpub/courtRoom/getRoomsByCourt";
		}else{
			return "/cpub/plan/outGetRooms";
		}
		
	}
	
	
}
