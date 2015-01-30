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
import com.ht.court.model.CourtTrialRecording;
import com.ht.court.service.CourtTrialRecordingService;

/**
 * 录像信息controller。
 * 
 * @author xinqichao
 * @version 1.0 2014/04/09
 *
 */
@Controller
@RequestMapping("/courtTrialRecording/v1")
public class CourtTrialRecordingController {
	
	private static final Logger logger=LoggerFactory.getLogger(CourtTrialRecordingController.class);
	
	@Autowired
	private CourtTrialRecordingService courtTrialRecordingService;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public @ResponseBody RestResponse<Page<CourtTrialRecording>> index(){
		logger.debug("录像信息查询...");
		int startIndex=0;
		int pageSize=20;
		Page<CourtTrialRecording> page=courtTrialRecordingService.findByPage(startIndex,pageSize,null);
		logger.debug("查询到{}条记录。",page==null ? 0 : page.getList().size());
		return RestResponse.success(page);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody RestResponse<CourtTrialRecording> get(@PathVariable String id){
		logger.debug("获取录像信息id={}",id);
		CourtTrialRecording courtTrialRecording=courtTrialRecordingService.get(id);
		logger.debug("录像信息获取成功。");
		return RestResponse.success(courtTrialRecording);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody RestResponse<CourtTrialRecording> create(CourtTrialRecording courtTrialRecording){
		logger.debug("新增录像信息{}",courtTrialRecording);
		courtTrialRecordingService.create(courtTrialRecording);
		logger.debug("新增成功。");
		return RestResponse.success(courtTrialRecording);
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	public @ResponseBody RestResponse<String> update(CourtTrialRecording courtTrialRecording){
		logger.debug("更新录像信息{}",courtTrialRecording);
		courtTrialRecordingService.update(courtTrialRecording);
		logger.debug("更新成功。");
		return RestResponse.success("更新成功。");
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody RestResponse<String> delete(@PathVariable String id){
		logger.debug("删除录像信息id={}",id);
		courtTrialRecordingService.delete(id);
		logger.equals("删除成功。");
		return RestResponse.success("删除成功。");
	}
}