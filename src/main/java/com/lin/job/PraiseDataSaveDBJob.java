package com.lin.job;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
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
@Lazy(false)//對該Bean在啟動的時候就加載
public class PraiseDataSaveDBJob {

	@Resource
	private RedisTemplate redisTemplate; 
	
	private static final String PRAISE_HASH_KEY ="springmv.mybatis.boot.mood.id.list.key";
	
	@Resource
	private UserMoodPraiseRelServiceImpl userMoodPraiseRelService;
	
	@Resource
	private MoodServiceImpl  moodService;
	
	private Logger log= Logger.getLogger(this.getClass());
	
	
	//定時任務
	// cron定時表達式 ,格式: 秒 分 時 日  月 周 年
	//每30秒執行一次
	@Scheduled(cron = "30 * * * * *") 
	private void savePraiseDataToDBJob2() {
		
		//1.redis緩存中,所有被點爛的文章的id (.members 值)
		//第一層
		Set<String> moods=redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
		
		log.info("--第一層--redis緩存中,所有被點爛的文章的id-->>moods>>--"+moods.toString()); //[2, 1]
	
		if(CollectionUtils.isEmpty(moods)){
			return;
		}
		//第二層
		for(String moodId : moods) {
			log.info("--第二層--被按的按文章-->>moodId>>--"+moodId.toString());
			if(redisTemplate.opsForSet().members(moodId) == null) {
				continue;
			}else {
				//2. 獲取所有按爛 ID
				Set<String> userIds=redisTemplate.opsForSet().members(moodId); 
				
				log.info("--按文章的用戶-->>userIds>>--"+userIds.toString());
				
				if(CollectionUtils.isEmpty(userIds)) {
					log.info("---空--->>return>>-----");
					return;
				}else {
					//3.循環保存 moodid 和 userid
					
					for(String userId : userIds) {
						
						UserMoodPraiseRel umpRel=new UserMoodPraiseRel();
						
//						umpRel.setMoodid(moodId);
//						umpRel.setUserid(userId);
						umpRel.setMoodid(Integer.valueOf(moodId));
						umpRel.setUserid(Integer.valueOf(userId));
						
						log.info("---save用戶與文章關聯表--->>umpRel>>----"+umpRel.toString());
						userMoodPraiseRelService.save(umpRel);
					}
					//4.更新
					
					Mood mood=moodService.findById(Integer.valueOf(moodId));
					log.info("--findById--->>mood>>-----------------------"+mood.toString());
					//總數=redis+db
					Integer redisPraisenum=redisTemplate.opsForSet().size(moodId).intValue();
					log.info("----總數=redis+db---->>redisPraisenum>>----"+redisPraisenum.toString());
					mood.setPraisenum(mood.getPraisenum()+redisPraisenum);
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
