package io.github.mxudong.rs.bean;

import io.github.mxudong.rs.annotations.TestAnnotation;

/**
 * @author Dong
 * @since 3.0
 */

public class People {
    private int age;
    private String name;
    public String number;

    public People() {
    }

    public People(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @TestAnnotation
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
