package com.fx.oss.upg;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppConfig {
	
	private static Logger logger=LoggerFactory.getLogger(AppConfig.class);
	private static Configuration config;
	private static String OSS_BASE;
	
	//初始化配置文件访问
	static{
		PropertiesConfiguration pconf = new PropertiesConfiguration();
        try {
            //pconf.load("config/upgrade_"+ Env.getEnvType() +".properties");
        	pconf.load("config/upgrade.properties");
        	config=pconf;
        } catch (ConfigurationException ex) {
            logger.error("初始化属性配置文件出错！");
            ex.printStackTrace();
        }
	}
	
	/**
	 * 获取属性配置。
	 * 
	 * @return 返回<code>Configuration</code>对象。
	 */
	public static Configuration cfg(){
		return config;
	}
	
	public static String ossBase(){
		return OSS_BASE;
	}
	/**
	 * 升级工作主目录。
	 * 
	 * @return
	 */
	public static String upgradeBaseDir(){
		return ossDataDir() + "upgrade" + File.separator;
	}
	/**
	 * 升级数据库目录。
	 * 
	 * @return
	 */
	public static String upgradeDbDir(){
		return upgradeBaseDir() + "db" + File.separator;
	}
	/**
	 * 升级文件目录。
	 * 
	 * @return
	 */
	public static String upgradeFileDir(){
		return upgradeBaseDir() + "files" + File.separator;
	}
	/**
	 * 升级备份目录。
	 * 
	 * @return
	 */
	public static String upgradeBackupDir(){
		return upgradeBaseDir() + "backup" + File.separator;
	}
	/**
	 * 运营系统的数据目录。
	 * @return
	 */
	public static String ossDataDir(){
		return ossBase() + "oss_data" + File.separator;
	}
	
	/**
	 * web方式设置运营系统工作宿主(base)目录。
	 * 
	 * @param rootCtxPath
	 */
	public static void setOSSBaseDir(String rootCtxPath){
		String spli=File.separator;
		OSS_BASE=rootCtxPath + spli 
				+ ".." + spli + ".." + spli + ".." + spli;
		logger.info("OSS工作主目录{}",OSS_BASE);
	}

}
