package io.github.mxudong.rs.base.randoms;

import java.util.Random;

/**
 * Class Name : BaseRandom
 * Create Time : 22:28
 * Create Date : 2019/6/15
 * Project : ReflectionSupport
 * <p>
 * This class provides a large number of random generation methods,
 * including the randomization of all basic objects, but also can
 * specify a lot of scope.
 * <p>
 * All methods are static methods.
 * <p>
 * All randomness comes from Long type randomness
 *
 * @author Dong
 * @since 2.0
 */

public class BaseRandom {

    /**
     * Get a random value of byte type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of byte type
     */
    public static byte getRandomByte(byte minValue, byte maxValue) {
        return (byte) getRandomLong(minValue, maxValue);
    }

    /**
     * Get a random value of byte type between Byte.max and Byte.min
     *
     * @return a random value of byte type
     */
    public static byte getRandomByte() {
        return getRandomByte(Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * get a random value of byte type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of byte type
     */
    public static byte getRandomByte(byte bind) {
        return getRandomByte((byte) 0, bind);
    }

    /**
     * Get a random value of short type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of short type
     */
    public static short getRandomShort(short minValue, short maxValue) {
        return (short) getRandomLong(minValue, maxValue);
    }

    /**
     * Get a random value of short type between short.max and short.min
     *
     * @return a random value of short type
     */
    public static short getRandomShort() {
        return getRandomShort(Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * get a random value of short type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of short type
     */
    public static short getRandomShort(short bind) {
        return getRandomShort((short) 0, bind);
    }

    /**
     * Get a random value of int type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of int type
     */
    public static int getRandomInt(int minValue, int maxValue) {
        return (int) getRandomLong(minValue, maxValue);
    }

    /**
     * Get a random value of int type between int.max and int.min
     *
     * @return a random value of int type
     */
    public static int getRandomInt() {
        return getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * get a random value of int type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of int type
     */
    public static int getRandomInt(int bind) {
        return getRandomInt(0, bind);
    }

    /**
     * Get a Random Value of long Type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of long type
     */
    public static long getRandomLong(long minValue, long maxValue) {
        if (maxValue <= minValue) {
            throw new IllegalArgumentException();
        }
        long range = maxValue - minValue;

        Random random = new Random();
        long RandomLong = Math.abs(random.nextLong());
        return (minValue) + (RandomLong % range);
    }

    /**
     * Get a random value of long type between long.max and long.min
     *
     * @return a random value of long type
     */
    public static long getRandomLong() {
        return getRandomLong(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * get a random value of long type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of long type
     */
    public static long getRandomLong(long bind) {
        return getRandomLong(0, bind);
    }

}
