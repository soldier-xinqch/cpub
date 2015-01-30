package com.ht.court.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.oss.upg.AppConfig;


/**
 *  封装生成完整点播地址的工具类
 */
public class VodUrlsUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(VodUrlsUtil.class);

	private AppConfig appConfig = new AppConfig();
	
	public List<String> createVodUrl(String caseNum,Date startVodTime,String vodFile){
 		List<String> vodFiles = new ArrayList<String>();
		List<String> vodFileUrls =new ArrayList<String>();
		logger.debug("获得扫描文件的参数：=[{}],[{}]",caseNum,startVodTime);
		if(StringUtils.isEmpty(vodFile)){
			vodFile="  ";//得到的播放列表为空！
		}
		String vodFiles1 = vodFile.substring(1, vodFile.length()-1);
		String[] fils = vodFiles1.split(",");
		for(int i=0;i<fils.length;i++){
			fils[i].trim();
//					fils[i].lastIndexOf(str)
			vodFiles.add(fils[i]);
		}
		List<String> paths=FunctionUtil.genVodFilesRelativePaths(caseNum, startVodTime, vodFiles, true);
		vodFileUrls = new ArrayList<String>();
		for(String path:paths){
			String afterPath = path.replace(" ", "");
			vodFileUrls.add(afterPath);
		}
		String protocol = appConfig.cfg().getString("red5Protocol");
		String host = appConfig.cfg().getString("red5host");
		Integer prot = appConfig.cfg().getInt("red5prot");
		String appFolder = appConfig.cfg().getString("red5App");
		String vodFolder = appConfig.cfg().getString("red5VodFolder");
		logger.debug("点播链路："+protocol+host+prot+appFolder);
		List<String> urls=FunctionUtil.genVODUrls(protocol,host, prot,appFolder, vodFolder, vodFileUrls);
		for(String url:urls){
		}
		vodFileUrls.clear();
		return  urls;
	}
	
}
