package io.github.mxudong.beans;

/**
 * Class Name : Student
 * Create Time : 16:03
 * Create Date : 2019/6/8
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since
 */

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
