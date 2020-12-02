package com.lin.mq;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.lin.dto.MoodDTO;

@Component
public class MoodProducer {
	
	@Resource
	private JmsTemplate jmsTemplate;
	
	private Logger log= Logger.getLogger(this.getClass());
	/*
	 * 1.指定列隊名稱
	 * 2.mood實體
	 */
	public void sendMessage(Destination destination , final MoodDTO mood){
		//紀錄日誌
		log.info("生產者---->>>> 用戶 ID: "+mood.getUserid()+"給訊息 ID: "+mood.getId()+"按了爛");
		//序列化接口 , 發送
		jmsTemplate.convertAndSend(destination , mood);
		
	}
	
	
	
	
	
	
	

	
	
}
