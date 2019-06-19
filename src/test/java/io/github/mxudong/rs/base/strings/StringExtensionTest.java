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
//        System.out.println(StringExtension.createRandomStringBase("*26"));
//        System.out.println(StringExtension.createRandomStringBase("{T}10"));
//        System.out.println(StringExtension.createRandomStringBase("{---}123}"));
//        System.out.println(StringExtension.createRandomStringBase("c3n{test-}}c"));
//        System.out.println(StringExtension.createRandomStringBase("c3n{test}12c"));
//        System.out.println(StringExtension.createRandomStringBase("[https://]2n9[@]2*l2[.com]"));
        System.out.println(StringExtension.createRandomStringBase("c<2|5>"));
        System.out.println(StringExtension.createRandomStringBase("c<|5>"));
        System.out.println(StringExtension.createRandomStringBase("c<2|>"));
        System.out.println(StringExtension.createRandomStringBase("c<0|>"));

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