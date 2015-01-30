package com.fx.oss.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 给web app提供一个application context的全局获取方式。
 * 
 * @author huangwy
 */
@Component("appContext")
public class AppCtx implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        appCtx = ac;
    }

    public static ApplicationContext getContext() {
        return appCtx;
    }

}
