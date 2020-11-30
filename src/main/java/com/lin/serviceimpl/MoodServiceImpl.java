package com.lin.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lin.dao.IMoodDao;
import com.lin.dao.IUserDao;
import com.lin.dao.IUserMoodPraiseRelDao;
import com.lin.domain.Mood;
import com.lin.domain.User;
import com.lin.domain.UserMoodPraiseRel;
import com.lin.dto.MoodDTO;
import com.lin.service.IMoodService;

@Service
public class MoodServiceImpl implements IMoodService {

	@Resource
	private IMoodDao mooddao;

	@Resource
	private IUserDao userdao;
	
	@Resource
	private IUserMoodPraiseRelDao umpreldao;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	private static final String PRAISE_HASH_KEY ="springmv.mybatis.boot.mood.id.list.key";

	@Override
	public List<MoodDTO> findAllMsg() {
		List<Mood> moodlist = mooddao.findAllMsg();
		return converModel2DTO(moodlist);
	}

	private List<MoodDTO> converModel2DTO(List<Mood> moodlist) {

		if (CollectionUtils.isEmpty(moodlist)) {
			return Collections.EMPTY_LIST;
		}
		List<MoodDTO> mooddtolist = new ArrayList<>();
		for (Mood mood : moodlist) {
			MoodDTO mooddto = new MoodDTO();
			mooddto.setId(mood.getId());
			mooddto.setContent(mood.getContent());
			mooddto.setUserid(mood.getUserid());
			mooddto.setPublishtime(mood.getPublishtime());
			mooddto.setPraisenum(mood.getPraisenum());

			//書中寫這mooddtolist.add(mooddto);
			User user = userdao.findUserById(mood.getId());
			mooddto.setUsername(user.getName());
			mooddto.setUseraccount(user.getAccount());
			
			mooddtolist.add(mooddto);
		}

		return mooddtolist;

	}

	@Override
	public boolean praiseMood(Integer userid, Integer moodid) {
		// 保存關聯
		UserMoodPraiseRel umpRel=new UserMoodPraiseRel();
		umpRel.setUserid(userid);
		umpRel.setMoodid(moodid);
		umpreldao.save(umpRel);
		
		//更新爛數
		Mood mood= this.findById(moodid);
		mood.setPraisenum(mood.getPraisenum()+1);
		this.update(mood);
		
		return Boolean.TRUE;
	}

	@Override
	public boolean update(Mood mood) {
		// TODO Auto-generated method stub
		return mooddao.update(mood);
	}

	@Override
	public Mood findById(Integer id) {
		// TODO Auto-generated method stub
		return mooddao.findById(id);
	}


	@Override
	public boolean praiseMoodForRedis(Integer userid, Integer moodid) {

		//存放set集合
		redisTemplate.opsForSet().add(PRAISE_HASH_KEY , moodid);
		//存放set
		redisTemplate.opsForSet().add(moodid, userid);
		return false;
	}

	@Override
	public List<MoodDTO> findAllForRedis() {
		
		List<Mood> moodlist=mooddao.findAllMsg();
		if(CollectionUtils.isEmpty(moodlist)){
			return Collections.EMPTY_LIST;
		}
		List<MoodDTO> mooddtolist = new ArrayList<>();
		for (Mood mood : moodlist) {
			MoodDTO mooddto = new MoodDTO();
			mooddto.setId(mood.getId());
			mooddto.setContent(mood.getContent());
			mooddto.setUserid(mood.getUserid());
			mooddto.setPublishtime(mood.getPublishtime());
			//爛總數= DB+redis
			mooddto.setPraisenum(mood.getPraisenum()+redisTemplate.opsForSet().size(mood.getId()).intValue());
			
			//id查用戶
			User user = userdao.findUserById(mood.getId());
			mooddto.setUsername(user.getName());
			mooddto.setUseraccount(user.getAccount());
			
			mooddtolist.add(mooddto);
		}

		return mooddtolist;
		
		
	}

}
