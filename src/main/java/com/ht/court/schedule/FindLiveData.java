package com.ht.court.schedule;

import org.springframework.stereotype.Component;
/**
 * 查出所有需要转换为点播的数据,添加到调度任务中
 * 该数据应符合：1.del_flag=false; 2.计划闭庭时间>当前时间
 */
@Component
public class FindLiveData {
	
//	private static final Logger logger=LoggerFactory.getLogger(FindLiveData.class);
//	
//	@Autowired
//	private CourtCasePlanService casePlanService;
	
	/**
	 * 直播开始调用服务器方法开始存储视频
	 * @throws SchedulerException
	 */
//	@Scheduled(cron="0 0/3 * * * ? ")
//	public synchronized void find() throws SchedulerException{
//		//查询，返回符合条件的庭审直播记录
//		List<CourtCasePlan> courtCasePlans = casePlanService.getToDatePlan();
//		logger.info("@-查询出来的当天的案件直点播条数{}",courtCasePlans.size());
//		
//		
//		Date date = new Date();
//		long dateNum = date.getTime();
//		if(!CollectionUtils.isEmpty(courtCasePlans)){
//			for(CourtCasePlan courtCasePlan:courtCasePlans){
//				courtCasePlan.getPlanStartTime().getTime();
//				logger.debug("得到的时间的long值：{},{}",dateNum,courtCasePlan.getPlanStartTime().getTime());
//				logger.debug("得到的时间的long值：{},{}",date,courtCasePlan.getPlanStartTime());
//				logger.debug("得到的时间的long值：{},{}",date,courtCasePlan.getPlanEndTime());
//				if(dateNum == courtCasePlan.getPlanStartTime().getTime()){
//					logger.info("@-开始连接服务器进行存储");
//					this.startStore(courtCasePlan.getId());
//				}else if(date.equals(courtCasePlan.getPlanEndTime())){
//					logger.info("@-停止与服务器的连接");
//					this.stopNet(courtCasePlan.getId());
//				}
//			}
//			
//		}
//	}
	
/*	public void startStore(String id){
		//将查询出来的非空记录的ID添加到集合中,用于后面判断存在有效记录，但存在非有效任务时，删除非有效任务
		if(!StringUtils.isEmpty(id)){
			ManagementNet managementNet = new ManagementNet();
			managementNet.getConnection(id);
			logger.info("-p--------jfoirefeojfeforoijforefoioefjorejforjf-------------查询直播调度参数：");
			System.out.println("^^开始去调用服务器方法^^");
			managementNet.beginStore(id);
		}else{
			logger.debug("直播调度：查询排期信息为空！");
		}
	}
	public void stopNet(String id){
		//将查询出来的非空记录的ID添加到集合中,用于后面判断存在有效记录，但存在非有效任务时，删除非有效任务
		if(!StringUtils.isEmpty(id)){
			ManagementNet managementNet = new ManagementNet();
			managementNet.getConnection(id);
			logger.info("-p--------jfoirefeojfeforoijforefoioefjorejforjf-------------查询直播调度参数：");
			System.out.println("^^开始去调用服务器方法^^");
			managementNet.stopNetService(id);
		}else{
			logger.debug("直播调度：查询排期信息为空！");
		}
	}*/
}
