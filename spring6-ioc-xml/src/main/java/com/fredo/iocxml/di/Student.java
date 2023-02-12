package com.fredo.iocxml.di;

import java.util.Arrays;

public class Student {
    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private Clazz clazz1;
    private Clazz clazz2;
    private Clazz clazz3;

    private String[] hobbies;

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Student() {
    }

    public Student(Integer id, String name, Integer age, String sex, Clazz clazz1, Clazz clazz2, Clazz clazz3, String[] hobbies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.clazz1 = clazz1;
        this.clazz2 = clazz2;
        this.clazz3 = clazz3;
        this.hobbies = hobbies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Clazz getClazz1() {
        return clazz1;
    }

    public void setClazz1(Clazz clazz1) {
        this.clazz1 = clazz1;
    }

    public Clazz getClazz2() {
        return clazz2;
    }

    public void setClazz2(Clazz clazz2) {
        this.clazz2 = clazz2;
    }

    public Clazz getClazz3() {
        return clazz3;
    }

    public void setClazz3(Clazz clazz3) {
        this.clazz3 = clazz3;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", clazz1=" + clazz1 +
                ", clazz2=" + clazz2 +
                ", clazz3=" + clazz3 +
                ", hobbies=" + Arrays.toString(hobbies) +
                '}';
    }
}
