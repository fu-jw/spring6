package com.fredo.iocxml.autowire;

public class UsrServiceImpl implements UsrService {

    private UsrDao usrDao;

    public void setUsrDao(UsrDao usrDao) {
        this.usrDao = usrDao;
    }

    @Override
    public void saveUser() {
        usrDao.saveUser();
    }

}