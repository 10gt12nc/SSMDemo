package com.lin.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lin.dto.MoodDTO;
import com.lin.service.IMoodService;

@Controller
@RequestMapping("/mood")
public class MoodController {

	@Resource
	private IMoodService moodservice;

	// Model封裝了應用程序的數據和一般他們會組成的POJO。
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<MoodDTO> mooddtolist = moodservice.findAllMsg();
		model.addAttribute("moods", mooddtolist);
		return "mood";// jsp頁面
	}

	//--------------------------------------------
	
	//傳統按爛
	@GetMapping(value = "/{moodid}/praise")
	private String praise(Model model, 
			@PathVariable(value = "moodid") Integer moodid,
			@RequestParam(value = "userid") Integer userid) {
		
		boolean ispraise=moodservice.praiseMood(userid, moodid);
		List<MoodDTO> mooddtolist=moodservice.findAllMsg();
		model.addAttribute("moods", mooddtolist);
		model.addAttribute("ispraise", ispraise);
		return "mood";// jsp頁面

	}
	

	
	//--------
	
	
	@GetMapping(value = "/{moodid}/praiseForRedis")
	private String praiseForRedis(
			Model model, 
			@PathVariable(value = "moodid") Integer moodid,
			@RequestParam(value = "userid") Integer userid) {
	
		userid=null;
		//使用亂數生成多個用戶ID
		Random random=new Random();
		userid =Integer.valueOf(random.nextInt(100)+1);//0(含)~n(不含)
		boolean ispraise=moodservice.praiseMoodForRedis(userid, moodid);
		//查詢訊息
		List<MoodDTO> mooddtolist=moodservice.findAllForRedis();
		model.addAttribute("moods", mooddtolist);
		model.addAttribute("ispraise", ispraise);
		
		return "mood";// jsp頁面

	}
	
	

}
