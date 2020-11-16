package com.lin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HI {
	
	@RequestMapping("/hI")
	public String hI(){
		System.out.println("MVC Hi");
		return "hi" ;
	}
	
	

}
