package com.lin.service;

import java.util.List;

import com.lin.domain.User;
import com.lin.dto.MoodDTO;
import com.lin.dto.UserDTO;

public interface IUserService {

	UserDTO findUserById(Integer id);
	
	
}
