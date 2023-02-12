package com.fredo.iocxml;

import com.fredo.iocxml.autowire.UsrController;
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

    // 6.接口 FactoryBean 常用于整合其他框架
    /**
     * Spring 中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean 即 FactoryBean。
     * FactoryBean跟普通Bean不同，其返回的对象不是指定类的一个实例，而是该FactoryBean的getObject方法所返回的对象。
     * 创建出来的对象是否属于单例由isSingleton中的返回决定。
     *
     * 一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，
     * 在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在<bean>中提供大量的配置信息。
     * 配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
     * Spring为此提供了一个 FactoryBean 的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。
     * FactoryBean 接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。
     * 它们隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。
     * 从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式
     *
     * 以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，
     * 根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，
     * 而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。
     *
     * FactoryBean 通常是用来创建比较复杂的bean，一般的bean 直接用xml配置即可，
     * 但如果一个bean的创建过程中涉及到很多其他的bean 和复杂的逻辑，用xml配置比较困难，这时可以考虑用FactoryBean。
     *
     * 很多开源项目在集成Spring 时都使用到FactoryBean，
     * 比如 MyBatis3 提供 mybatis-spring项目中的 org.mybatis.spring.SqlSessionFactoryBean：
     */

    // 6.基于XML的自动装配

    /**
     * 自动装配：
     * 根据指定的策略，在IOC容器中匹配某一个bean，自动为指定的bean中所依赖的类类型或接口类型属性赋值
     * 使用bean标签的autowire属性设置自动装配效果
     * 自动装配方式：byType、byName
     * byType：根据类型匹配IOC容器中的某个兼容类型的bean，为属性自动赋值
     * 若在IOC中，没有任何一个兼容类型的bean能够为属性赋值，则该属性不装配，即值为默认值null
     * 若在IOC中，有多个兼容类型的bean能够为属性赋值，则抛出异常NoUniqueBeanDefinitionException
     * byName：将自动装配的属性的属性名，作为bean的id在IOC容器中匹配相对应的bean进行赋值
     */
    @Test
    public void test8(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        UsrController userController = ioc.getBean(UsrController.class);
        userController.saveUser();
    }
}
