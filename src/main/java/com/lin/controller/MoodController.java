package com.lin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.dto.MoodDTO;
import com.lin.service.IMoodService;

@Controller
@RequestMapping("/mood")
public class MoodController {
 
	@Resource
	private IMoodService  moodservice;
	
	@RequestMapping("/findAll")
	public String findAll(Model model){
		//Model封裝了應用程序的數據和一般他們會組成的POJO。
		List<MoodDTO> mooddtolist =moodservice.findAllMsg();
		model.addAttribute("moods",mooddtolist);
		return "mood";// jsp頁面
		
	}
	
	
	
	
}
