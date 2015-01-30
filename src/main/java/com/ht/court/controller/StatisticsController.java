package com.ht.court.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.court.common.CpubEnum.VOD_STATUS;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.util.FunctionUtil;
import com.ht.court.util.SendMail;
import com.hx.auth.CollectionUtils;


/**
 * @author xinqichao
 * @说明: 统计控制器  统计各法院的排期情况
 *
 */
@Controller
@RequestMapping("/courtPub")
public class StatisticsController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StatisticsController.class);
	@Autowired
	private CourtCasePlanService courtCasePlanService;
	private String username;
	private String email;
	private String msg;
	
	public void pooo(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("有方法进来了！");
		SendMail sendMail = new SendMail(email, email, email, email);
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("编译异常：JDK版本不同 ");
			e.printStackTrace();
		}
		username = request.getParameter("username");
		email = request.getParameter("email");
		//TODO 做些其他的处理
		if(sendMail.sendMail(email, username)){
			msg ="邮件发送成功！";
		}else{
			msg = "邮件发送失败！";
		}
//		try {
//			toResponse(response, msg);
//		} catch (IOException e) {
//			System.out.println("流发送失败！");
//			e.printStackTrace();
//		}
	}
	
	
	@RequestMapping(value="statistics/home" ,method=RequestMethod.GET)
	public String chartDatas(HttpServletRequest request,Model model)  {
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<CourtCasePlan> courtCasePlan = courtCasePlanService.getCaseAndPlanByCourtId(orgIds, null, null, null, 0, 999999);
		System.out.println("************"+courtCasePlan.size()+"$$$$$$$$$$$$$$$"+courtCasePlan.get(0));
		//总排期数
		int totalPlanNum = courtCasePlanService.getCaseAndPlanNum(orgIds, null, null, null, null);
		// 点播  is not null  =>>>   成功       成功点播数
		int successVodNum = courtCasePlanService.getCaseAndPlanNum(orgIds, null, null, null, VOD_STATUS.SUNCCESS.getValue());
		// 失败数
		int failVodNum  = totalPlanNum-successVodNum;
		
		
		
		
		
		
		
		
		if(CollectionUtils.isEmpty(courtCasePlan)){
			return "/cpub/statistics/statistics";
		}
		String chartsData = FunctionUtil.generateChartsData(courtCasePlan);
		
		model.addAttribute("chartsData", chartsData);
		
		return "/cpub/statistics/statistics";
	}

	@RequestMapping(value="statistics/findBycourt" ,method=RequestMethod.GET)
	public String findChartDatas(HttpServletRequest request,Model model)  {
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		//总排期数
		int totalPlanNum = courtCasePlanService.getCaseAndPlanNum(orgIds, null, null, null, null);
		// 点播  is not null  =>>>   成功       成功点播数
		int successVodNum = courtCasePlanService.getCaseAndPlanNum(orgIds, null, null, null, VOD_STATUS.SUNCCESS.getValue());
		// 失败数
		int failVodNum  = totalPlanNum-successVodNum;
		
		
		
		 return "/cpub/statistics/statisticsByCourt";
	}
}

