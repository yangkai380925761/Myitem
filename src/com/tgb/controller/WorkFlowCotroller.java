package com.tgb.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/workflow")
public class WorkFlowCotroller {
	
	@RequestMapping("/index1")
	public String index1(){
		return "/jsp/workflow/processfinition";
	}

	@RequestMapping("/index")
	public String index(){
		return "/jsp/workflow/processfinition";
	}
	
}
