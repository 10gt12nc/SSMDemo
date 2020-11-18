package com.lin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


public class SpringTest extends BaseTest {
	
	
	@Test
	void springTest() {
		String path="classpath*:/app-context.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(path);
		SpringTest st=(SpringTest) ac.getBean(SpringTest.class);
		st.say();
	}

	void say() {
		System.out.println("Hi~~~");

	}

}
