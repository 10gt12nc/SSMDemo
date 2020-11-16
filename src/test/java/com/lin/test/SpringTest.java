package com.lin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SpringTest {

	@Test
	public void testSpring() {

		ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
		SpringTest springTest = (SpringTest) ac.getBean("SpringTest");
		springTest.sayHi();
	}

	public void sayHi() {
		System.out.println("HI~~~~~~~~~");

	}

}
