package com.ht.court.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.rest.RestResponse;
import com.hx.util.Page;
import com.ht.court.model.CourtRoom;
import com.ht.court.service.CourtRoomService;

/**
 * 法庭信息controller。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/23
 *
 */
@Controller
@RequestMapping("/courtRoom/v1")
public class CourtRoomController {
	
	private static final Logger logger=LoggerFactory.getLogger(CourtRoomController.class);
	
	@Autowired
	private CourtRoomService courtRoomService;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public @ResponseBody RestResponse<Page<CourtRoom>> index(){
		logger.debug("法庭信息查询...");
		int startIndex=0;
		int pageSize=20;
		Page<CourtRoom> page=courtRoomService.findByPage(startIndex,pageSize,null);
		logger.debug("查询到{}条记录。",page==null ? 0 : page.getList().size());
		return RestResponse.success(page);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody RestResponse<CourtRoom> get(@PathVariable String id){
		logger.debug("获取法庭信息id={}",id);
		CourtRoom courtRoom=courtRoomService.get(id);
		logger.debug("法庭信息获取成功。");
		return RestResponse.success(courtRoom);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody RestResponse<CourtRoom> create(CourtRoom courtRoom){
		logger.debug("新增法庭信息{}",courtRoom);
		courtRoomService.create(courtRoom);
		logger.debug("新增成功。");
		return RestResponse.success(courtRoom);
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	public @ResponseBody RestResponse<String> update(CourtRoom courtRoom){
		logger.debug("更新法庭信息{}",courtRoom);
		courtRoomService.update(courtRoom);
		logger.debug("更新成功。");
		return RestResponse.success("更新成功。");
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody RestResponse<String> delete(@PathVariable String id){
		logger.debug("删除法庭信息id={}",id);
		courtRoomService.delete(id);
		logger.equals("删除成功。");
		return RestResponse.success("删除成功。");
	}
}