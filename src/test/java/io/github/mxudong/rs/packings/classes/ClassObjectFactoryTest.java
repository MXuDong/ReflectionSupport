package io.github.mxudong.rs.packings.classes;

import org.junit.Test;

import java.util.Arrays;

public class ClassObjectFactoryTest {
    @Test
    public void test1() {
        System.out.println(TestInterface.class.getSuperclass());
        System.out.println(Arrays.toString(TestInterface.class.getClasses()));
    }
}

interface TestInterface{

}