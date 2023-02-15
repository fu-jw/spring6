package com.fredo.service;

import com.fredo.anno.Bean;
import com.fredo.anno.Di;
import com.fredo.dao.UserDao;

@Bean
public class UserServiceImpl implements UserService {

    @Di
    private UserDao userDao;

    @Override
    public void out() {
        userDao.print();
        System.out.println("Service层执行结束");
    }
}