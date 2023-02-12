package com.fredo.iocxml.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("☆☆☆" + beanName + " = " + bean);
        System.out.println("生命周期：3、bean的后置处理器（初始化之前）");
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("★★★" + beanName + " = " + bean);
        System.out.println("生命周期：5、bean的后置处理器（初始化之后）");
        return bean;
    }
}