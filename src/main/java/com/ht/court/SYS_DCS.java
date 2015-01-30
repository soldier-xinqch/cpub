package com.ht.court;

public interface SYS_DCS {
	/**
	 * 法院类型。
	 * 
	 * @author huangweiyong
	 *
	 */
	public enum SYS_DCS_COURTCAT implements SYS_DCS{
		SYS_DCS_COURTCAT_HIGHEST,//最高法院
		SYS_DCS_COURTCAT_HIGH,//高级法院
		SYS_DCS_COURTCAT_MID,//中级法院
		SYS_DCS_COURTCAT_BASE//基层法院
	}
}
