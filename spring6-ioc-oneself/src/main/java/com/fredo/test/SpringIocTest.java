package com.fredo.test;

import com.fredo.bean.MyAnnotationApplicationContext;
import com.fredo.service.UserService;
import org.junit.jupiter.api.Test;

public class SpringIocTest {

    @Test
    public void testIoc() {
        MyAnnotationApplicationContext applicationContext =
                new MyAnnotationApplicationContext("com.fredo");
        UserService userService = (UserService)applicationContext.getBean(UserService.class);
        userService.out();
        System.out.println("run success");
    }
}