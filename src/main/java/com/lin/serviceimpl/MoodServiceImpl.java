package com.lin.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lin.dao.IMoodDao;
import com.lin.dao.IUserDao;
import com.lin.domain.Mood;
import com.lin.domain.User;
import com.lin.dto.MoodDTO;
import com.lin.service.IMoodService;

@Service
public class MoodServiceImpl implements IMoodService {

	@Resource
	private IMoodDao mooddao;

	@Resource
	private IUserDao userdao;

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

}
