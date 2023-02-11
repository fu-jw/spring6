package com.fredo.iocxml;

import com.fredo.iocxml.bean.UserDao;
import com.fredo.iocxml.di.Book;
import org.junit.jupiter.api.BeforeAll;
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
         */
        // 4.根据接口的实现类获取的bean
        UserDao userDao1 = ioc.getBean(UserDao.class);
        System.out.println("根据接口的实现类获取的bean："+ userDao1);

        // 5.根据接口的实现类获取的bean，多个实现类时，报错
//        UserDao userDao2 = ioc.getBean(UserDao.class);
//        System.out.println("根据接口的实现类获取的bean："+ userDao2);
        /**
         * 同一个接口，不能配置两个实现类，
         *  否则报错：No qualifying bean of type 'com.fredo.iocxml.bean.UserDao' available:
         *          expected single matching bean but found 2: userDao,personDao
         *  【原因】：根据类型来获取bean时，在满足bean唯一性的前提下，
         *          其实只是看：『对象 instanceof 指定的类型』的返回结果，
         *          只要返回的是true就可以认定为和类型匹配，能够获取到。
         */

    }

    // 2.依赖注入--setter
    @Test
    public void test2(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.依赖注入--setter
        Book book1 = (Book) ioc.getBean("book1");
        System.out.println(book1);

        // 2.依赖注入--构造器
        Book book2 = (Book) ioc.getBean("book2");
        System.out.println(book2);
    }
}
