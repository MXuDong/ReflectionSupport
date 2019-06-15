package io.github.mxudong.rs.base.randoms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRandomTest {

    @Test
    public void testGetNextLong(){
        long test1 = BaseRandom.getNextLong(0, 2);
        System.out.println(test1);
    }

}