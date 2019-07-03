package io.github.mxudong.rs.utils;

import io.github.mxudong.rs.packings.methods.CommonMethod;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayUtilTest {

    @Test
    public void getArrayClasses() throws NoSuchMethodException {
        System.out.println(Arrays.toString(CommonMethod.class.getMethod("getMethodType").getParameterTypes()));
    }
}