package com.fredo.bean;

import java.util.HashMap;

/**
 * 自定义Bean容器实现类
 */
public class MyAnnotationApplicationContext implements MyApplicationContext{

    /**
     * 放bean对象
     *  key      全类名
     *  value    bean对象
     */
    private HashMap<Class, Object> beanMap = new HashMap<>();

    /**
     * 获取bean对象
     */
    @Override
    public Object getBean(Class clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 创建有参构造器，根据参数定义扫描规则
     */
    public MyAnnotationApplicationContext(String basePackage) {

    }












}
