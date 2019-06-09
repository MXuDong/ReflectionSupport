package io.github.mxudong.rs.base.strings;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringExtensionTest {

    @Test
    public void testBase(){
        String getMethod = "getTest";
        System.out.println(StringExtension.getGetterMethodProperty(getMethod));
    }

}