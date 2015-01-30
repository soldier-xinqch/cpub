package com.ht.court.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xinqichao
 *
 *  连接公网推流器
 */
public class CpubClinetPushStream {

	private static Logger logger = LoggerFactory.getLogger(CpubHttpClient.class);
	
	/**
	 *  连接方法
	 * 
	 * @param pushFlag 推送标识（推送的开关）
	 * @param clientUrl 公网的服务的URL
	 * @param soureStreamKey 源流的KEY
	 * @param soureStreamUrl 源rtmp流的URL
	 * @param targetStream  目标RTMP流
	 * @param StartTime 推送开始时间
	 * @param endTime  推送结束时间
	 * @return
	 * @throws IOException
	 */
	public static String clientServerPushStream
		(String securityFlag ,String pushFlag,String clientUrl,String soureStreamKey,String soureStreamUrl,String targetStream,Date StartTime,Date endTime ) throws IOException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pushStartTime = format.format(StartTime);
		String pushEndTime = format.format(endTime);
		
		//初始化连接
		HttpClient httpClient = new HttpClient();
		//设置连接方式并放入连接目标
		PostMethod post = new PostMethod(clientUrl);
	    //放置连接头信息
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
		System.out.println("soureStreamKey"+soureStreamKey+"<+++>soureStreamUrl:" +soureStreamUrl
				+"<++__++>targetStream:"+targetStream);
	    //放置参数
		NameValuePair[] param = { 
			new NameValuePair("securityFlag",securityFlag),
	    	new NameValuePair("pushFlag",pushFlag),
	   		new NameValuePair("soureStreamKey",soureStreamKey),  
            new NameValuePair("soureStreamUrl",soureStreamUrl), 
            new NameValuePair("targetStream",targetStream),
            new NameValuePair("pushStartTime",pushStartTime),
	        new NameValuePair("pushEndTime",pushEndTime)
	    } ; 
	  post.setRequestBody(param);  
      post.releaseConnection(); 
      String response = null;
      try {
      	logger.info("<<开始向最高院推流 ！>>");
		//发送连接
      	httpClient.executeMethod(post);
		//获得返回的回应参数	
      	response = new String((post.getResponseBodyAsString()).getBytes(),"UTF-8");  
		} catch (HttpException e) {
			logger.info("发送请求时发生HttpException异常",e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("发送请求时发生IOException异常",e.getMessage());
			throw e;
		}  
		return response;
	}

}
