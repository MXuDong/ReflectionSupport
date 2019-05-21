package io.github.mxudong.rs.base.entity;

/**
 * @author Dong
 * <p>
 * Class Name : Student
 * Create Time : 21:31
 * Create Date : 2019/5/11
 * Project : rs
 */

public class Student extends People {
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                '}';
    }
}
