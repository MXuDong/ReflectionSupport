package io.github.mxudong.rs.base.reflectionSupport;

import io.github.mxudong.rs.base.ReflectionSupport;
import io.github.mxudong.rs.exceptions.ReflectionException;
import org.junit.Test;

/**
 * @author Dong
 * <p>
 * Class Name : TestAddSetOrGetPrefix
 * Create Time : 18:06
 * Create Date : 2019/5/11
 * Project : rs
 */

public class TestAddSetOrGetPrefix {
    @Test
    public void testErrorOfNull() {
        doTest(null, true, null);
        doTest(null, false, null);
    }

    @Test
    public void testCommont(){
        doTest("test", true, "setTest");
        doTest("test", false, "getTest");
    }

    @Test
    public void testLittle(){
        doTest("", true, null);
    }

    @Test
    public void testBiggerStart(){
        doTest("Test", true, "setTest");
    }

    @Test
    public void testOnlyOneChar(){
        doTest("c", true, "setC");
    }

    public void doTest(String param, boolean isSet, String exceptString) {
        String result = null;
        try {
            result = ReflectionSupport.addSetOrGetPrefix(param, isSet);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        if(exceptString!=null){
            assert result.equals(exceptString);
        }

        else {
            assert exceptString == null;
        }
    }
}
