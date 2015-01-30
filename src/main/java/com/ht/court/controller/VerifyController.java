package com.ht.court.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.UserService;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.model.CourtRoom;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.service.CourtCaseService;
import com.ht.court.service.CourtRoomService;
import com.ht.court.service.PauseAndGoService;
import com.ht.court.util.FunctionUtil;
import com.hx.rest.RestResponse;


/**
 * @author xinqichao
 *  AJAX 交互类
 */
@Controller
public class VerifyController {
	
	private static final Logger logger = LoggerFactory.getLogger(VerifyController.class);
	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private CourtCasePlanService  courtCasePlanService;
	@Autowired
	private CourtRoomService courtRoomService;
	@Autowired
	private PauseAndGoService pauseAndGoService;
	@Autowired
	private UserService userService;
	/**
	 *  ajax交互测试法院名称是否重复
	 */
	@RequestMapping(value="/verifyCourtRoom" ,method=RequestMethod.GET)
	public @ResponseBody RestResponse<String> verifyAddAndModifyCourtRoom(HttpServletRequest request ,HttpServletResponse response) throws IOException, SQLException{
		//得到法庭名称并以法庭名称查找，检验是否有重复的法庭名称
		String roomName = request.getParameter("roomName");
		String roomId = request.getParameter("roomId");
		User user = (User) FunctionUtil.getUserDetails();
		boolean checkRoom = false;
		if (!StringUtils.isEmpty(roomId)) {
			CourtRoom room = this.courtRoomService.get(roomId);
	    	checkRoom = room.getRoomName().equalsIgnoreCase(roomName);
		}
		CourtRoom courtRoom = courtRoomService.getCourtRoomIdByCourtRoomName(roomName,user.getCourtId());
		if ((courtRoom != null) && (!StringUtils.isEmpty(roomName))) {
			if (checkRoom) {
				return RestResponse.success("SUCCESS");
			}
			return RestResponse.fail("法庭名称重复", null);
		}
		return RestResponse.success("SUCCESS");
	}
	/**
	 *  ajax交互，测试案号是否重复
	 */
	@RequestMapping(value="/verifyCase" ,method=RequestMethod.GET)
	public @ResponseBody RestResponse<String> verifyAddAndModifyCase(HttpServletRequest request ,HttpServletResponse response) throws IOException, SQLException{
		//得到案号名称并以案号名称查找，检查是否有重复的案号名称
		String caseNum = request.getParameter("caseNum");
		String caseId = request.getParameter("caseId");
		boolean checkCaseNum = false;
		if (!StringUtils.isEmpty(caseId)) {
			CourtCaseWithBLOBs nativeCaseNum = this.courtCaseService.get(caseId);
			checkCaseNum = nativeCaseNum.getCaseNum().equalsIgnoreCase(caseNum);
		}
		CourtCaseWithBLOBs courtCase = courtCaseService.getByCaseNum(caseNum);
		if(courtCase !=null){
			 if (checkCaseNum) {
				 return RestResponse.success("SUCCESS");
			 }
			 return RestResponse.fail("案号不能重复", null);
		}
		return RestResponse.success("SUCCESS");
	}
	/**
	 * ajax 交互，测试案件是否可以删除，有排期则不能删除
	 */
	@RequestMapping(value="/verifyCaseDelete" ,method=RequestMethod.GET)
	public @ResponseBody RestResponse<String> verifydeleteByCase(HttpServletRequest request ,HttpServletResponse response) throws IOException, SQLException{
		//得到案件ID 以案件ID来查询，查看此案件下是否有排期信息，若果有则不允许被删除
		String id = request.getParameter("id");
		List<CourtCasePlan> courtCasePlan = courtCasePlanService.getPlanByCaseId(id);
		if(courtCasePlan !=null){
			logger.debug("案件有排期不能删除");
			 return RestResponse.fail("案件有排期不能删除", null);
		}
		return RestResponse.success("SUCCESS");
	}
	
	/**
	 * 判断用户名是否重复
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	@RequestMapping(value="/verifyUserName" ,method=RequestMethod.GET)
	public @ResponseBody RestResponse<String> verifyUserName(HttpServletRequest request ,HttpServletResponse response) throws IOException, SQLException{
		//得到用户名信息通过用户名Id来查询用户，如果用户自己修改用户名则可以通过。如果修改他人的用户名若用户名重复则不可以提交
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		User user =null;
		User userNames = userService.getUserByUserName(userName);
		 if (!StringUtils.isEmpty(userId))
		    {
		      long id = Long.valueOf(userId).longValue();
		      user = this.userService.getUserById(Long.valueOf(id));
		      if (!StringUtils.isEmpty(userName)) {
		        if (user.getUserName().equals(userNames.getUserName())) {
		          return RestResponse.success("SUCCESS");
		        }
		        logger.debug("用户名重复");
		        return RestResponse.fail("用户名不能重复", null);
		      }
		    }
		    else {
		      if (userNames != null) {
		        logger.debug("用户名重复不能删除");
		        return RestResponse.fail("SUCCESS", null);
		      }
		      return RestResponse.success("SUCCESS");
		    }

		    return null;
	}
	

	  @RequestMapping(value={"/verifyDeleteUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public @ResponseBody RestResponse<String> verifyDeleteUser(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, SQLException
	  {
	    String userId = request.getParameter("id");
	    if (!StringUtils.isEmpty(userId))
	    {
	      long id = Long.valueOf(userId).longValue();
	      User user = this.userService.getUserById(Long.valueOf(id));
	      User localUser = (User)FunctionUtil.getUserDetails();
	      if (localUser.getCourtId().equalsIgnoreCase(user.getCourtId())) {
	        return RestResponse.fail("用户不能删除", null);
	      }
	      return RestResponse.success("SUUCCESS");
	    }

	    return null;
	  }
	  
	  @RequestMapping(value={"/verifyChooseUser"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  public @ResponseBody RestResponse<String> verifyChooseUser(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, SQLException
	  {
	    String courtId = request.getParameter("courtId");
	    if (!StringUtils.isEmpty(courtId))
	    {
	      User localUser = (User)FunctionUtil.getUserDetails();
	      if (localUser.getCourtId().equalsIgnoreCase(courtId)) {
	        return RestResponse.success("SUUCCESS");
	      }
	      return RestResponse.fail("用户不能删除", null);
	    }

	    return null;
	  }
	
	/**
	 *  暂停和继续 方法调用 
	 */
	@RequestMapping(value="/verifyPauseAndGo" ,method=RequestMethod.GET)
	public @ResponseBody RestResponse<Boolean> verifyPause(HttpServletRequest request ,HttpServletResponse response) throws IOException, SQLException{
		String casePlanId = request.getParameter("casePlanId");//排期ID
		String storeFlag = request.getParameter("storeFlag");//播放标识
		CourtCasePlan courtCasePlan = courtCasePlanService.get(casePlanId);//通过ID得到排期信息
		if(StringUtils.isEmpty(storeFlag)||StringUtils.isEmpty(casePlanId)){
			logger.debug("页面错误，ajax传回数据为空");
			return null;
		}else{
			if("PAUSE".equals(storeFlag)){
				//庭审结束，停止推流
				pauseAndGoService.toPause(casePlanId);
				DateTime nowTime = new DateTime();
				//缓冲时间为5分钟，在这五分钟里用户还可以进行操作！
				Date endTime = nowTime.plusMinutes(5).toDate();
				logger.debug("点击按钮，开始闭庭-时间为：{}",endTime);
				courtCasePlan.setPlanEndTime(endTime);
				courtCasePlanService.update(courtCasePlan);
				//若这个庭审需要推送到最高院，则停止给最高院推送直播流
				if(courtCasePlan.getAllowLiveFlag() == 2){
					pauseAndGoService.pushToPause(courtCasePlan);
				}
				return RestResponse.success(false);
			}else if("TOGO".equals(storeFlag)){
				//继续推流
				pauseAndGoService.toGo(casePlanId);
				if(courtCasePlan.getAllowLiveFlag() == 2){
					pauseAndGoService.pushToGo(courtCasePlan);
				}
				return RestResponse.success(true);
			}else if("PAUSEXIU".equals(storeFlag)){
				//暂停推流，（停止推流，可以再暂停之后点击继续可以继续推流）
				pauseAndGoService.toPause(casePlanId);
				if(courtCasePlan.getAllowLiveFlag() == 2){
					pauseAndGoService.pushToPause(courtCasePlan);
				}
				return RestResponse.success(false);
			}
		}
		return null;
	}
}
