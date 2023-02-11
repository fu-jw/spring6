package com.fredo.iocxml.di;

public class Book {
    private String name;
    private String author;

    // 快捷键：alt+insert


    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // 不使用Spring的，原生注入方式：
        // 1.setter 注入
        Book book1 = new Book();
        book1.setName("java");
        book1.setAuthor("高斯林");

        // 2.构造器注入
        Book book2 = new Book("linux", "linas");

        // 在Spring中，两种方式的实现，详见测试方法

    }
}
