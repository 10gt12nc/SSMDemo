package com.lin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.lin.test.BaseTest;

public class SpringTest extends BaseTest {

	// @Transactional //標明此方法需使用事務
	@Test
	public void springTest() {
		// 要public 不能void  say() 也要加註解 
		//整個專案 run as  junit test
		String path = "classpath*:/app-context.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(path);
		SpringTest st = (SpringTest) ac.getBean(SpringTest.class);
		st.say();
	}

	@Test
	public void say() {
		System.out.println("Hi~~~");

	}

}
