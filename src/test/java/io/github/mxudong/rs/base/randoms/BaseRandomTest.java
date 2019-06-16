package io.github.mxudong.rs.base.randoms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRandomTest {

    @Test
    public void getNextByte() {
        System.out.println("B1++++=========================");
        System.out.println(BaseRandom.getNextByte());
    }

    @Test
    public void getNextByte1() {
        System.out.println("B2++++=========================");
        System.out.println(BaseRandom.getNextByte((byte) 1, (byte) 5));
    }

    @Test
    public void getNextShort() {
        System.out.println("S1++++=========================");
        System.out.println(BaseRandom.getNextShort());
    }

    @Test
    public void getNextShort1() {
        System.out.println("S2++++=========================");
        System.out.println(BaseRandom.getNextShort((short) 10, (short) 15));
    }

    @Test
    public void getNextInt1() {
        System.out.println("I1++++=========================");
        System.out.println(BaseRandom.getNextInt());
    }

    @Test
    public void getNextInt2() {
        System.out.println("I2++++===================321======");
        System.out.println(BaseRandom.getNextInt(20, 22));
    }

    @Test
    public void getNextLong1() {
        System.out.println("L1++++=========================");
        System.out.println(BaseRandom.getNextLong());
    }

    @Test
    public void getNextLong2() {
        System.out.println("L2++++=========================");
        System.out.println(BaseRandom.getNextLong(75L, 86L));
    }
}