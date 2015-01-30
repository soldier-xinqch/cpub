package com.fx.oss.upg;

import java.sql.SQLException;

import org.apache.commons.configuration.Configuration;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2DBServer {
	
	private static Logger logger=LoggerFactory.getLogger(H2DBServer.class);
	
	private static volatile boolean dbStarted=false;
	private static Server h2TcpServer;
	private static Server h2WebServer;
	
	public static final String H2_MODE_SERVER_REMOTE="server_remote";
	public static final String H2_MODE_SERVER_LOCAL="server_local";
	public static final String H2_MODE_MEMORY="memory";
	
	private static final String H2_TCP_PORT="9081";
	private static final String H2_WEB_PORT="8082";

	/*
	 * 以服务器方式(Server Mode)启动H2数据库服务。
	 * 
	 * @throws SQLException
	 */
	public static synchronized void startH2() throws SQLException{
		String mode=cfg().getString("h2_mode");
		logger.info("H2运行在{}",descMode(mode));
		if(!dbStarted && H2_MODE_SERVER_LOCAL.equals(mode)){
			String tcpPort=cfg().getString("h2_tcp_port");
			String webPort=cfg().getString("h2_web_port");
			String dir=AppConfig.upgradeDbDir();
			logger.info(" 数据库目录{}",dir);
			if(tcpPort==null)
				tcpPort=H2_TCP_PORT; 
			if(webPort==null)
				webPort=H2_WEB_PORT;
			//"-tcpPort", port,
			logger.info("port1-{};port2-{}",tcpPort,webPort);
			//dir="c:\\temp2";
			h2TcpServer = Server.createTcpServer(new String[] { "-tcpAllowOthers", 
					"-webAllowOthers",  "-tcpPort", tcpPort, "-baseDir", dir });
			logger.info("1111");
			h2TcpServer.start();
			logger.info("22222");
			h2WebServer = Server.createWebServer(new String[] {"-webPort", webPort}).start();
			logger.info("33333");
			dbStarted=true;
			logger.info("H2数据库[服务端口：{}，数据库基础目录：{}, web访问端口："+webPort+"]启动成功。", tcpPort, dir);
		}
//		if(!dbStarted && H2_MODE_MEMORY.equals(mode)){
//			
//		}
	}
	
	/*
	 * 关闭H2数据库服务。
	 * 
	 */
	public static synchronized void stopH2(){
		if(dbStarted){
			h2TcpServer.stop();
			h2WebServer.stop();
			dbStarted=false;
			logger.info("H2数据库已关闭。");
		}
	}
	
	public static Configuration cfg(){
		return AppConfig.cfg();
	}
	
	public static String descMode(String mode){
		if(H2_MODE_SERVER_REMOTE.equals(mode)){
			return "运程服务模式";
		}else if(H2_MODE_SERVER_LOCAL.equals(mode)){
			return "本地服务模式";
		}else if(H2_MODE_MEMORY.equals(mode)){
			return "内存服务模式";
		}else{
			return "错误，发现未知的运行模式";
		}
	}
}
