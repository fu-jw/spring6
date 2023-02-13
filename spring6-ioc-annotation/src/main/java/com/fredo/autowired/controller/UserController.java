package com.fredo.autowired.controller;

import com.fredo.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    /**
     * 注解 Autowired
     *  自动注入
     *
     *  可用在：
     *  - 构造方法上
     *  - 方法上
     *  - 形参上
     *  - 属性上
     *  - 注解上
     *
     *  注入方式：
     *  1、属性注入：默认根据类型
     *  2、set 注入
     *  3、构造方法注入
     *  4、形参上注入
     *  5、只有一个构造函数，无注解
     */
    // 1、属性注入
    @Autowired
    private UserService userService;

    // 2、set 注入
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    // 3、构造方法注入
//    private UserService userService;
//    @Autowired  // 不写也可以？？？
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    // 4、形参上注入
//    private UserService userService;
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }
    // 5、只有一个构造函数，无注解
//    @Autowired
//    private UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //======================================================
    public void out() {
        userService.out();
        System.out.println("Controller层执行结束。");
    }

}
