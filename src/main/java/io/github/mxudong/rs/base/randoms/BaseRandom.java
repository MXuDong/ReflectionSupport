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
     * Get a Random Value of Long Type
     *
     * @param minValue minimum value
     * @param maxValue Maximum value
     * @return a random value of long type
     */
    public static long getNextLong(long minValue, long maxValue) {
        if (maxValue <= minValue) {
            throw new IllegalArgumentException();
        }
        long range = maxValue - minValue;

        Random random = new Random();
        long nextLong = random.nextLong();
        return (minValue) + (nextLong % range);
    }

}
