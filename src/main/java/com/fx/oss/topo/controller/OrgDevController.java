package com.fx.oss.topo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织与设备控制器。
 * <p>
 * 显示组织和设备列表。
 * 
 * @author
 *
 */
@Controller
@RequestMapping(value="/topo/orgDev")
public class OrgDevController {
	@RequestMapping(value="/list")
	public String list(){
		return "list";
	}
}
