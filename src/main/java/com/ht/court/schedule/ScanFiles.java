package com.ht.court.schedule;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ht.court.model.CourtCasePlan;
import com.ht.court.service.CourtCasePlanService;
import com.ht.court.service.CourtCaseService;
import com.ht.court.util.ScanFilesUtil;
import com.hx.auth.CollectionUtils;


/**
 * @author xinqichao
 *   
 *  定时查找点播文件
 */
@Component
public class ScanFiles {
	
	private static final Logger logger = LoggerFactory.getLogger(ScanFiles.class);
	
	@Autowired
	private CourtCaseService caseService;
	@Autowired
	private CourtCasePlanService casePlanService;
	@Autowired
	private Scheduler scheduler;
//	@Scheduled(cron="0 0 6 * * ? ")
	@Scheduled(cron="0 0/3 * * * ? ")
	public synchronized void findFilesSetDB(){
		logger.debug("开始对点播文件进行调度！逐条进行插入操作");
		//时间戳
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		//查找文件的工具类
		ScanFilesUtil scanFiles = new ScanFilesUtil();
		List<CourtCasePlan> courtCasePlans = casePlanService.findPlansByCaseNumAndStartTime(nowTime);
		if(CollectionUtils.isEmpty(courtCasePlans)){
			logger.debug("查询文件-调度操作：点播为空！");
		}else{
			logger.debug("当前时间为：" + new Date());
			for(CourtCasePlan courtCasePlan : courtCasePlans){
				List<String> vodUrls = scanFiles.findFiles(courtCasePlan.getCourtCase().getCaseNum(), courtCasePlan.getPlanStartTime());
				String vodKey = null;
				
//				if(courtCasePlan.getVodAccessUrl() != null){
//					vodKey = courtCasePlan.getVodAccessUrl()+vodUrls.toString();
//				}else{
					vodKey = vodUrls.toString();
//				}
				courtCasePlan.setVodAccessUrl(vodKey);
				casePlanService.update(courtCasePlan);
				vodUrls.clear();
			}
		}
	}
}