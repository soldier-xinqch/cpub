package com.fx.oss.utils;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

/**
 * 分页显示、查询和处理工具类.
 * 
 * @author Weiyong Huang
 */
public class PaginateUtil {
    public static final String MODEL_ATTRIBUTE_NAME_FOR_PAGER="pagi";
    public static final String PAGINATE_PARAM_PREFIX="pager.";
    public static final String START_INDEX="offset";
    public static final String PAGE_SIZE="pageSize";
    public static final String PAGINATE_PARAM_START_INDEX=PAGINATE_PARAM_PREFIX + START_INDEX;
    public static final String PAGINATE_PARAM_PAGE_SIZE=PAGINATE_PARAM_PREFIX + PAGE_SIZE;
    public static final String QUERY_PARAM_PREFIX="qp_";
    
    static{
        ConvertUtils.register(new OssDateConverter(), Date.class);
    }
    
    /**
     * 往spring的model中设置page对象。
     * 
     * @param model
     * @param pageObj 
     */
    public static void addPager(Model model,Object pageObj){
        model.addAttribute(MODEL_ATTRIBUTE_NAME_FOR_PAGER, pageObj);
    }
    
    /**
     * 从request中获取所有分页参数。
     * <p>
     * 分页参数的参数名称都以<code>PAGINATE_PARAM_PREFIX</code>指定的字符串打头。
     * @param request
     * @return 
     */
    public static Map<String,Object> getPaginateParams(HttpServletRequest request){
        return WebUtils.getParametersStartingWith(request, PAGINATE_PARAM_PREFIX);
    }
    
    /**
     * 获取分页相关查询参数。
     * 
     * @param request
     * @return 
     */
    public static Map<String,String> getPaginateQueryParams(HttpServletRequest request){
        Map<String,Object> params=getPaginateParams(request);
        Map<String,String> queryParams=new HashMap<String,String>();
        if(params.isEmpty()){
            return queryParams; 
        }
        Set<String> keys=params.keySet();
        Iterator<String> its=keys.iterator();
        while(its.hasNext()){
            String key=its.next();
            if(key.equals(START_INDEX) || key.equals(PAGE_SIZE)){
                continue;
            }
            queryParams.put(PAGINATE_PARAM_PREFIX + key, (String)params.get(key));
        }
        return queryParams;
    }
    /**
     * 从request中获取记录的开始索引。
     * @param request
     * @return 
     */
    public static int getStartIndex(HttpServletRequest request){
        String startIndex=request.getParameter(PAGINATE_PARAM_START_INDEX);
        return startIndex==null ? 0 : Integer.parseInt(startIndex);
    }
    
    /**
     * 从request中获取每页纪录数量。
     * 
     * @param request
     * @return 
     */
    public static int getPageSize(HttpServletRequest request,int defaultPageSize){
        String pageSize=request.getParameter(PAGINATE_PARAM_PAGE_SIZE);
        return pageSize==null ? defaultPageSize : Integer.parseInt(pageSize);
    }
    
    /**
     * 从request中获取查询参数。
     * 
     * @param request
     * @param paramName 除去<code>PAGINATE_PARAM_PREFIX</code>前缀的参数名。
     * @return 
     */
    public static String getQueryParam(HttpServletRequest request,String paramName){
        return request.getParameter(PAGINATE_PARAM_PREFIX + paramName);
    }
    /**
     * 将查询参数map中的参数值进行类型转换。
     * 
     * @param origQueryParams 待转换的查询参数map。
     * @param paramDataTypeMap 参数数据类型映射map。
     */
    public static void convertQueryParams(Map<String,Object> origQueryParams,Map<String,Class> paramDataTypeMap){
        if(CollectionUtils.isEmpty(paramDataTypeMap)){
            return;
        }
        for(Map.Entry<String,Class> entry:paramDataTypeMap.entrySet()){
            String paramName=entry.getKey();
            Class paramType=entry.getValue();
            Object origVal=origQueryParams.get(paramName);
            if(origVal!=null){
                Object convertVal=ConvertUtils.convert(origVal, paramType);
                origQueryParams.put(paramName, convertVal);
            }
        }
    }
//    public static void setQueryParam(HttpServletRequest request,String paramName,String paramValue){
//        request.setAttribute(PAGINATE_PARAM_PREFIX + paramName, paramValue);
//    }
}
