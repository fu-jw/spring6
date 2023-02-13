package com.fredo.resource;

import com.fredo.autowired.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("myUserDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void print() {
        System.out.println("Dao层执行结束");
    }
}
/**
 * @Resource注解也可以完成属性注入。那它和@Autowired注解有什么区别？
 *
 * - @Resource注解是JDK扩展包中的，也就是说属于JDK的一部分。所以该注解是标准注解，更加具有通用性。(JSR-250标准中制定的注解类型。JSR是Java规范提案。)
 * - @Autowired注解是Spring框架自己的。
 * - @Resource注解默认根据名称装配 byName，未指定name时，使用属性名作为name。通过name找不到的话会自动启动通过类型byType装配。
 * - @Autowired注解默认根据类型装配 byType，如果想根据名称装配，需要配合@Qualifier注解一起用。
 * - @Resource注解用在属性上、setter方法上。
 * - @Autowired注解用在属性上、setter方法上、构造方法上、构造方法参数上。
 *
 * @Resource注解属于JDK扩展包，所以不在JDK当中，需要额外引入以下依赖：
 * 【如果是JDK8的话不需要额外引入依赖。高于JDK11或低于JDK8需要引入以下依赖。】
 */

/**
 * 【Spring全注解开发】
 * 全注解开发就是不再使用spring配置文件了，写一个配置类来代替配置文件。
 *
 * @Configuration
 * //@ComponentScan({"com.fredo.controller", "com.fredo.service","com.fredo.dao"})
 * @ComponentScan("com.fredo")
 * public class Spring6Config {
 *    // ......
 * }
 */