package com.fx.oss.upg;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 应用服务器容器启动和关闭侦听器。
 * <p>
 * 负责应用服务器启动和关闭的初始化和销毁工作。
 */
@WebListener
public class StartupListener implements ServletContextListener {
	
	private static Logger logger=LoggerFactory.getLogger(StartupListener.class);

    public StartupListener() {
        logger.info("升级服务初始化...");
    }


    public void contextInitialized(ServletContextEvent sce) {
    	ServletContext sc = sce.getServletContext();
    	//设置环境变量
//    	String env=sc.getInitParameter("ENV");
//    	Env.setEnvType(env==null ? Env.ENV_DEVELOPMENT : env);
//    	logger.info("升级服务运行在{}",Env.descEnvType(Env.getEnvType()));
    	//设置升级服务应用的根目录
    	AppConfig.setOSSBaseDir(getCxtRealPath(sc));
    	//启动H2数据库
    	try {
    		H2DBServer.startH2();
		} catch (SQLException e) {
			logger.error("启动H2数据库发生错误，错误消息：{}",e.getMessage());
			e.printStackTrace();
		}
    }
    
    public void contextDestroyed(ServletContextEvent sce) {
    	//ServletContext sc = sce.getServletContext();
    	H2DBServer.stopH2();
    }
    
    private String getCxtRealPath(ServletContext sc){
    	String path = new File(sc.getRealPath("/")).getAbsolutePath();
    	return path;
    }
	
}
