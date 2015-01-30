package com.ht.court.schedule;

import java.io.IOException;

import com.fx.oss.upg.AppConfig;
import com.ht.court.client.CpubClinetPushStream;
import com.ht.court.model.CourtCasePlan;


/**
 * @author xinqichao
 *
 *  组合目标rtmp流和连接地址以及调用发送http的类
 */
public class PushStreamToHigtest {
	
	private AppConfig appConfig = new AppConfig();
	private String clientProtocol = appConfig.cfg().getString("clientProtocol");
	private String clientHost = appConfig.cfg().getString("clientHost");
	private String clientPost = appConfig.cfg().getString("clientPost");
	private String targetProtocol = appConfig.cfg().getString("targetProtocol");
	private String targetHost = appConfig.cfg().getString("targetHost");
	private String targetPost = appConfig.cfg().getString("targetPost");
	private String targetApp = appConfig.cfg().getString("targetApp");
	private String targetAuthority = appConfig.cfg().getString("targetAuthority");
	
	/**
 	 *   检测是否需要推送到最高院 
	 * @param courtCasePlan
	 * @return
	 */
	public boolean TestAllowLiveFlag(CourtCasePlan courtCasePlan){
		if(courtCasePlan.getAllowLiveFlag() == 2){
			return true;
		}
		return false;
	}
	
	/**
	 *  调用连接推送到最高院的连接
	 * @param courtCasePlan
	 * @param pushFlag
	 * @throws IOException
	 */
	public void pushStream(CourtCasePlan courtCasePlan,String securityFlag,String pushFlag) throws IOException{
		String soureStreamKey = courtCasePlan.getPubAccessUrl().substring(courtCasePlan.getPubAccessUrl().lastIndexOf("/") + 1);
		String clientUrl = clientProtocol+"://"+ clientHost+":"+clientPost;
		String targetStream = targetProtocol +"://"+targetAuthority+"@"+ targetHost+":"+targetPost+"/"+targetApp+"/"+soureStreamKey;
		//String targetStream = targetProtocol +"://"+ targetHost+":"+targetPost+"/"+targetApp+"/"+soureStreamKey;
		System.out.println("clientUrl: " + clientUrl +"&&&"+"targetStream: " + targetStream+">||||");
	
		String response = CpubClinetPushStream.clientServerPushStream
				(securityFlag,pushFlag,clientUrl, soureStreamKey, courtCasePlan.getPubAccessUrl(), targetStream, courtCasePlan.getPlanStartTime(),courtCasePlan.getPlanEndTime());
		System.out.println("推送到最高院的返回值为：" + response);
	}
	
	/*public static void main(String[] args) throws IOException {
		DateTime date = new DateTime();
	
		CourtCasePlan courtCasePlan = new CourtCasePlan();
		PushStreamToHigtest push = new PushStreamToHigtest();
		courtCasePlan.setPubAccessUrl("rtmp://125.76.229.24:1935/live/livestream_shangluo");
		courtCasePlan.setPlanStartTime(new Date());
		courtCasePlan.setPlanEndTime(date.plusDays(2).toDate());
		push.pushStream(courtCasePlan, "Stop");
	}*/
}
