package com.fredo.spring6;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    // 测试创建对象
    @Test
    public void test1(){
        // 1.获取spring的配置文件，并创建对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 2.获取创建的对象
        // 此处name必须与配置文件中的id一致，否则报错：No bean named 'user1' available
//        User user = (User) ioc.getBean("user1");
        User user = (User) ioc.getBean("user");
        // 3.使用对象调用方法
        user.add();
    }
}
