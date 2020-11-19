package com.lin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lin.domain.Mood;

@Repository
public interface IMoodDao {

	List<Mood> findAllMsg();

}
