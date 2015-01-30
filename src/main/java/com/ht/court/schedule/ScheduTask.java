package com.ht.court.schedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

import com.ht.court.model.CourtCasePlan;
/**
 * 
 *
 */
@Component
public class ScheduTask {
	@Autowired
	private Scheduler scheduler;
	@Autowired
	private CasePlanTask casePlanTask;
	@Autowired
	private CasePlanTaskListner casePlanTaskListner;
	
	public void creatTask(CourtCasePlan courtCasePlan) {

		try {
			//定制job细节
			MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
			jobDetail.setTargetObject(casePlanTask);
			jobDetail.setTargetMethod("execute");
			Object[] object = {courtCasePlan};
			jobDetail.setArguments(object);
			jobDetail.setGroup("LIVED_TASK");//LIVED_TASK,job的分组，此处指定分组为庭审直播的分组
			jobDetail.setName(courtCasePlan.getId());//job的名字，此处指定排期记录的ID
			jobDetail.setConcurrent(false);
			jobDetail.afterPropertiesSet();
			
			//定义触发策略，此处为根据排期的计划时间触发任务
			SimpleTrigger simpleTrigger = new SimpleTrigger(courtCasePlan.getId(),"LIVED_TASK");//触发器命名、分组
			simpleTrigger.setStartTime(courtCasePlan.getPlanStartTime());//将计划开庭时间定制为任务的触发时间
//			Date ss = courtCasePlan.getPlanEndTime();
			simpleTrigger.setEndTime(courtCasePlan.getPlanEndTime());
			//注册调度
			scheduler.scheduleJob((JobDetail) jobDetail.getObject(),simpleTrigger);
			
			//将当前排期记录的ID和修改时间保存到SchedulerContext中
			SchedulerContext context = scheduler.getContext();
			context.put(courtCasePlan.getId(), courtCasePlan.getUpdateTime());
			
			//注册job监听器
			scheduler.addGlobalJobListener(casePlanTaskListner);
			
			// Start Scheduler
			scheduler.start();
			System.out.println("排期"+ courtCasePlan.getId() + "计划任务已启动");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
