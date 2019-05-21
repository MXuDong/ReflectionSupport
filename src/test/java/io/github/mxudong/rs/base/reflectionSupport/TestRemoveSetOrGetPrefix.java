package io.github.mxudong.rs.base.reflectionSupport;

import io.github.mxudong.rs.base.ReflectionSupport;
import io.github.mxudong.rs.exceptions.ReflectionException;
import org.junit.Test;

/**
 * @author Dong
 * <p>
 * Class Name : TestRemoveSetOrGetPrefix
 * Create Time : 21:42
 * Create Date : 2019/5/9
 * Project : rs
 */

public class TestRemoveSetOrGetPrefix {

    @Test
    public void testCommonError() {
        doTest("testCommon", null);
    }

    @Test
    public void testLengIsIllage1(){
        doTest("set", null);
    }

    @Test
    public void testLengIsIllage2(){
        doTest("se", null);
    }

    @Test
    public void testLengIsIllage3(){
        doTest("setT", "t");
    }

    @Test
    public void testNullPoint(){
        doTest(null, null);
    }

    @Test
    public void testRemoveSetPrefix1(){
        doTest("setTest", "test");
    }

    @Test
    public void testRemoveSetPrefix2(){
        doTest("settest", "test");
    }

    @Test
    public void testCommonGet(){
        doTest("getRes", "res");
    }

    public void doTest(String param, String expect){
        String str = null;
        try {
            str = ReflectionSupport.removeSetOrGetPrefix(param);
            System.out.println(str);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        if(expect == null){
            assert str == null;
        }else {
            assert str.equals(expect);
        }

    }
}
