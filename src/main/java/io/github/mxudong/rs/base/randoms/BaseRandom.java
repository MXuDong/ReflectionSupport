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
     * Get a random value of int type between Byte.max and Byte.min
     *
     * @return a random value of int type
     */
    public static byte getNextByte(){
        return getNextByte(Byte.MIN_VALUE, Byte.MAX_VALUE);
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

}