package com.fx.oss.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.*;
import javax.servlet.ServletRequest;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * web辅助工具类。
 * 
 * @author Weiyong Huang
 */
public class WUtils{

    public static final String getParam(ServletRequest request, String name, String defval) {
    	String param = request.getParameter(name);
        return (param != null ? param : defval);
    }

    public static final int getParam(ServletRequest request, String name, int defval) {
        String param = request.getParameter(name);
        int value = defval;
        if (param != null) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }
    
    public static final Map<String,Object> getParamsStartWith(ServletRequest request,String startWith){
        return null;
    }
    
    /**
     * 过滤web请求中属于绑定对象的属性名称。
     * 
     * @param bindedObj 绑定对象。
     * @param request servlet请求。
     * @return 过滤出的绑定属性列表。
     */
    public static String[] filterBindedProperties(Object bindedObj, ServletRequest request){
        Enumeration<String> enu=request.getParameterNames();
        List<String> properties=new ArrayList<String>();
        if(enu==null){
            return null;
        }
        PropertyDescriptor[] pds=PropertyUtils.getPropertyDescriptors(bindedObj);
        Map<String,String> pdMap=new HashMap<String,String>();
        for(PropertyDescriptor pd:pds){
            pdMap.put(pd.getName(), pd.getName());
        }
        while(enu.hasMoreElements()){
            String p=enu.nextElement();
            if(pdMap.get(p)!=null){
                properties.add(p);
            }
        }
        String[] result=new String[]{};
        result=properties.toArray(result);
        return result;
    }
    
    public static <T> T[] splitAndCast(String aStr,String splitter,Class<T> typeClz){
        if(StringUtils.isEmpty(aStr)){
            return (T[])Array.newInstance(typeClz, 0);
        }
        if(splitter==null){
            splitter=",";
        }
        String[] strArray=StringUtils.split(aStr, splitter);
        T[] result=(T[])Array.newInstance(typeClz, strArray.length);
        int index=0;
        for(String str:strArray){
            T convertVal=(T)ConvertUtils.convert(str, typeClz);
            result[index]=convertVal;
            index++;
        }
        return result;
    }
}
