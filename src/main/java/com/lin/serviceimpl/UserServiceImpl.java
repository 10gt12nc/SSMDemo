package com.lin.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.IUserDao;
import com.lin.domain.User;
import com.lin.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userdao;
	
	
	public List<User> findAll() {
		return userdao.findAll();
	}

}
