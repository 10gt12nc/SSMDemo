package com.lin.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
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
import com.lin.mq.MoodProducer;
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

	private static final String PRAISE_HASH_KEY = "springmv.mybatis.boot.mood.id.list.key";
	
	@Resource
	private MoodProducer moodProducer;
	
	//列隊
	private static Destination destination = new ActiveMQQueue("lin.queue.praise"); 
	
	
	
	

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

			// 書中寫這mooddtolist.add(mooddto);
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
		UserMoodPraiseRel umpRel = new UserMoodPraiseRel();
		umpRel.setUserid(userid);
		umpRel.setMoodid(moodid);
		umpreldao.save(umpRel);

		// 更新爛數
		Mood mood = this.findById(moodid);
		mood.setPraisenum(mood.getPraisenum() + 1);
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

	
		/*
		 * 修改為異步處理
		 * 
		 */
	@Override
	public boolean praiseMoodForRedis(Integer userid, Integer moodid) {
		
		
		MoodDTO moodDTO= new MoodDTO();
		moodDTO.setUserid(userid);
		moodDTO.setId(moodid);
		
		//發送消息
		moodProducer.sendMessage(destination, moodDTO);		
		
		return false;
	}

	@Override
	public List<MoodDTO> findAllForRedis() {

		List<Mood> moodlist = mooddao.findAllMsg();
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
			/*爛的總數= DB+redis
			 * size() 变量中值的"个数",型態 long ; members() 获取变量中的"值"
			 */
			Integer redisPraisenum=redisTemplate.opsForSet().size(Integer.toString(mood.getId())).intValue();
			mooddto.setPraisenum(mood.getPraisenum() + redisPraisenum);
			// id查用戶
			User user = userdao.findUserById(mood.getId());
			mooddto.setUsername(user.getName());
			mooddto.setUseraccount(user.getAccount());
			mooddtolist.add(mooddto);
		}
		return mooddtolist;
	}

}
