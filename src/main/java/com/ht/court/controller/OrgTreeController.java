package com.ht.court.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.GetOrgList;
import com.ht.court.util.FunctionUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;


/**
 * 组织树controller。
 * 
 * @author huangweiyong
 * @version 2.0.0.0 2014/03/01
 *
 */
@Controller
@RequestMapping("/courtPub/vod")
public class OrgTreeController {
	
//	private static final Logger logger=LoggerFactory.getLogger(OrgTreeController.class);
	
	@Autowired
	private TpOrgService tpOrgService;
	@Autowired
	private GetOrgList getOrgList;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true","false",true));
	}
	
	@RequestMapping(value="/courtTree" )
//			,produces="application/json;charset=utf-8")
    public @ResponseBody List<TpOrg> tree(HttpServletRequest request ,
    				@RequestParam(required=false) String treeType, 
    				@RequestParam(required=false) String id,@RequestParam(required=false) Boolean expandAll){
		List<TpOrg> orgs=new ArrayList<TpOrg>();
		User user = (User) FunctionUtil.getUserDetails();
        orgs = (List<TpOrg>) request.getSession().getAttribute("orgNames");
        return orgs;
    }
}