package com.fx.oss.vod.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fx.oss.utils.Page;
import com.fx.oss.utils.PaginateUtil;
import com.fx.oss.vod.model.CourtLiveVodWithBLOBs;
import com.fx.oss.vod.service.CourtLiveVodService;

/**
 * @author Soldier
 * 向电信开放的REST借口
 *
 */
//@Controller
//@RequestMapping("/courtPub/")
public class RestInterfaceController {
	private static Logger logger = LoggerFactory.getLogger(RestInterfaceController.class);
	
	@Autowired
	private CourtLiveVodService courtLiveVodService;
	
	/*
	 * 直播列表+分页机制
	 */
//	@RequestMapping(value = "/vod/pub")
//	public String pubShowPage(HttpServletRequest request ,Model model) throws SQLException  {
//		logger.info("根据直播分页获取所有信息。");
//		Page<CourtLiveVodWithBLOBs> page = courtLiveVodService.getPage(request);
//	    PaginateUtil.addPager(model, page);
//	    model.addAttribute("total", page.getTotalCount());
//	  	model.addAttribute("lis_dev", page.getList());
//		return "pub_main";
//	}
	/*
	 * 点播列表+分页机制
	 */
//	@RequestMapping(value = "/vod/list")
//	public String showVodList(HttpServletRequest request ,Model model) throws SQLException {
//		logger.info("根据点播分页获取所有信息。");
//		Page<CourtLiveVodWithBLOBs> page = courtLiveVodService.getPage(request);
//	    PaginateUtil.addPager(model, page);
//	    logger.debug(">>"+page.getList().get(0));
//	    model.addAttribute("total", page.getTotalCount());
//	  	model.addAttribute("lis_dev", page.getList());
//		return "vod_main";
//	}
	
//}
}
