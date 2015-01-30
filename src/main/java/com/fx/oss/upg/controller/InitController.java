package com.fx.oss.upg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 初始设置控制器。
 * <p>
 * 初始化设置ip，数据同步等。
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/upg/init")
public class InitController {
	/**
	 * 显示当前配置。
	 * 
	 * @return
	 */
	@RequestMapping(value="/inputs")
	public String inputs(){
		return "inputs";
	}
	
	
}
