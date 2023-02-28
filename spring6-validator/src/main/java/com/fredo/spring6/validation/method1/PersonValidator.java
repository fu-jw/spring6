package com.fredo.spring6.validation.method1;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    /**
     * 表示此校验用在哪个类型上
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    /**
     * 校验规则：设置校验逻辑的地点
     * 其中ValidationUtils，是Spring封装的校验工具类，帮助快速实现校验
     */
    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "名字不能为空！");
        Person p = (Person) object;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "错误：年龄不能小于0！");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "错误：年龄过大！");
        }
    }
}