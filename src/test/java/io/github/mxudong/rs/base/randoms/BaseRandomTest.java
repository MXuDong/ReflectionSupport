package io.github.mxudong.rs.base.randoms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRandomTest {

    @Test
    public void getNextInt() {
        for(int i = 0; i < 10; i++){
            System.out.println(BaseRandom.getNextInt(10, 13));
        }
    }

    @Test
    public void getNextLong() {
        for(int i = 0; i < 10; i++){
            long test1 = BaseRandom.getNextLong(0, 1);
            System.out.println(test1);
        }
    }
}