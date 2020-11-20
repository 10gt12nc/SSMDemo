package com.lin.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lin.dao.IUserMoodPraiseRelDao;
import com.lin.domain.UserMoodPraiseRel;
import com.lin.service.IUserMoodPraiseRelService;


@Service
public class UserMoodPraiseRelServiceImpl implements IUserMoodPraiseRelService {

	@Resource
	private IUserMoodPraiseRelDao  umpReldao;
	
	
	@Override
	public boolean save(UserMoodPraiseRel umpRel) {
		return umpReldao.save(umpRel);
	}

}
