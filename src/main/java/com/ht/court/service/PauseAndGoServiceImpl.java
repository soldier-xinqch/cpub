package com.ht.court.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.oss.upg.AppConfig;
import com.ht.court.client.CpubHttpClient;
import com.ht.court.common.CpubEnum;
import com.ht.court.model.CourtCase;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.schedule.PushStreamToHigtest;

@Service("pauseAndGoService")
public class PauseAndGoServiceImpl implements PauseAndGoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PauseAndGoServiceImpl.class);
	@Autowired
	private CourtCaseService courtCaseService;
	@Autowired
	private CourtRoomService courtRoomService;
	@Autowired
	private CourtCasePlanService casePlanService;
	private AppConfig appConfig = new AppConfig();
	private PushStreamToHigtest pushStream = new PushStreamToHigtest();
	private String storeHost = appConfig.cfg().getString("host");
	private Integer storePort = appConfig.cfg().getInt("prot");
	private String securityFlag = appConfig.cfg().getString("securityFlag");

	@Override
	public void toPause(String id) {
		logger.info("停止推流");
		//根据排期表的一条记录，找到案件记录 
		CourtCasePlan courtCasePlan = null;
		if(StringUtils.isEmpty(id)){
			logger.info("排期id为空，无法停止推流！");
		}else{
			courtCasePlan = casePlanService.getCaseMessageByPlanId(id);
			CourtCase courtCase = courtCaseService.get(courtCasePlan.getCaseId());
			logger.info(">>>>>>"+ courtCasePlan.getPubAccessUrl());
			String response;
			try {
				response = CpubHttpClient.httpClientRequest("http",storeHost, storePort, "/handle_post_request", courtCase.getCaseNum(),
						courtRoomService.get(courtCasePlan.getCourtRoomId()).getWwwLiveUrl(),CpubEnum.STREAM_ACTION.STOP.getValue(), courtCasePlan.getPlanStartTime(), courtCasePlan.getPlanEndTime(), null, null);
				logger.debug("调用服务器的储存接口返回值={}",response);
				System.out.println("调用服务器的储存接口返回值=" + response); 
			} catch (HttpException e) {
				logger.info("HTTP异常，请检查协议，ip，端口，和地址");
				e.printStackTrace();
			} catch (IOException e) {
				logger.info("流异常，请检查流的格式，以及准确性");
				e.printStackTrace();
			}
		}
		
		}
			
	@Override
	public void toGo(String id) {
		logger.info("开始推流");
		CourtCasePlan courtCasePlan = null;
		if(StringUtils.isEmpty(id)){
			logger.info("排期id为空，无法推流！");
		}else{
			courtCasePlan = casePlanService.getCaseMessageByPlanId(id);
			CourtCase courtCase = courtCaseService.get(courtCasePlan.getCaseId());
			logger.info(">>>>>>推流地址："+ courtCasePlan.getPubAccessUrl());
			logger.info(">>>>>>推流参数："+storeHost+storePort + courtCase.getCaseNum()+ courtCasePlan.getPlanStartTime()+courtCasePlan.getPlanEndTime() );
			String response;
			try {
				response = CpubHttpClient.httpClientRequest("http",storeHost, storePort, "/handle_post_request", courtCase.getCaseNum(),
						courtRoomService.get(courtCasePlan.getCourtRoomId()).getWwwLiveUrl(),CpubEnum.STREAM_ACTION.STORE.getValue(), courtCasePlan.getPlanStartTime(), courtCasePlan.getPlanEndTime(),"100", "40");
				logger.debug("调用服务器的储存接口返回值={}",response);
				System.out.println("调用服务器的储存接口返回值=" + response); 
			} catch (HttpException e) {
				logger.info("HTTP异常，请检查协议，ip，端口，和地址");
				e.printStackTrace();
			} catch (IOException e) {
				logger.info("流异常，请检查流的格式，以及准确性");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void pushToPause(CourtCasePlan courtCasePlan) throws IOException {
		if(courtCasePlan.getAllowLiveFlag() == 2){
			pushStream.pushStream(courtCasePlan,securityFlag, "Stop");
		}
		
		
	}

	@Override
	public void pushToGo(CourtCasePlan courtCasePlan) throws IOException {
		if(courtCasePlan.getAllowLiveFlag() == 2){
			pushStream.pushStream(courtCasePlan,securityFlag, "Start");
		}
	}

}
