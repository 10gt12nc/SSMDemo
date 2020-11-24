package com.lin.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends BaseTest {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void  testRedis(){
		
		redisTemplate.opsForValue().set("name", "AAAAAA");
		String name=(String) redisTemplate.opsForValue().get("name");
		System.out.println("============="+name);
		
		
	}
	
	
	
	
	

}
