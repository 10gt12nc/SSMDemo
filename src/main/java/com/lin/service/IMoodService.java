package com.lin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lin.domain.Mood;
import com.lin.dto.MoodDTO;

public interface IMoodService {

	List<MoodDTO> findAllMsg();
	//傳統 點 爛
	boolean praiseMood(Integer userid,Integer moodid);

	boolean update(@Param("mood") Mood mood);

	Mood findById(Integer id);

}
