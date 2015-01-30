package com.ht.court.schedule;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fx.oss.upg.AppConfig;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCaseWithBLOBs;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.service.CourtCaseService;
import com.ht.court.service.PauseAndGoService;
import com.ht.court.util.ScanFilesUtil;
import com.hx.auth.CollectionUtils;


/**
 * @author xinqichao 
 * 
 *  定时查询是否需要停止存储以及停止推流
 */
@Component
public class StopStore{
	private static final Logger logger=LoggerFactory.getLogger(FindLiveData.class);
	
	@Autowired
	private CourtCasePlanService casePlanService;
	@Autowired
	private CourtCaseService caseService;
	
	@Autowired
	private PauseAndGoService pasuseAndGo;
	private PushStreamToHigtest pushStream = new PushStreamToHigtest();
	private AppConfig appConfig = new AppConfig();
	private String securityFlag = appConfig.cfg().getString("securityFlag");
	private Map<String,String> stopPlanId = new HashMap<String, String>();
	
	
	@Scheduled(cron="0 0/2 * * * ? ")
	public synchronized void execute() throws IOException {
		System.out.println("34567890oikjedsu890pokwefiucxoklmnfekjvcxlnm");
		List<CourtCasePlan> courtCasePlans = casePlanService.getToDatePlan();
		if(!CollectionUtils.isEmpty(courtCasePlans)){
			logger.info("@stop-查询出来的当天的案件直点播条数及可以结束的案件数{}",courtCasePlans.size()+"-----"+new Date());
			for(CourtCasePlan courtCaseplan : courtCasePlans){
				if(!StringUtils.isEmpty(courtCaseplan.getId())){
					if(courtCaseplan.getPlanEndTime() != null){
						CourtCaseWithBLOBs courtCase = caseService.get(courtCaseplan.getCaseId());
						long nowTime = new Date().getTime();
						long endTimeNum = courtCaseplan.getPlanEndTime().getTime();
						if(((nowTime-endTimeNum <(10*60*1000)&&nowTime-endTimeNum >0)
								||(-(10*60*1000)<(nowTime-endTimeNum)&&nowTime-endTimeNum <0))){
							//停止向最高院推流
							if(courtCaseplan.getAllowLiveFlag() == 2){
								pushStream.pushStream(courtCaseplan,securityFlag, "Stop");
							}
							pasuseAndGo.toPause(courtCaseplan.getId());
							System.out.println("停止存储" + "------" +courtCase.getCaseNum());
						}
						
						stopPlanId.put(courtCaseplan.getId(),"store");
						if(new Date().before(courtCaseplan.getPlanEndTime())){
							
							this.store(courtCase.getCaseNum(),courtCaseplan.getPlanStartTime(),courtCaseplan.getId());
						}
				}else{
					System.err.println("停止存促调用的Id为空===+++");
				}
			}
		}
		}
		
		this.clearMap();
	}
	
	private void clearMap(){
		DateTime tomorrow = new DateTime().withTimeAtStartOfDay();
		long tomorrowNum = tomorrow.plusDays(1).toDate().getTime();
		long nowTimeNum = new Date().getTime();
		if(tomorrowNum - nowTimeNum <3*60*1000){
			stopPlanId.clear();
		}
	}
	
	//查询文件是否还在录制
	public void store(String caseNum,Date planStartTime,String id) throws IOException{
		System.out.println("查询案件是否还在录制" +"---" +caseNum);
		ScanFilesUtil scanFilesUtil = new ScanFilesUtil();
		boolean storeState = scanFilesUtil.searchVideoSize(caseNum,planStartTime);
		if(!storeState){
			CourtCasePlan courtCasePlan = casePlanService.get(id);
			//验证可直播标示若为2返回true则进行向最高院推流的动作
			boolean liveFlag  = pushStream.TestAllowLiveFlag(courtCasePlan);
			if(liveFlag){	
				pushStream.pushStream(courtCasePlan,securityFlag,"Start");
			}
			pasuseAndGo.toGo(id);
		}
	}
}
