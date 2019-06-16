package io.github.mxudong.rs.base.randoms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRandomTest {

    @Test
    public void getNextByte() {
        System.out.println("B1++++=========================");
        System.out.println(BaseRandom.getRandomByte());
    }

    @Test
    public void getNextByte1() {
        System.out.println("B2++++=========================");
        System.out.println(BaseRandom.getRandomByte((byte) 1, (byte) 5));
    }

    @Test
    public void getNextShort() {
        System.out.println("S1++++=========================");
        System.out.println(BaseRandom.getRandomShort());
    }

    @Test
    public void getNextShort1() {
        System.out.println("S2++++=========================");
        System.out.println(BaseRandom.getRandomShort((short) 10, (short) 15));
    }

    @Test
    public void getNextInt1() {
        System.out.println("I1++++=========================");
        System.out.println(BaseRandom.getRandomInt());
    }

    @Test
    public void getNextInt2() {
        System.out.println("I2++++===================321======");
        System.out.println(BaseRandom.getRandomInt(20, 22));
    }

    @Test
    public void getNextLong1() {
        System.out.println("L1++++=========================");
        System.out.println(BaseRandom.getRandomLong());
    }

    @Test
    public void getNextLong2() {
        System.out.println("L2++++=========================");
        System.out.println(BaseRandom.getRandomLong(75L, 86L));
    }

    @Test
    public void getRandomCharFromAllChar() {
        for(int i = 0; i < 10; i++){
            System.out.print(BaseRandom.getRandomCharFromAllChar());
        }
    }

    @Test
    public void getRandomChar() {
    }
}