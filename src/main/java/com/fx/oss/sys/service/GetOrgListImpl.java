package com.fx.oss.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;

@Service("getOrgList")
public class GetOrgListImpl implements GetOrgList {

	private static final Logger logger = LoggerFactory
			.getLogger(GetOrgListImpl.class);
	@Autowired
	private TpOrgService tpOrgService;
	
	/**
	 * 得到所有的自组织ID
	 * 
	 * @param id     父组织ID
	 * @return
	 */
	public List<String> getOrgId(String id) {
		List<String> childOrgs = new ArrayList<String>();
		List<TpOrg> tpOrgIds = this.getOrgIdsByParentId(id);
		for(TpOrg tpOrg : tpOrgIds){
			childOrgs.add(tpOrg.getId());
		}
		return childOrgs;
		
	}

	public List<TpOrg> getOrgName(String id) {
		List<TpOrg> childOrgNames = new ArrayList<TpOrg>();
		List<TpOrg> tpOrgIds = this.getOrgIdsByParentId(id);
		for(TpOrg tpOrg : tpOrgIds){
			childOrgNames.add(tpOrg);
		}
		return childOrgNames;
	}
	
	public List<TpOrg> getTpOrgs(String id) {
		List<TpOrg> tpOrgIds = this.getOrgIdsByParentId(id);
		return tpOrgIds;
	}
	
	private List<TpOrg> getOrgIdsByParentId(String courtId) {
		List<TpOrg> tpOrgs = new ArrayList<TpOrg>();
		List<String> childOrgs = new ArrayList<String>();
		TpOrg tpOrgById = tpOrgService.get(courtId);
		tpOrgs.add(tpOrgById);
		String levelFalg = tpOrgService.get(courtId).getIdentifyCode();
		if ("SYS_DCS_COURTCAT_HIGH".equals(levelFalg)) {
			logger.debug("此用户为高院用户将获得省下所有子院");
			List<TpOrg> tpOrgss = tpOrgService.findSubsByParent(courtId);
			for (TpOrg tpOrg : tpOrgss) {
				tpOrgs.add(tpOrg);
				childOrgs.add(tpOrg.getId());
			}
			tpOrgss = tpOrgService.findOrgIdsByParents(childOrgs);
			for (TpOrg tpOrg : tpOrgss) {
				tpOrgs.add(tpOrg);
			}
		}
		if ("SYS_DCS_COURTCAT_MID".equals(levelFalg)) {
			logger.debug("此用户为中级院用户将获得它所属的基层院");
			List<TpOrg> tpOrgss = tpOrgService.findChildrenByParent(courtId);
			for (TpOrg tpOrg : tpOrgss) {
				tpOrgs.add(tpOrg);
			}
			if ("SYS_DCS_COURTCAT_BASE".equals(levelFalg)) {
				logger.debug("此用户为基层级院用户");
			}
		}
		return tpOrgs;
	}
}
