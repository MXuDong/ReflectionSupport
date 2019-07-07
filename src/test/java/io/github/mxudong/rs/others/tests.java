package io.github.mxudong.rs.others;

/**
 * @author Dong
 * @since 3.0
 */

public class tests {
    public static void main(String[] args) {
        Class<TestInterface> tClass = TestInterface.class;

        System.out.println(tClass.isAssignableFrom(TestAble.class));
        System.out.println(tClass.isAssignableFrom(TestInterface2.class));
    }
}

class TestAble implements TestInterface {
    @Override
    public int getInt() {
        return 1;
    }
}

interface TestInterface {
    /**
     * return 1
     *
     * @return 1
     */
    int getInt();
}

interface TestInterface2 extends TestInterface{

}