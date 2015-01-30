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

public class CpubHttpClient {
	
	private static Logger logger = LoggerFactory.getLogger(CpubHttpClient.class);
	
	/**
	 * 调用流媒体服务器的借口
	 * @param protocol   协议类型 eg:http/https
	 * @param host 		 流媒体服务器IP
	 * @param prot		 流媒体服务端口
	 * @param url		流媒体服务的路径
	 * @param courtNumber	案号：每天只有唯一的案号接收存储命令
	 * @param streamUrl		流地址：eg: rtmp协议
	 * @param command		命令：store（存储）、stop（停止）和search（查询）
	 * @param startTime		开始存储时间	精确到分钟:09:30
	 * @param endTime		结束存储时间	精确到分钟:13:50
	 * @param chunkTize		存储的每块文件大小，单位M。 如果为null，使用默认值。
	 * @param chunkTime		存储的每块文件的时长，单位min(分钟)。如果为null，使用默认值。
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
//	@SuppressWarnings("deprecation")
	public static String httpClientRequest(String protocol,String host,int prot,String url,
			String courtNumber, String streamUrl,String command, 
			Date startTime, Date endTime, String chunkTize,String chunkTime) throws HttpException, IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String storeStart = format.format(startTime);
		String storeEnd = format.format(endTime);
		String[] timeStr = storeStart.split(" ");
		String[] timeStr2 = storeEnd.split(" ");
		String startTimeStr  = timeStr[1];
		String endTimeStr  = timeStr2[1];
		
		
		HttpClient httpClient = new HttpClient();  
        httpClient.getHostConfiguration().setHost(host, prot, protocol);
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");    
        NameValuePair[] param = { 
        		new NameValuePair("court_number",courtNumber),  
                new NameValuePair("stream_url",streamUrl),  
                new NameValuePair("cmd",command),  
                new NameValuePair("start_time",startTimeStr),
		        new NameValuePair("end_time",endTimeStr),
		        new NameValuePair("chunk_size",chunkTize),
		        new NameValuePair("chunk_time",chunkTime)
        } ; 
        post.setRequestBody(param);  
        post.releaseConnection(); 
        String response = null;
        try {
        	logger.info("<<开始发送流！>>");
			httpClient.executeMethod(post);
			response = new String((post.getResponseBodyAsString()).getBytes(),"UTF-8");  
		} catch (HttpException e) {
			logger.info("发送流媒体服务请求时发生HttpException异常",e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("发送流媒体服务请求时发生IOException异常",e.getMessage());
			throw e;
		}  
		return response;
	}
	
	/**
	 * 调用流媒体服务器的借口
	 * @param protocol   协议类型 eg:http/https
	 * @param host 		 流媒体服务器IP
	 * @param prot		 流媒体服务端口
	 * @param url		流媒体服务的路径
	 * @param courtNumber	案号：每天只有唯一的案号接收存储命令
	 * @param streamUrl		流地址：eg: rtmp协议
	 * @param command		命令：store（存储）、stop（停止）和search（查询）
	 * @param startTime		开始存储时间	精确到分钟:09:30
	 * @param endTime		结束存储时间	精确到分钟:13:50
	 * @param chunkTize		存储的每块文件大小，单位M。 如果为null，使用默认值。
	 * @param chunkTime		存储的每块文件的时长，单位min(分钟)。如果为null，使用默认值。
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String httpClientRequest(String protocol,String host,int prot,String url,String courtNumber, String streamUrl,
			String command, Date startTime, Date endTime) throws HttpException, IOException  {
		String response = httpClientRequest(protocol,host,prot,url,courtNumber, streamUrl,command, startTime, endTime,null,null);
		return response;
	}
	
	
	/**
	 * 调用流媒体服务器的借口
	 * @param protocol   协议类型 eg:http/https
	 * @param host 		 流媒体服务器IP
	 * @param prot		 流媒体服务端口
	 * @param url		流媒体服务的路径
	 * @param courtNumber	案号：每天只有唯一的案号接收存储命令
	 * @param streamUrl		流地址：eg: rtmp协议
	 * @param command		命令：store（存储）、stop（停止）和search（查询）
	 * @param startTime		开始存储时间	精确到分钟:09:30
	 * @param endTime		结束存储时间	精确到分钟:13:50
	 * @param chunkTize		存储的每块文件大小，单位M。 如果为null，使用默认值。
	 * @param chunkTime		存储的每块文件的时长，单位min(分钟)。如果为null，使用默认值。
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static PostMethod cpubHttpClientRequest(String protocol,String host,int prot,String url,
			String courtNumber, String streamUrl,String command, 
			Date startTime, Date endTime, String chunkTize,String chunkTime) throws HttpException, IOException {
		
		String startTimeStr = startTime.getHours() +":" + (startTime.getMinutes()+1);
		String endTimeStr = endTime.getHours() +":" + endTime.getMinutes();
		
		HttpClient httpClient = new HttpClient();  
        httpClient.getHostConfiguration().setHost(host, prot, protocol);
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");    
        NameValuePair[] param = { 
        		new NameValuePair("court_number",courtNumber),  
                new NameValuePair("stream_url",streamUrl),  
                new NameValuePair("cmd",command),  
                new NameValuePair("start_time",startTimeStr),
		        new NameValuePair("end_time",endTimeStr),
		        new NameValuePair("chunk_size",chunkTize),
		        new NameValuePair("chunk_time",chunkTime)
        } ; 
        post.setRequestBody(param);  
        post.releaseConnection(); 
        try {
        	logger.info("发送流");
			httpClient.executeMethod(post);
		} catch (HttpException e) {
			logger.info("发送流媒体服务请求时发生HttpException异常",e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("发送流媒体服务请求时发生IOException异常",e.getMessage());
			throw e;
		}  
		return post;
	}
}
