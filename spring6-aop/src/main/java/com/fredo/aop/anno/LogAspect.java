package com.fredo.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面类
 * 在切面类中设置切点和通知类型
 */
@Component// 放到IoC容器中
@Aspect// 表示是一个切面
public class LogAspect {

    @Before(value = "execution(public int com.fredo.aop.target.CalculatorLogImpl.*(..))")
    public void beforeMethod() {
        System.out.println("前置通知");
    }

    @After(value = "execution(* com.fredo.aop.target.CalculatorLogImpl.*(..))")
    public void afterMethod() {
        System.out.println("后置通知");
    }

    @AfterReturning(value = "execution(* com.fredo.aop.target.CalculatorLogImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知，方法名：" + methodName + "，结果：" + result);

    }

    @AfterThrowing(value = "execution(* com.fredo.aop.target.CalculatorLogImpl.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名："+methodName+"，异常："+ex);
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("环绕通知-->目标对象方法执行之前");
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->目标对象方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标对象方法出现异常时");
        } finally {
            System.out.println("环绕通知-->目标对象方法执行完毕");
        }
        return result;
    }

    @Pointcut("execution(* com.fredo.aop.target.CalculatorLogImpl.*(..))")
    public void pointCut(){}

}
