package com.fredo.aop.anno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 在切面类中设置切点和通知类型
 */
@Component// 放到IoC容器中
@Aspect// 表示是一个切面
public class LogAspect {

    @Before("execution(public int com.fredo.aop.target.CalculatorLogImpl.*(..))")
    public void beforeMethod(){
        System.out.println("前置通知");
    }
}
