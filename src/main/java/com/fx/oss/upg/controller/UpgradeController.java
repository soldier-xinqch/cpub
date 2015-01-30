package com.fx.oss.upg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/upg/upgrade")
public class UpgradeController {
	
	@RequestMapping(value="/list")
	public String list(){
		return "list";
	}
	
}
