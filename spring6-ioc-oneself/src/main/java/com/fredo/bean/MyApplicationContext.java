package com.fredo.bean;

/**
 * 自定义bean容器接口
 */
public interface MyApplicationContext {

    Object getBean(Class clazz);
}
