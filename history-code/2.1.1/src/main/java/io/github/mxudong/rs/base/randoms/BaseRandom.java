package io.github.mxudong.rs.base.randoms;

import java.util.Random;

import static io.github.mxudong.rs.base.strings.StringExtension.getAllCharacter;

/**
 * Class Name : BaseRandom
 * Create Time : 22:28
 * Create Date : 2019/6/15
 * Project : ReflectionSupport
 * <p>
 * This class provides a large number of random generation packings,
 * including the randomization of all basic objects, but also can
 * specify a lot of scope.
 * <p>
 * All packings are static packings.
 * <p>
 * All randomness comes from Long type randomness
 *
 * @author Dong
 * @since 2.0
 */

public class BaseRandom {


    /**
     * Getting a random character from all character sets
     *
     * @return a char
     */
    public static char getRandomCharFromAllChar() {
        return getRandomChar(getAllCharacter());
    }

    /**
     * Get a random character from a string
     *
     * @param chars a string
     * @return a random character
     */
    public static char getRandomChar(String chars) {
        int index = getRandomInt(chars.length());
        return chars.charAt(index);
    }

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

    /**
     * Generate double-precision floating-point numbers
     * with minimum flags, no more than flags + 1
     * <p>
     *
     * @param flags Generating Random Double Precision Floating Point Cardinals
     * @return a random value of double type
     */
    public static double getRandomDouble(double flags) {
        double temp = Math.abs(new Random().nextDouble());
        return temp + flags;
    }

    /**
     * Generate double-precision floating-point numbers
     * with minimum flags, no more than flags + 1
     * <p>
     *
     * @return a random value of double type
     */
    public static double getRandomDouble() {
        return getRandomDouble(getRandomLong());
    }

    /**
     * Generate float-precision floating-point numbers
     * with minimum flags, no more than flags + 1
     * <p>
     *
     * @param flags Generating Random float Precision Floating Point Cardinals
     * @return a random value of float type
     */
    public static float getRandomFloat(float flags) {
        return (float) getRandomDouble(flags);
    }

    /**
     * Generate float-precision floating-point numbers
     * with minimum flags, no more than flags + 1
     * <p>
     *
     * @return a random value of float type
     */
    public static float getRandomFloat() {
        return getRandomFloat(getRandomInt());
    }

    /**
     * Get a Random Value of boolean Type
     *
     * @return true or false
     */
    public static boolean getRandomBoolean() {
        return getRandomInt(0, 2) == 1;
    }
}
