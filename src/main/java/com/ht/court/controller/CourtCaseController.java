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

import com.ht.court.model.CourtCase;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.service.CourtCaseService;
import com.hx.rest.RestResponse;
import com.hx.util.Page;

/**
 * 案件信息controller。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/20
 *
 */
@Controller
@RequestMapping("/courtCase/v1")
public class CourtCaseController {
	
	private static final Logger logger=LoggerFactory.getLogger(CourtCaseController.class);
	
	@Autowired
	private CourtCaseService courtCaseService;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public @ResponseBody RestResponse<Page<CourtCaseWithBLOBs>> index(){
		logger.debug("案件信息查询...");
		int startIndex=0;
		int pageSize=20;
		Page<CourtCaseWithBLOBs> page=courtCaseService.findByPage(startIndex,pageSize,null);
		logger.debug("查询到{}条记录。",page==null ? 0 : page.getList().size());
		return RestResponse.success(page);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody RestResponse<CourtCase> get(@PathVariable String id){
		logger.debug("获取案件信息id={}",id);
		CourtCase courtCase=courtCaseService.get(id);
		logger.debug("案件信息获取成功。");
		return RestResponse.success(courtCase);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody RestResponse<CourtCaseWithBLOBs> create(CourtCaseWithBLOBs courtCase){
		logger.debug("新增案件信息{}",courtCase);
		courtCaseService.create(courtCase);
		logger.debug("新增成功。");
		return RestResponse.success(courtCase);
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	public @ResponseBody RestResponse<String> update(CourtCaseWithBLOBs courtCase){
		logger.debug("更新案件信息{}",courtCase);
		courtCaseService.update(courtCase);
		logger.debug("更新成功。");
		return RestResponse.success("更新成功。");
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody RestResponse<String> delete(@PathVariable String id){
		logger.debug("删除案件信息id={}",id);
		courtCaseService.delete(id);
		logger.equals("删除成功。");
		return RestResponse.success("删除成功。");
	}
}