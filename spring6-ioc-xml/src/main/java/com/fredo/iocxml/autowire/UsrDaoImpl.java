package com.fredo.iocxml.autowire;

public class UsrDaoImpl implements UsrDao {

    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }

}