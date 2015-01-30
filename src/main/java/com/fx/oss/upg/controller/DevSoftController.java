package com.fx.oss.upg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设备软件映射控制器。
 * <p>
 * 显示设备和软件映射关系。
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/upg/devSoft")
public class DevSoftController {
	/**
	 * 显示设备和软件的映射关系。
	 * 
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(){
		return "list";
	}
}
