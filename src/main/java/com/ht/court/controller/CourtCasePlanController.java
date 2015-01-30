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
import com.ht.court.model.CourtCasePlan;
import com.ht.court.service.CourtCasePlanService;

/**
 * 案件排期信息controller。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/30
 *
 */
@Controller
@RequestMapping("/courtCasePlan/v1")
public class CourtCasePlanController {
	
	private static final Logger logger=LoggerFactory.getLogger(CourtCasePlanController.class);
	
	@Autowired
	private CourtCasePlanService courtCasePlanService;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public @ResponseBody RestResponse<Page<CourtCasePlan>> index(){
		logger.debug("案件排期信息查询...");
		int startIndex=0;
		int pageSize=20;
		Page<CourtCasePlan> page=courtCasePlanService.findByPage(startIndex,pageSize,null);
		logger.debug("查询到{}条记录。",page==null ? 0 : page.getList().size());
		return RestResponse.success(page);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody RestResponse<CourtCasePlan> get(@PathVariable String id){
		logger.debug("获取案件排期信息id={}",id);
		CourtCasePlan courtCasePlan=courtCasePlanService.get(id);
		logger.debug("案件排期信息获取成功。");
		return RestResponse.success(courtCasePlan);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody RestResponse<CourtCasePlan> create(CourtCasePlan courtCasePlan){
		logger.debug("新增案件排期信息{}",courtCasePlan);
		courtCasePlanService.create(courtCasePlan);
		logger.debug("新增成功。");
		return RestResponse.success(courtCasePlan);
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	public @ResponseBody RestResponse<String> update(CourtCasePlan courtCasePlan){
		logger.debug("更新案件排期信息{}",courtCasePlan);
		courtCasePlanService.update(courtCasePlan);
		logger.debug("更新成功。");
		return RestResponse.success("更新成功。");
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody RestResponse<String> delete(@PathVariable String id){
		logger.debug("删除案件排期信息id={}",id);
		courtCasePlanService.delete(id);
		logger.equals("删除成功。");
		return RestResponse.success("删除成功。");
	}
}