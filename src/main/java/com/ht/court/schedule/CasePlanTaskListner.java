package com.ht.court.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * 调度任务监控器类
 *
 */
@Component
public class CasePlanTaskListner implements JobListener {

	private static final Logger logger=LoggerFactory.getLogger(CasePlanTaskListner.class);
	
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		String jobName = context.getJobDetail().getName();
		logger.info(jobName+"job被否决未调用");

	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getName();
		logger.info(jobName+"job即将执行");

	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException arg1) {
		String jobName = context.getJobDetail().getName();
		logger.info(jobName+"job已经执行完毕");

	}

}
