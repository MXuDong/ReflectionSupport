package io.github.mxudong.rs.others;

import java.lang.reflect.Constructor;

/**
 * @author Dong
 * @since
 */

public class tests {
    public static void main(String[] args) {
        Class<TestInterface> testInterfaceClass = TestInterface.class;
        Constructor<?>[] constructor = testInterfaceClass.getConstructors();
        System.out.println(constructor.length);
    }
}

interface TestInterface {

}