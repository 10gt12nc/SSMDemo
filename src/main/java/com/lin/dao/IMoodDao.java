package com.lin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lin.domain.Mood;

@Repository
public interface IMoodDao {

	List<Mood> findAllMsg();
	
	boolean update (@Param("mood") Mood mood);
	
	Mood findById(Integer id);

}
