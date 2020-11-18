package com.lin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.domain.User;
import com.lin.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource
	private IUserService userservice;
	
	@GetMapping("/findAll")
	public String finAll(){
		List<User> userlist= userservice.findAll();
		for(User user:userlist){
			System.out.println(user.getId());
			System.out.println(user.getName());
		}
		return "hi";
	}

}
