package com.lin.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



//spring添加junit測試環境
@RunWith(SpringJUnit4ClassRunner.class)
//加載配置文件
@ContextConfiguration(locations = {"classpath:app-context.xml"})
@WebAppConfiguration() //設定web專案的環境，如果是Web專案，必須配置該屬性，否則無法獲取
public class BaseTest {

}
