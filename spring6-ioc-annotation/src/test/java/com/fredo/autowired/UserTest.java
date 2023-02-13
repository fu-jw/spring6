package com.fredo.autowired;

import com.fredo.autowired.controller.UserController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    private Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void testAnnotation(){

        ApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
        UserController userController = ioc.getBean("userController", UserController.class);
        userController.out();
        logger.info("执行成功");
    }
}
