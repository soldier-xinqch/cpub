package com.ht.court.util;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ht.court.model.CourtCasePlan;

/**
 * 功能函数工具类。
 * <p>
 * 以函数方式提供一些常用功能。
 * 
 * @author huangwy
 * @version 1.0 2014/4/7
 *
 */
public class FunctionUtil {
	/**
	 * 获取登录用户信息。
	 * 
	 * @return 返回登录用户接口对象。
	 */
	public static UserDetails getUserDetails(){
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}
	
	/**
	 * 获取点播文件列表。
	 * 
	 * @param folder 点播目录。
	 * @param fileFilter 文件过滤器。
	 * 
	 * @return 返回点播文件数组。
	 */
	public static List<String> getVODFiles(String folderName,String fileFilter,boolean isFullPath){
		File folder=new File(folderName);
		List<String> result=new ArrayList<String>();
		if(!folder.isDirectory()){
			return result;
		}
		String[] files=null;
		if(fileFilter!=null){
			files=folder.list(new FileFilter(fileFilter));
		}else{
			files=folder.list();
		}
		if(files!=null){
			folderName= folderName.endsWith(File.separator) ? folderName : folderName + File.separator;
			String parentFolder=isFullPath ? folderName : "";
			for(String file:files){
				result.add(parentFolder + file);
			}
			Collections.sort(result);
		}
		return result;
	}
	
	private static class FileFilter implements FilenameFilter{
		
		private String fileExtension;
		
		public FileFilter(String fileExtension){
			this.fileExtension=fileExtension.startsWith(".") 
					? fileExtension.toLowerCase() : "." + fileExtension.toLowerCase(); 
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(fileExtension);
		}
		
	}
	
	/**
	 * 生成点播Url路径。
	 * 
	 * @param protocol 点播流协议，通常是rtmp。
	 * @param host 点播流主机。
	 * @param port 点播流端口。
	 * @param red5App red5应用名称。
	 * @param streamFolder red5流目录，通常是streams。
	 * @param vodFileRelativePath 点播文件相对路径，通常是"案号/开庭日期/无扩展名的flv文件"
	 * 
	 * @return 返回点播url路径。
	 */
	public static String genVODUrl(String protocol, String host, int port, String red5App, String streamFolder, String vodFileRelativePath){
		return genVODUrlPrefix(protocol,host,port,red5App,streamFolder) + "/" + vodFileRelativePath;
	}
	
	/**
	 * 
	 * 生成多个点播Url路径。
	 * 
	 * @param protocol 点播流协议，通常是rtmp。
	 * @param host 点播流主机。
	 * @param port 点播流端口。
	 * @param red5App red5应用名称。
	 * @param streamFolder red5流目录，通常是streams。
	 * @param vodFileRelativePaths 多个点播文件相对路径，格式通常是"案号/开庭日期/无扩展名的flv文件"
	 * 
	 * @return
	 */
	public static List<String> genVODUrls(String protocol, String host, int port, String red5App, String streamFolder, List<String> vodFileRelativePaths){
		List<String> result=new ArrayList<String>();
		for(String path:vodFileRelativePaths){
			result.add(genVODUrl(protocol,host,port,red5App, streamFolder,path));
		}
		return result;
	}
	
	/**
	 * 生成点播Url路径前缀。
	 * 
	 * @param protocol 点播流协议，通常是rtmp。
	 * @param host 点播流主机。
	 * @param port 点播流端口。
	 * @param red5App red5应用名称。
	 * @param streamFolder red5流目录，通常是streams。
	 * 
	 * @return 返回点播url路径前缀。
	 */
	public static String genVODUrlPrefix(String protocol, String host, int port, String red5App, String streamFolder){
		return protocol + "://" + host + ":" + port + "/" + red5App + "/" + streamFolder;
	}
	
	/**
	 * 生成点播文件相对路径。
	 * 
	 * @param caseNum 案号。
	 * @param startTime 开庭日期。
	 * @param vodFile 点播文件名。
	 * @param isExtSub 是否截取扩展名，通常为true。
	 * 
	 * @return 返回点播文件相对路径。
	 */
	public static String genVodFileRelativePath(String caseNum,Date startTime, String vodFile, boolean isExtSub){
		DateFormat df=new SimpleDateFormat("yyyy/M/d");
		String startTimeStr=df.format(startTime);
		if(isExtSub){
			int extPointLastIndex=vodFile.lastIndexOf(".");
			if(extPointLastIndex>0)
				vodFile=vodFile.substring(0, extPointLastIndex);
		}
		return startTimeStr + "/" + caseNum + "/" + vodFile;
	}
	
	/**
	 * 生成多个点播文件相对路径。
	 * 
	 * @param caseNum 案号。
	 * @param startTime 开庭日期。
	 * @param vodFiles 点播文件名列表。
	 * @param isExtSub 是否截取扩展名，通常为true。
	 * 
	 * @return 返回点播文件相对路径列表。
	 */
	public static List<String> genVodFilesRelativePaths(String caseNum,Date startTime, List<String> vodFiles, boolean isExtSub){
		List<String> result=new ArrayList<String>();
		for(String vodFile:vodFiles){
			result.add(genVodFileRelativePath(caseNum,startTime,vodFile,isExtSub));
		}
		return result;
	}
	
	/**
	 * 组装返回图标的数据
	 *  可输入多参数
	 * @param args 参数列表  String类型
	 * @return
	 */
	public static String generateChartsData(String... args) {
		 for (int i = 0; i < args.length; i++) {
	            System.out.println(args[i]);
	        }
		return "";
	}
	
	/**
	 *  组装返回图标的数据
	 * @param paramsMap  封装数据的map
	 * @return
	 */
	public static String generateChartsData(List<CourtCasePlan> courtCasePlan) {
		
		return "";
	}
	
	public static void main(String[] args) {
		generateChartsData("22","33","fff","ergerg","wcewecwe");
	}
}
