package com.lin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lin.domain.UserMoodPraiseRel;

@Repository
public interface IUserMoodPraiseRelDao {

	//保存按讚紀錄
	boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel umpRel );
	
}
