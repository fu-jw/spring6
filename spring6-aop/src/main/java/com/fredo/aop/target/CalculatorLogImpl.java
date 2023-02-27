package com.fredo.aop.target;

import org.springframework.stereotype.Component;

@Component
public class CalculatorLogImpl implements Calculator {

    @Override
    public int add(int i, int j) {

        int result = i + j;
        System.out.println("方法执行结果：" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {

        int result = i - j;
        System.out.println("方法执行结果：" + result);

        return result;
    }

    @Override
    public int mul(int i, int j) {

        int result = i * j;
        System.out.println("方法执行结果：" + result);

        return result;
    }

    @Override
    public int div(int i, int j) {

        int result = i / j;
        System.out.println("方法执行结果：" + result);

        return result;
    }
}