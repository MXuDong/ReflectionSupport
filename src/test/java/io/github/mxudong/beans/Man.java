package io.github.mxudong.beans;

/**
 * Class Name : Man
 * Create Time : 18:19
 * Create Date : 2019/6/10
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since
 */

public class Man extends People {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Man{" +
                "age=" + age +
                '}';
    }
}
