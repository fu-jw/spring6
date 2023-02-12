package com.fredo.iocxml;

import com.fredo.iocxml.bean.UserDao;
import com.fredo.iocxml.di.Book;
import com.fredo.iocxml.di.Clazz;
import com.fredo.iocxml.di.Student;
import com.fredo.iocxml.life.UserLife;
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

    // 3.依赖注入--特殊值处理
    @Test
    public void test3(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.依赖注入--setter
        Book book1 = (Book) ioc.getBean("book1");
        System.out.println(book1);
    }

    // 3.依赖注入--特殊值处理
    @Test
    public void test4(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.依赖注入--setter--对象属性
        Student stu = (Student) ioc.getBean("stu");
        System.out.println(stu);
    }

    // 3.依赖注入--特殊值处理
    @Test
    public void test5(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.依赖注入--setter--数组类型
        Student stu = (Student) ioc.getBean("stu2");
        System.out.println(stu);
    }

    // 3.依赖注入--特殊值处理
    @Test
    public void test6(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        // 1.依赖注入--setter--集合类型
        Clazz clazz2 = (Clazz) ioc.getBean("clazz2");
        System.out.println(clazz2);
    }
    // 4.bean 的作用域
    /**
     * 在Spring中可以通过配置bean标签的scope属性来指定bean的作用域范围
     *  1.singleton（默认） 在IOC容器中，这个bean的对象始终为单实例，在 IOC容器初始化时创建
     *  2.prototype       这个bean在IOC容器中有多个实例，在获取bean时创建
     *
     * 如果是在WebApplicationContext环境下还会有另外几个作用域（但不常用）：
     *  3.request       在一个请求范围内有效
     *  4.session       在一个会话范围内有效
     */

    // 5.bean 的生命周期
    /**
     * 1 bean对象创建（调用无参构造器）
     * 2 给bean对象设置属性
     * 3 bean的后置处理器（初始化之前）
     * 4 bean对象初始化（需在配置bean时指定初始化方法）
     * 5 bean的后置处理器（初始化之后）
     * 6 bean对象就绪可以使用
     * 7 bean对象销毁（需在配置bean时指定销毁方法）
     * 8 IOC容器关闭
     */
    @Test
    public void test7(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        UserLife userLife = (UserLife) ioc.getBean("userLife");
        System.out.println(userLife);
        System.out.println("生命周期：6、通过IOC容器获取bean并使用");
        ioc.close();
    }
}
