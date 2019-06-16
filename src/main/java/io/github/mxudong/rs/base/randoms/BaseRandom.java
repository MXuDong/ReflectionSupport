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
    public static byte getNextByte(byte minValue, byte maxValue) {
        return (byte) getNextLong(minValue, maxValue);
    }

    /**
     * Get a random value of byte type between Byte.max and Byte.min
     *
     * @return a random value of byte type
     */
    public static byte getNextByte() {
        return getNextByte(Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * get a random value of byte type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of byte type
     */
    public static byte getNextByte(byte bind) {
        return getNextByte((byte) 0, bind);
    }

    /**
     * Get a random value of short type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of short type
     */
    public static short getNextShort(short minValue, short maxValue) {
        return (short) getNextLong(minValue, maxValue);
    }

    /**
     * Get a random value of short type between short.max and short.min
     *
     * @return a random value of short type
     */
    public static short getNextShort() {
        return getNextShort(Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * get a random value of short type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of short type
     */
    public static short getNextShort(short bind) {
        return getNextShort((short) 0, bind);
    }

    /**
     * Get a random value of int type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of int type
     */
    public static int getNextInt(int minValue, int maxValue) {
        return (int) getNextLong(minValue, maxValue);
    }

    /**
     * Get a random value of int type between int.max and int.min
     *
     * @return a random value of int type
     */
    public static int getNextInt() {
        return getNextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * get a random value of int type between 0 and bind
     *
     * @param bind maxValue
     * @return a random value of int type
     */
    public static int getNextInt(int bind) {
        return getNextInt(0, bind);
    }

    /**
     * Get a Random Value of long Type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value (not include)
     * @return a random value of long type
     */
    public static long getNextLong(long minValue, long maxValue) {
        if (maxValue <= minValue) {
            throw new IllegalArgumentException();
        }
        long range = maxValue - minValue;

        Random random = new Random();
        long nextLong = Math.abs(random.nextLong());
        return (minValue) + (nextLong % range);
    }

    /**
     * Get a random value of long type between long.max and long.min
     *
     * @return a random value of long type
     */
    public static long getNextLong() {
        return getNextLong(Long.MIN_VALUE, Long.MAX_VALUE);
    }

}
