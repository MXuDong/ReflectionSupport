package io.github.mxudong.beans;

/**
 * Class Name : People
 * Create Time : 16:11
 * Create Date : 2019/6/8
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since
 */

public class People {
    private String name;
    private String number;

    public People() {
    }

    public People(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
