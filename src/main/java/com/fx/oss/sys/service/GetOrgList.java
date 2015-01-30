package com.fx.oss.sys.service;

import java.util.List;

import com.hx.model.TpOrg;

public interface GetOrgList {
	
	/**
	 * 获得所有子组织的ID
	 * @param id
	 * @return
	 */
	List<String> getOrgId(String id );
	
	/**
	 * 获得所有自及子组名称
	 * @param id 
	 * @return
	 */
	List<TpOrg> getOrgName(String id );
	
	/**
	 * 获得所有自及子组织信息
	 * @param id
	 * @return
	 */
	List<TpOrg> getTpOrgs(String id );
}
