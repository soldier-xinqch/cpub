package com.ht.court.restapi;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.court.service.CasePubRestService;
import com.ht.court.vo.CasePlanVO;
import com.ht.court.vo.CasePlansVO;
import com.hx.rest.RestResponse;
import com.hx.util.Page;

/**
 * 案件REST API控制器。
 * 
 * @author huangweiyong
 * @version 2.0.0.0 2014/4/9
 *
 */
@Controller
@RequestMapping(value="/restapi/v1/case")
public class CaseRestController {
	
	@Autowired
	private CasePubRestService casePubRestService;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	
	/**
	 * 获取直播预告列表。
	 * 
	 * @param days 从今天起往后推几天。0表示当天,1表示当天到明天，2表示当天到后天，以此类推。
	 * @param limitSize 记录数限制。
	 * 
	 * @return 返回案件和排期数据列表。
	 */
	@RequestMapping(value="/live/afterDays/{days}/{startIndex}/{pageSize}")
	public @ResponseBody RestResponse<Page<CasePlanVO>> indexLiveBillboard(@PathVariable int days,
			@PathVariable int startIndex,@PathVariable int pageSize,
			@RequestParam(required=false) String caseType,
			@RequestParam(required=false) String courtName){
		if(days<0){
			days=0;
		}
		DateTime sdt = new DateTime().withTimeAtStartOfDay();
		Date edt = sdt.plusDays(days+1).toDate();
		Date nowTime = new Date();
		Page<CasePlanVO> page = casePubRestService.findLiveBillboardByTime(startIndex, pageSize,null,null, nowTime, edt, null, null);
		return RestResponse.success(page);
	}
	
	/**
	 * 获取点播列表。
	 * 
	 * @param days 从今天起往前推几天。1表示昨天，2表示前天，以此类推。
	 * @param limitSize 记录数限制。
	 * 
	 * @return 返回案件和排期数据列表。
	 */
	@RequestMapping(value="/vod/beforeDays/{days}/{startIndex}/{pageSize}")
	public @ResponseBody RestResponse<Page<CasePlanVO>> indexVOD
		(@PathVariable int days,
				@PathVariable int startIndex,@PathVariable int pageSize,
				@RequestParam(required=false) String caseType,
				@RequestParam(required=false) String courtName
				){
		if(days<=0){
			days=1;
		}
		DateTime edt = new DateTime();
		Date sdt = edt.minusDays(days-1).toDate();
		Page<CasePlanVO> page = casePubRestService.findLiveBillboardByTime(startIndex, pageSize,caseType,null, null, null, edt.toDate(), sdt);
		return RestResponse.success(page);
	}
	
	/**
	 * 根据案件id获取案件信息。
	 * 
	 * @return 返回案件和排期数据。
	 */
	@RequestMapping(value="/{id}")
	public @ResponseBody RestResponse<CasePlansVO> getCase(@PathVariable String id){
		return RestResponse.success(casePubRestService.getCasePlans(id));
	}
	
	/**
	 * 根据案件id和排期id获取案件信息。
	 * 
	 * @return 返回案件和排期数据。
	 */
	@RequestMapping(value="/{id}/plan/{planId}")
	public @ResponseBody RestResponse<CasePlanVO> getPlan(@PathVariable String id,@PathVariable String planId){
		return RestResponse.success(casePubRestService.getCasePlan(id, planId));
	}
	
	/**
	 * 根据法院获取直播列表。
	 * 
	 * @param days 从今天起往后推几天。0表示当天,1表示当天到明天，2表示当天到后天，以此类推。
	 * @param limitSize 记录数限制。
	 * 
	 * @return 返回案件和排期数据列表。
	 */
	@RequestMapping(value="/live/afterDays/{days}/court/{id}")
	public @ResponseBody RestResponse<List<CasePlanVO>> indexLivedForCourt(@PathVariable int days,@PathVariable String id, @RequestParam(required=false) Integer limitSize){
		if(limitSize==null){
			limitSize=0;
		}
		if(days<0){
			days=0;
		}
		DateTime sdt = new DateTime();
//		DateTime sdt = new DateTime().withTimeAtStartOfDay();
		DateTime edt = sdt.plusDays(days+1);
		List<CasePlanVO> vos=casePubRestService.findPubCases(sdt.toDate(), edt.toDate(), limitSize, id);
		return RestResponse.success(vos);
	}
	
	/**
	 * 根据法院获取点播列表。
	 * 
	 * @param days 从今天起往前推几天。1表示昨天，2表示前天，以此类推。
	 * @param limitSize 记录数限制。
	 * 
	 * @return 返回案件和排期数据列表。
	 */
	@RequestMapping(value="/vod/beforeDays/{days}/court/{id}")
	public @ResponseBody RestResponse<List<CasePlanVO>> indexVODForCourt(@PathVariable int days,@PathVariable String id, @RequestParam(required=false) Integer limitSize){
		if(limitSize==null){
			limitSize=0;
		}
		if(days<=0){
			days=1;
		}
		DateTime edt = new DateTime();
//		DateTime edt = new DateTime().withTimeAtStartOfDay();
		DateTime sdt = edt.minusDays(days);
		List<CasePlanVO> vos=casePubRestService.findPubCases(sdt.toDate(), edt.toDate(), limitSize, id);
		return RestResponse.success(vos);
	}
	
	/**
	 * 获取最新直播。
	 * 
	 * @param limitSize 限制最新直播条数，如果参数为空，默认返回一条。
	 * @return
	 */
	@RequestMapping(value="/liveNow/{startIndex}/{pageSize}")
	public @ResponseBody RestResponse<Page<CasePlanVO>> getLiveNow(
//			@RequestParam(required=false) Integer limitSize
			@PathVariable int startIndex,@PathVariable int pageSize,
			@RequestParam(required=false) String id
			){
//		List<CasePlanVO> vos=casePubRestService.findLiveNow(limitSize);
//		List<String> orgIds = new ArrayList<String>(); 
//		orgIds.add(id);
		Page<CasePlanVO> page = casePubRestService.findCaseAndPlanByPage(
				startIndex, pageSize, null,null, new Timestamp(System.currentTimeMillis()), null);
		return RestResponse.success(page);
	}
	
	/**
	 * 根据条件查询案件。
	 * 
	 * @param startTime 庭审直播开始时间。
	 * @param endTime 庭审直播结束时间。
	 * @param courtId 法院id。
	 * @param courtName 法院名称，支持模糊查询。
	 * @param caseName 案件名称，支持模糊查询。
	 * @param caseType 案件类型。
	 * @param startIndex 结果起始索引。
	 * @param pageSize 结果大小。
	 * 
	 * @return 返回案件排期联合对象的rest响应。
	 */
	@RequestMapping(value="/search/{startIndex}/{pageSize}")
	public @ResponseBody RestResponse<Page<CasePlanVO>> search(
			@RequestParam(required=false) String startTime,
			@RequestParam(required=false) String endTime,
			@RequestParam(required=false) String courtId,
			@RequestParam(required=false) String trialLevel,
			@RequestParam(required=false) String courtName,
			@RequestParam(required=false) String caseName,
			@RequestParam(required=false) String caseType,
			@PathVariable int startIndex,@PathVariable int pageSize
			){
		SimpleDateFormat forMat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date toStartTime = null;
		Date toEndTime = null;
		if((!StringUtils.isEmpty(startTime))&&(!StringUtils.isEmpty(endTime))){
			try {
				toStartTime = forMat.parse(startTime);
				toEndTime = forMat.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Page<CasePlanVO> page = casePubRestService.findCaseAndPlanBySearch
			(startIndex, pageSize, toStartTime, toEndTime, courtName, caseName, caseType,
					courtId, trialLevel);
		return RestResponse.success(page);
	}
	
	/**
	 *  根据排期ID来查询该排期的案件信息及点播地址
	 *  
	 * @param planId  排期ID
	 * @return
	 */
	@RequestMapping(value="/getVodUrls/{planId}")
	public @ResponseBody RestResponse<CasePlanVO> getVodUrls(@PathVariable String planId){
		CasePlanVO vo =null;
		if(!StringUtils.isEmpty(planId)){
			vo = casePubRestService.findCaseAndPlanById(planId);
		}
		return RestResponse.success(vo);
	}
	
	/**
	 *  根据法院名称以及状态获取本级院以及下级院的排期信息
	 * @param courtName
	 * @param trialStatus
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping({"/getPlansByCourtIds/{courtName}/{trialStatus}/{startIndex}/{pageSize}"})
	public @ResponseBody RestResponse<Page<CasePlanVO>> getAllplansByCourtIds(@PathVariable String courtName, @PathVariable String trialStatus, @PathVariable int startIndex, @PathVariable int pageSize) {
	    Date BillboardTime = null;
	    Date pubTime = null;
	    Date vodTime = null;
	    Page<CasePlanVO> page = null;
	    List<String> orgIds = this.casePubRestService.getOrgNames(courtName);
	    if ("PLAN".equalsIgnoreCase(trialStatus)) {
	      BillboardTime = new Timestamp(System.currentTimeMillis());
	      page = this.casePubRestService.findlocalAndInferiorPlansByCourtId(startIndex, pageSize, orgIds, BillboardTime, pubTime, vodTime);
	    } else if ("VOD".equalsIgnoreCase(trialStatus)) {
	      vodTime = new Timestamp(System.currentTimeMillis());
	      page = this.casePubRestService.findlocalAndInferiorPlansByCourtId(startIndex, pageSize, orgIds, BillboardTime, pubTime, vodTime);
	    } else if ("LIVE".equalsIgnoreCase(trialStatus)) {
	      pubTime = new Timestamp(System.currentTimeMillis());
	      page = this.casePubRestService.findlocalAndInferiorPlansByCourtId(startIndex, pageSize, orgIds, BillboardTime, pubTime, vodTime);
	    }
	    return RestResponse.success(page);
	  }
	
	
}
