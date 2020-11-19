package com.lin.service;

import java.util.List;

import com.lin.domain.Mood;
import com.lin.dto.MoodDTO;

public interface IMoodService {

	
	List<MoodDTO> findAllMsg();
	
	
}
