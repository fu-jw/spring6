package com.fredo.iocxml.di;

public class Book {
    private String name;
    private String author;
    private String other1;
    private String other2;
    private String other3;

    // 快捷键：alt+insert


    public Book() {
    }

    public Book(String name, String author, String other1, String other2, String other3) {
        this.name = name;
        this.author = author;
        this.other1 = other1;
        this.other2 = other2;
        this.other3 = other3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", other3='" + other3 + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // 不使用Spring的，原生注入方式：
        // 1.setter 注入
        Book book1 = new Book();
        book1.setName("java");
        book1.setAuthor("高斯林");

        // 2.构造器注入
//        Book book2 = new Book("linux", "linas");

        // 在Spring中，两种方式的实现，详见测试方法

    }
}
