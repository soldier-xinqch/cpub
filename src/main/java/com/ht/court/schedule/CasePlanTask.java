package com.ht.court.schedule;

import java.util.Date;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import com.fx.oss.upg.AppConfig;
import com.ht.court.client.CpubHttpClient;
import com.ht.court.common.CpubEnum;
import com.ht.court.model.CourtCase;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.service.CourtCaseService;
import com.ht.court.service.CourtRoomService;


/**
 * @author xinqichao
 * 
 *  任务执行类
 */
@Component
public class CasePlanTask {

	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private CourtRoomService courtRoomService;
	private static Logger logger =  LoggerFactory.getLogger(CasePlanTask.class);
	private PushStreamToHigtest pushStream = new PushStreamToHigtest();
	private AppConfig appConfig = new AppConfig();
	private String storeHost = appConfig.cfg().getString("host");
	private Integer storePort = appConfig.cfg().getInt("prot");
	private String fileSize = appConfig.cfg().getString("fileSize");
	private String securityFlag = appConfig.cfg().getString("securityFlag");
	/**fileSize
	 * 执行储存
	 * @param courtCasePlan
	 * @throws Exception 
	 */
	public void execute(CourtCasePlan courtCasePlan) throws Exception{
		logger.info("开始调度");
		//验证可直播标示若为2返回true则进行向最高院推流的动作
		boolean liveFlag  = pushStream.TestAllowLiveFlag(courtCasePlan);
		if(liveFlag){
			pushStream.pushStream(courtCasePlan,securityFlag,"Start");
		}
		
		//根据排期表的一条记录，找到案件记录 
		if(courtCasePlan!=null){
			CourtCase courtCase = courtCaseService.get(courtCasePlan.getCaseId());
			logger.info("任务调度id/排期id={}",courtCasePlan.getId());
			System.out.println("任务调度id/排期id=" + courtCasePlan.getId());
			logger.info("将要发送的流地址："+ courtCasePlan.getPubAccessUrl());
			System.out.println("将要发送的流地址：=" + courtCasePlan.getPubAccessUrl());
			logger.info("调用存储接口"+"http://"+storeHost+":"+storePort);
			System.out.println("调用存储接口=" +"http://"+storeHost+":"+storePort);
			logger.info("相关的参数：案号[{}],文件大小[{}]",courtCase.getCaseNum(),fileSize);
			System.out.println("相关的参数：案号="+ courtCase.getCaseNum() +";文件块大小（M）=" + fileSize);
			Date startTime = courtCasePlan.getPlanStartTime();
			Date endTime = courtCasePlan.getPlanEndTime();
			System.out.println("相关的参数：开始时间="+startTime +";结束时间=" + endTime);
			String response = CpubHttpClient.httpClientRequest("http",storeHost, storePort, "/handle_post_request", courtCase.getCaseNum(),
					courtRoomService.get(courtCasePlan.getCourtRoomId()).getWwwLiveUrl(),CpubEnum.STREAM_ACTION.STORE.getValue(),startTime,endTime , "100", "40");
//					CpubHttpClient.httpClientRequest("http", "192.168.200.201", 8080, "/handle_post_request", 
//							courtCase.getCaseNum(), "rtmp://192.168.200.201/oflaDemo/a1", CpubEnum.STREAM_ACTION.STORE.getValue(), 
//							courtCasePlan.getPlanStartTime(), courtCasePlan.getPlanEndTime(),);
			logger.debug("调用服务器的储存接口返回值={}",response+"调用存储接口返回的状态信息");
			System.out.println("调用服务器的储存接口返回值=" + response); 
//			logger.debug("调用服务器的储存接口返回值={}",response);
		}
		
	}
	
	
}
