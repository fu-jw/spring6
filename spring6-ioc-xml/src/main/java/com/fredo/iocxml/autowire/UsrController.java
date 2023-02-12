package com.fredo.iocxml.autowire;

public class UsrController {

    private UsrService usrService;

    public void setUsrService(UsrService usrService) {
        this.usrService = usrService;
    }

    public void saveUser(){
        usrService.saveUser();
    }

}