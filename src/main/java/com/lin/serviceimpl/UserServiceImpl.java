package com.lin.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.IUserDao;
import com.lin.domain.User;
import com.lin.dto.UserDTO;
import com.lin.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userdao;
	
	
	@Override
	public UserDTO findUserById(Integer id) {

		User user = userdao.findUserById(id);
		
		return converModel2DTO(user);
	}
	
	private UserDTO converModel2DTO( User user){
		
		UserDTO userDTO =new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAccount(user.getAccount());
		return userDTO;
		
	}



}
