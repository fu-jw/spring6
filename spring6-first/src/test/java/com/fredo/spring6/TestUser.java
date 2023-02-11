package com.fredo.spring6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

public class TestUser {

    private Logger logger = LoggerFactory.getLogger(TestUser.class);

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

        // 可以手动写日志信息
        logger.info("执行成功");
    }

    /**
     * 入门案例小结：
     * Spring 出现以前，创建对象需要无参构造器：
     *  类名 对象 = new 类名()；
     * Spring 实现了 IOC（控制反转），可以根据配置文件，在程序启动时就创建好对象，并放在IOC容器中供用户使用
     * 将创建对象的权利反转给Spring，省去用户创建对象的操作
     *
     * 1.Spring 创建对象的步骤：
     *  1 加载配置文件 beans.xml
     *  2 对配置文件进行解析
     *  3 获取xml文件中 bean 标签的属性值（常见有id，clas。。。）
     *  4 使用反射，根据全类名(class的值)创建对象
     *
     * 2.为什么不自己new对象
     *  1 后期可能巨多的类，自己难以管理
     *  2 解耦(依赖注入)
     *  3 交给Spring 方便管理
     *
     * 3.Spring 创建的对象放在哪
     *  在 beanDefinitionMap中，是一个Map<String, BeanDefinition>
     *  key     是 bean 的 id
     *  value   是 bean 的定义(或描述信息) BeanDefinition
     */

    /**
     * IoC
     *  IoC 是 Inversion of Control 的简写，“控制反转”，
     *  是一种思想，不是一门技术
     *  是重要的面向对象的编程法则，可以指导我们设计出松耦合的程序
     *
     *  IoC 思想的落地实现是 IoC 容器
     *
     *  Spring 通过 IoC 容器来管理所有 Java对象的实例化和初始化，控制对象之间的依赖关系
     *  将由 IoC 容器管理的对象称为 Bean，与new出来的普通对象无任何本质区别
     *
     * IoC容器在Spring的实现
     *  Spring 的 IoC 容器就是 IoC思想的一个落地的产品实现。
     *  IoC容器中管理的组件也叫做 bean。在创建 bean 之前，首先需要创建IoC 容器。
     *  Spring 提供了IoC 容器的两种实现方式：
     *  1.BeanFactory
     *  2.ApplicationContext
     *
     * IoC容器加载 Bean 对象的流程
     *  0.配置Bean定义信息，可以在XML配置文件中，或者注解
     *  1.通过接口 BeanDefinitionReader 读取配置信息
     *  2.在容器中，将Bean信息实例化，工厂(BeanFactory) + 反射
     *  3.对象初始化成最终的Bean对象，将实例化的空对象中的属性信息进行初始化
     *  4.getBean(“name”) 即可获取Bean对象
     *
     * DI
     *  Dependency Injection 依赖注入，实现控制反转的具体实现
     *  DI 的方式有两种：
     *  1.setter 注入
     *  2.构造器注入
     */

}
