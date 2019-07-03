package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CommonMethodTest {

    public CommonMethod Construction() throws NoSuchMethodException {
        Method method = CommonMethod.class.getMethod("getReturnType");
        return new CommonMethod(method, new ClassObject<>());
    }

    @Test
    public void invoke() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(commonMethod.invoke(commonMethod));
    }

    @Test
    public void getMethodType() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(commonMethod.getMethodType());
        System.out.println(commonMethod.getMethodType().getType());
        System.out.println(commonMethod.getMethodType().type);
    }

    @Test
    public void isParamsIsThisMethod() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(commonMethod.isParamsIsThisMethod());
        System.out.println(commonMethod.isParamsIsThisMethod((Object[]) new Class[]{}));
        System.out.println(commonMethod.isParamsIsThisMethod(1));
        System.out.println(commonMethod.isParamsIsThisMethod(1, 2, 3, 4, 5, 6, null, 5));
    }

    @Test
    public void getMethodParamsType() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(Arrays.toString(commonMethod.getMethodParamsType()));
    }

    @Test
    public void getBelongClass() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(commonMethod.getBelongClass());
    }

    @Test
    public void getReturnType() throws NoSuchMethodException {
        CommonMethod commonMethod = Construction();
        System.out.println(commonMethod.getReturnType());
    }
}