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

//        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));
//        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));
//        System.out.println(StringExtension.createRandomString("testestsaklsjx,mnv,x"));

        System.out.println(StringExtension.createRandomString());
        System.out.println(StringExtension.createRandomString());
        System.out.println(StringExtension.createRandomString());

    }

    @Test
    public void testCreateRandomFromDataFromat(){
        StringExtension stringExtension = new StringExtension();
//        System.out.println(stringExtension.createRandomStringBase("*26"));
//        System.out.println(stringExtension.createRandomStringBase("{T}10"));
//        System.out.println(stringExtension.createRandomStringBase("{---}123}"));
//        System.out.println(stringExtension.createRandomStringBase("c3n{test-}}c"));
//        System.out.println(stringExtension.createRandomStringBase("c3n{test}12c"));
//        System.out.println(stringExtension.createRandomStringBase("[https://]n9[@]{q}2[.com]"));
//        System.out.println(stringExtension.createRandomStringBase("c<2|5>"));
//        System.out.println(stringExtension.createRandomStringBase("c<|5>"));
//        System.out.println(stringExtension.createRandomStringBase("c<2|>"));
//        System.out.println(stringExtension.createRandomStringBase("c<0|>"));
        System.out.println(stringExtension.createRandomStringBase("[H-]]([t])[p://]"));

    }

    @Test
    public void convent(){
        System.out.println(StringExtension.convent("3"));
        System.out.println(StringExtension.convent("<3|10>"));
        System.out.println(StringExtension.convent("<|10>"));
        System.out.println(StringExtension.convent("<3|>"));
        System.out.println(StringExtension.convent("<33a|>"));
        System.out.println(StringExtension.convent(">"));

    }
}