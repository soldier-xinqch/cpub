package com.ht.court.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.model.TpOrg;
import com.hx.rest.RestResponse;
import com.hx.service.TpOrgService;
/**
 * 法院REST API控制器。
 * <p>
 * 提供法院列表rest数据接口。
 * 
 * @author huangweiyong
 * @version 2.0.0.0 2014/4/8
 *
 */
@Controller
@RequestMapping(value="/restapi/v1/court")
public class CourtRestController {
	
	@Autowired
	private TpOrgService tpOrgService;
	
	/**
	 * 获取法院列表。
	 * 
	 * @param treeType 树类型。
	 * @param identifyCode 组织标识码，可以是<code>SYS_DCS.SYS_DCS_COURTCAT</code>枚举类型。
	 * 
	 * @return 返回法院列表。
	 */
	@RequestMapping(value="/list/treeType/{treeType}")
	public @ResponseBody RestResponse<List<TpOrg>> index(@PathVariable String treeType,@RequestParam(required=false) String identifyCode){
		List<TpOrg> orgs=tpOrgService.findAllOrgs(treeType,identifyCode);
		return RestResponse.success(orgs);
	}
	
	@RequestMapping(value="/{id}/children")
	public @ResponseBody RestResponse<List<TpOrg>> getChildOrgs(@PathVariable String id){
		List<TpOrg> orgs=tpOrgService.findChildrenByParent(id);
		return RestResponse.success(orgs);
	}
	
	@RequestMapping(value="/{id}")
	public @ResponseBody RestResponse<TpOrg> get(@PathVariable String id){
		TpOrg org=tpOrgService.get(id);
		return RestResponse.success(org);
	}
	
	@RequestMapping(value="/root/treeType/{treeType}")
	public @ResponseBody RestResponse<TpOrg> getRoot(@PathVariable String treeType){
		TpOrg org=tpOrgService.findRootOrg(treeType);
		return RestResponse.success(org);
	}
	
	@RequestMapping(value="/name/{courtName}")
	public @ResponseBody RestResponse<List<TpOrg>> getCourtNameByStr(@PathVariable String courtName){
		List<TpOrg> org=tpOrgService.getTpOrgsByStr(courtName);
		return RestResponse.success(org);
	}
}
