package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;
import org.junit.Test;

import java.lang.reflect.Method;

public class CommonMethodTest {

    public CommonMethod Construction() throws NoSuchMethodException {
        Method method = CommonMethod.class.getMethod("getReturnType");
        CommonMethod commonMethod = new CommonMethod(method, new ClassObject<>());
        return commonMethod;
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
        System.out.println(commonMethod.isParamsIsThisMethod(1,2,3,4,5,6,null,5));
    }

    @Test
    public void getMethodParamsType() {
    }

    @Test
    public void getBelongClass() {
    }

    @Test
    public void getReturnType() {
    }
}