package com.ht.court.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.oss.upg.AppConfig;

/**
 * 扫描点播文件的工具类
 * @author soldier
 *
 */
public class ScanFilesUtil {
	private static final Logger logger = LoggerFactory.getLogger(ScanFilesUtil.class);
	
	private Map<String,Long> fileSize = new HashMap<String, Long>();

	public List<String> findFiles(String caseNum,Date planStartTime){
		List<String> vodFiles = new ArrayList<String>();
//		DateFormat df=new SimpleDateFormat("yyyy/M/d");
		AppConfig appConfig = new AppConfig();
		FunctionUtil  streamPaths = new FunctionUtil();
		if(StringUtils.isEmpty(caseNum)||planStartTime == null){
			logger.debug("得到的案号或时间为空！");
			return null;
		}
		//文件所在路径
//		String path = appConfig.cfg().getString("vodpath");
		//所查找文件的格式
		String expanded  = appConfig.cfg().getString("vodExpanded");
//		String startTimeStr=df.format(planStartTime);
		String paths =getVideoPath(caseNum,planStartTime);
		logger.debug("<<<<查找文件的路径为："+paths+">>>>");
		List<String> files = streamPaths.getVODFiles(paths, expanded, true);
		for(int i=0;i<files.size();i++){
			String ee = files.get(i).substring(files.get(i).lastIndexOf(File.separator)+1 );
			vodFiles.add(ee);
		}
		this.deleteFiles(paths+File.separator);
		return vodFiles;
	}
	
	//获得文件路径
	public String getVideoPath(String caseNum,Date planStartTime){
		AppConfig appConfig = new AppConfig();
		DateFormat df=new SimpleDateFormat("yyyy/M/d");
		String startTimeStr=df.format(planStartTime);
		//文件所在路径
		String path = appConfig.cfg().getString("vodpath");
		String paths =(path+startTimeStr+"/"+caseNum).trim();
		return paths;
		
	}
	
	//删除小于1M的视频文件
	public void deleteFiles(String filePath){
		File file = new File(filePath);
		String[] files = file.list();//获取文件集合
		if(files != null){
			for(int i = 0;i<files.length;i++){
				File siFile = new File(filePath+files[i]);//得到视频文件的大小
				if(1048576 >siFile.length()){
					siFile.delete();//删除文件
				}
			}
		}
	}
	
	//查询路径下的文件查看文件大小是否增长
	public boolean searchVideoSize(String caseNum,Date planStartTime){
		if(new Date().getHours() == 00){
			fileSize.clear();
		}
		String path = this.getVideoPath(caseNum,planStartTime);
		File file = new File(path);
		String[] files = file.list();//获取文件集合
		long totalSize = 0;
		if(files != null){
			System.out.println("搜索到的文件："+ files.length);
			for(int i = 0;i<files.length;i++){
				File siFile = new File(path+files[i]);//得到视频文件的大小
				totalSize = siFile.length()+totalSize;
			}
			if(fileSize.get(caseNum) != null){
				System.out.println("得到的差值为："+ (totalSize - fileSize.get(caseNum)));
				if(totalSize >fileSize.get(caseNum)){
					logger.debug("返回ture 仍然在存");
					return true;
				}
			}
			
			fileSize.put(caseNum, totalSize);
		}
		logger.debug("返回false 存储不存了");
		return false;
		
	}
	
}
