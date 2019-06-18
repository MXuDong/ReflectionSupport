package io.github.mxudong.rs.base.strings;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringExtensionTest {

    @Test
    public void testBase(){
        String getMethod = "getTest";
        System.out.println(StringExtension.getGetterMethodProperty(getMethod));
    }

    @Test
    public void createRandomString() {
//        System.out.println(StringExtension.createRandomString("TTTTTEst", 3));
//        System.out.println(StringExtension.createRandomString("TTTTTEst", 0));
//        System.out.println(StringExtension.createRandomString("TTTTTEst", -1));
//        System.out.println(StringExtension.createRandomString("TTTTTEst", 100));
//        System.out.println(StringExtension.createRandomString("", 3));

        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));
        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));
        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));
    }
}