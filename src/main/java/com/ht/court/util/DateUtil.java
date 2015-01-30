package com.ht.court.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author xinqichao
 * 处理实际那的工具类
 */
public class DateUtil {

	public 	String[] formatDate(Date date,String dateFormat,String splitParam){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String processDate = format.format(date);
		String[] splited = processDate.split(splitParam);
		return splited; 
	} 
	//得到日期
	public String getDay(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String processDate = format.format(date); 
		String day = processDate.substring(8, 11); 
		return day;	
	}
	
//	public static void main(String[] args) {
//		DateUtil dateUtil = new DateUtil();
//		System.out.println(dateUtil.getDay(new Date()));
//		
//	}
}
