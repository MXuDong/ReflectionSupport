package io.github.mxudong.rs.others;

import java.util.Arrays;

/**
 * @author Dong
 * @since 3.0
 */

public class tests {
    public static void main(String[] args) {
        Class<TestInterface> tClass = TestInterface.class;

        System.out.println(tClass.isAssignableFrom(TestAble.class));
        System.out.println(tClass.isAssignableFrom(TestInterface2.class));
        System.out.println(Arrays.toString(TestInterface2.class.getDeclaredMethods()));
        System.out.println(Arrays.toString(TestAble.class.getInterfaces()));
        System.out.println(Arrays.toString(TestInterface2.class.getInterfaces()));
    }
}

class TestAble implements TestInterface2 {
    @Override
    public int getInt() {
        return 1;
    }
}

interface TestInterface3{

}

interface TestInterface {
    /**
     * return 1
     *
     * @return 1
     */
    int getInt();
}

interface TestInterface2 extends TestInterface, TestInterface3{

}