package com.lin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lin.domain.User;
import com.lin.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userservice;
	

}
