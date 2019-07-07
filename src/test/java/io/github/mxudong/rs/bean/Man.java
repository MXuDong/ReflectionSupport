package io.github.mxudong.rs.bean;

/**
 * @author Dong
 * @since 3.0
 */

public class Man extends People {
    private final static String SEX = "男";

    public static String getSEX() {
        return SEX;
    }

    @Override
    public String toString() {
        return "Man{" +
                "number='" + number + '\'' +
                '}';
    }
}
