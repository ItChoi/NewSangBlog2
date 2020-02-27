package com.blog.newsangblog2.manager.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/manager")
@Controller
public class ManagerMainController {
	
	@RequestMapping(value= {"/", ""})
	public String managerMainPage() {
		
		return "/manager/main/index";
	}
	
}
