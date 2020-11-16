package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//控制器
@Controller
//處理請求地址映射
@RequestMapping("/HI")
public class HI {
	//組合註解 = @RequestMapping(method=RequestMethod.GET)
	@GetMapping("/haha")
	public String hI(){
		System.out.println("MVC Hi");
		return "hi" ;
	}
	
	

}
