package com.fx.oss.utils;
/**
 * 运营系统枚举接口。
 * 
 * @author huangwieyong
 * @version 1.0 2013/6/22
 *
 */
public interface OssEnum {
	/**
	 * 
	 * 消息类型。
	 *
	 */
	enum MSG implements OssEnum{
		MSG_SUCCESS,
		MSG_FAIL,
		MSG_PROMPT,
		MSG_ERROR,
		MSG_WARN
	}
}
