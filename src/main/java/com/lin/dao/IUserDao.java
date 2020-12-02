package com.lin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lin.domain.User;

@Repository
public interface IUserDao {

	User findUserById(Integer id);
	
}
