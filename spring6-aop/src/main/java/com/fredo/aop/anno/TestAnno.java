package com.fredo.aop.anno;

import com.fredo.aop.target.Calculator;
import com.fredo.aop.target.CalculatorLogImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnno {

    @Test
    public void test1(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("bean.xml");
        Calculator cal = ioc.getBean(Calculator.class);

        cal.div(2,1);
    }
}
