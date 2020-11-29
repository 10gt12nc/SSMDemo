package com.lin.job;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lin.domain.Mood;
import com.lin.domain.UserMoodPraiseRel;
import com.lin.serviceimpl.MoodServiceImpl;
import com.lin.serviceimpl.UserMoodPraiseRelServiceImpl;



/**
 * Quartz定時器
 * 
 */
@Component
@Configurable // = XML文件可被spring掃描 初始化
@EnableScheduling //啟動"任務"的支持
public class PraiseDataSaveDBJob {

	@Resource
	private RedisTemplate redisTemplate; 
	
	private static final String PRAISE_HASH_KEY ="springmv.mybatis.boot.mood.id.list.key";
	
	@Resource
	private UserMoodPraiseRelServiceImpl userMoodPraiseRelService;
	
	@Resource
	private MoodServiceImpl  moodService;
	
	//定時任務
	//格式: 秒 分 時 日  月 周 年
	//每10秒執行一次
	@Scheduled(cron = "*/10 * * * * * *") 
	private void savePraiseDataToDBJob2() {
		
		//1.redis緩存 所有被點爛的文章的id
		Set<Integer> moods=redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
		
		if(CollectionUtils.isEmpty(moods)){
			return;
		}
		
		for(Integer moodId : moods) {
			
			if(redisTemplate.opsForSet().members(moodId) == null) {
				continue;
			}else {
				//2. 獲取所有按爛 ID
				Set<Integer> userIds=redisTemplate.opsForSet().members(moodId); 
				if(CollectionUtils.isEmpty(userIds)) {
					return;
				}else {
					//3.循環保存 moodid 和 userid
					for(Integer userId : userIds) {
						
						UserMoodPraiseRel umpRel=new UserMoodPraiseRel();
						
						umpRel.setMoodid(moodId);
						umpRel.setUserid(userId);
						userMoodPraiseRelService.save(umpRel);
					}
					//4.更新
					Mood mood=moodService.findById(moodId);
					//總數=redis+db
					mood.setPraisenum(mood.getPraisenum()+redisTemplate.opsForSet().size(moodId).intValue());
					moodService.update(mood);
					//5.清除緩存
					redisTemplate.delete(moodId);
					
				}
			}	
		}
		
		////6.清除緩存
		redisTemplate.delete(PRAISE_HASH_KEY);
	}


}
