package io.github.mxudong.beans;

import io.github.mxudong.rs.base.randoms.annotations.Randomset;

/**
 * Class Name : Student
 * Create Time : 16:03
 * Create Date : 2019/6/8
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.1.1
 */

@Randomset(defaultFormat = "[number:]n13", minIntegerValue = 12, maxIntegerValue = 17)
public class Student {
    private int age;
    private String name;
    private String number;

    public Student() {
    }

    public Student(int age, String name, String number) {
        this.age = age;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
