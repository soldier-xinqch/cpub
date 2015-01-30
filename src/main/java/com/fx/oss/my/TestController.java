package com.fx.oss.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/courtPub/voc")
public class TestController {
	@RequestMapping("/{id}")
	public String index(@PathVariable String id){
		return "list";
	}
}
