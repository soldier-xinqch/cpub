package com.ht.court.common;

/**
 * @author xinqichao
 *  
 *  存储指令枚举类
 * 
 */
public interface CpubEnum {
		
	public enum STREAM_ACTION implements CpubEnum {

		STOP("stop"),
		STORE("store"),
		SEARCH("search");
		private String value;

		STREAM_ACTION(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum VOD_STATUS implements CpubEnum {
	
		SUNCCESS("SUNCCESS"),
		FAIL("FAIL");
		private String value;
	
		VOD_STATUS(String value) {
			this.value = value;
		}
	
		public String getValue() {
			return value;
		}
	}
}