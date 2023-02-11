package com.fredo.iocxml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    /**
     * 本模块会进行以下测试：
     *  1.bean的获取
     *  2.依赖注入
     *  3.特殊值处理
     *  4.bean的作用域
     *  5.bean的生命周期
     *  6.基于XML的自动装配
     */

    // 1.bean的获取
    @Test
    public void test1(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.根据ID获取bean
        User user1 = (User) ioc.getBean("user");
        System.out.println("根据ID获取的bean："+ user1);

        // 2.根据类型获取bean
        User user2 = ioc.getBean(User.class);
        System.out.println("根据类型获取的bean："+ user2);

        // 3.根据ID和类型获取bean
        User user3 = ioc.getBean("user", User.class);
        System.out.println("根据ID和类型获取bean："+ user3);

        /**
         * 注意：
         *  根据类型获取bean时(根据ID和类型获取bean，不会报错)，相同类型只能配置一个！！！
         *  否则报错：No qualifying bean of type 'com.fredo.iocxml.User' available:
         *          expected single matching bean but found 2: user,user2
         *
         */
    }
}
