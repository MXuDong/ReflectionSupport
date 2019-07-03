package io.github.mxudong.rs.utils;

import io.github.mxudong.rs.packings.methods.CommonMethod;
import org.junit.Test;

import java.util.Arrays;

public class ArrayUtilTest {

    @Test
    public void getArrayClasses() throws NoSuchMethodException {
        System.out.println(Arrays.toString(CommonMethod.class.getMethod("getMethodType").getParameterTypes()));
    }

    @Test
    public void equalsArray() {
        Object[] first1 = new Object[]{null};
        Object[] second1 = new Object[]{};
        System.out.println(ArrayUtil.equalsArray(first1, second1));

        Object[] first2 = new Object[]{};
        Object[] second2 = new Object[]{};
        System.out.println(ArrayUtil.equalsArray(first2, second2));

        Object[] first3 = new Object[]{null};
        Object[] second3 = new Object[]{null};
        System.out.println(ArrayUtil.equalsArray(first3, second3));

        Object[] first4 = new Object[]{null};
        Object[] second4 = new Object[]{1};
        System.out.println(ArrayUtil.equalsArray(first4, second4));

        Object[] first5 = new Object[]{1,2,3};
        Object[] second5 = new Object[]{1,2,3};
        System.out.println(ArrayUtil.equalsArray(first5, second5));

        Object[] first6 = new Object[]{3,2,1};
        Object[] second6 = new Object[]{1,2,3};
        System.out.println(ArrayUtil.equalsArray(first6, second6));
    }
}