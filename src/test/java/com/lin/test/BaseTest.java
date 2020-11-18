package com.lin.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//spring添加junit測試環境
@RunWith(SpringJUnit4ClassRunner.class)
//加載配置文件
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class BaseTest {

}
