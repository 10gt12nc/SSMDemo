package com.lin.mq;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.lin.dto.MoodDTO;


@Component(value="MoodConsumer")
public class MoodConsumer  implements MessageListener{
	
	@Resource
	private RedisTemplate redisTemplate;

	private static final String PRAISE_HASH_KEY = "springmv.mybatis.boot.mood.id.list.key";
	
	private Logger log= Logger.getLogger(this.getClass());
	
	//自動調用監聽器
	@Override
	public void onMessage(Message message) {

		try {
			MoodDTO moodDTO=  (MoodDTO)((ActiveMQObjectMessage)message) .getObject();
			
			
				String mdtoidstr = Integer.toString(moodDTO.getId());
				String mdtouseridstr = Integer.toString(moodDTO.getUserid());
			
			//存放Set
			redisTemplate.opsForSet().add(PRAISE_HASH_KEY, mdtoidstr);
			//存放Set
			redisTemplate.opsForSet().add(mdtoidstr, mdtouseridstr);
			
			log.info("消費者---->>>> 用戶 ID: "+mdtouseridstr+"給訊息 ID: "+mdtoidstr+"按了爛");
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
