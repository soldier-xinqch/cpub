package com.ht.court.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ht.court.model.CourtCasePlan;
import com.ht.court.service.CourtCasePlanService;
/**
 * 查出所有需要转换为点播的数据,添加到调度任务中
 * 该数据应符合：1.del_flag=false; 2.计划闭庭时间>当前时间
 */
@Component
public class FindData {
	
	private static final Logger logger=LoggerFactory.getLogger(FindData.class);
	
	@Autowired
	private CourtCasePlanService casePlanService;
	@Autowired
	private ScheduTask scheduTask;
	@Autowired
	private Scheduler scheduler;
	
	@Scheduled(cron="0 0/2 * * * ? ")
	public synchronized void find() throws SchedulerException{
		
		//查询，返回符合条件的庭审直播记录
//		List<CourtCasePlan> courtCasePlans = casePlanService.findAllMatchRecord();
		DateTime date = new DateTime();
		logger.info("查询直播调度参数：=[{}],[{}]",date.plusDays(1).toDate(),date.toDate());
		List<CourtCasePlan> courtCasePlans = casePlanService.findLivesByTime(date.plusDays(1).toDate(),date.toDate());
		
		//将查询出来的非空记录的ID添加到集合中,用于后面判断存在有效记录，但存在非有效任务时，删除非有效任务
		List<String> matchedIds = new ArrayList<String>();
		if(courtCasePlans!=null){
			for(CourtCasePlan courtCasePlan:courtCasePlans){
				matchedIds.add(courtCasePlan.getId());
			}
		}else{
			logger.debug("直播调度：查询排期信息为空！");
		}
		
		String[] jobNames = scheduler.getJobNames("LIVED_TASK");//调度器中存在的任务名
		String groupName = "LIVED_TASK";//job的分组名称。和调度类中的分组名称一样
		logger.debug("开始直播调度准备工作-数据扫描，有效数据有"+courtCasePlans.size()+"条");
		int liveNum =0;
		//遍历排期表数据，将其添加到调度任务中
		if(!CollectionUtils.isEmpty(courtCasePlans)){
			for(CourtCasePlan courtCasePlan:courtCasePlans){
				//定义变量
				String jobName = courtCasePlan.getId();//job的名称，唯一值，用的排期的ID。和调度类中的名称一样
				long planTime = courtCasePlan.getPlanStartTime().getTime();//计划开庭时间的long值
				long sysTime = new Date().getTime();//当前系统时间内的long值
				String updateTime=null;//排期表的更新时间
				String saveTime = null;//调度器中保存的任务对应记录的更新时间
				
				if(courtCasePlan.getUpdateTime()!=null){
					updateTime = courtCasePlan.getUpdateTime().toString();
				}
				if(scheduler.getContext().get(jobName)!=null){
					saveTime = scheduler.getContext().get(jobName).toString();
				}
				if(new Date().after(courtCasePlan.getPlanStartTime())){
					liveNum++;
				}
				//1、新增的任务：对于未删除的记录，从调度器中检查是否已有该任务，没有的话，如果直播在10分钟后开始，则把任务开始放入调度中
				if(scheduler.getJobDetail(jobName, groupName)==null
						&&planTime-sysTime>0 && planTime-sysTime<(10*60*1000)){
					scheduTask.creatTask(courtCasePlan);
					logger.debug("新增任务={}", jobName);
					System.out.println("新增任务,planId=" + jobName);
				//2、修改的任务：已放入调度中，但后来修改过的直播任务
				}else if(scheduler.getJobDetail(jobName, groupName)!=null
						&&updateTime!=null&&(!updateTime.equals(saveTime))){
					scheduler.pauseTrigger(jobName, groupName);//停止触发器
					scheduler.unscheduleJob(jobName, groupName);//移除触发器
					scheduler.deleteJob(jobName, groupName);//删除任务
					logger.debug("修改任务，先删除原任务={}", jobName);
					System.out.println("修改任务，先删除原任务=" + jobName);
					if((planTime-sysTime)<(10*60*1000)){
						scheduTask.creatTask(courtCasePlan);
						logger.debug("修改任务，将修改后的任务重新调度={}", jobName);
						System.out.println("修改任务，将修改后的任务重新调度=" + jobName);
					}
				}else if(jobNames!=null){//3. 删除任务：如果排期表存在有效记录，而调度器中存在非有效记录的任务，则将非有效任务删除
					for(String name : jobNames){
						if(!matchedIds.contains(name)){
							scheduler.pauseTrigger(name, groupName);
							scheduler.unscheduleJob(name, groupName);
							scheduler.deleteJob(name, groupName);
							logger.debug("如果记录已经删除，任务存在，则删除====,={}", jobName);
							System.out.println("如果记录已经删除，任务存在，则删除====,=" + jobName);
						}
					}
				}
			}
			logger.info("可以进行调度的任务数："+liveNum);
			System.out.println("可以进行调度的任务数：" + liveNum);
			liveNum=0;
		}else{
			//4、删除任务：如果排期表有效记录已全部被删除，检查调度器中是否有任务，有的话，予以清空
			if(jobNames!=null){
				for(String name : jobNames){
					scheduler.pauseTrigger(name, groupName);
					scheduler.unscheduleJob(name, groupName);
					scheduler.deleteJob(name, groupName);
					logger.debug("如果记录已经删除，任务存在，则删除+++++,={}", name);
				}
			}
		}
	}
	
}
