package io.github.mxudong.rs.utils;

import java.util.Objects;

/**
 * for array operation, such as equal array
 *
 * @author Dong
 * @since 3.0
 */

public class ArrayUtil {

    /**
     * to get the item class of an array
     *
     * @param params the array which will be got classes
     * @return the classes
     * @since 3.0
     */
    public static Class<?>[] getArrayClasses(Object... params) {
        if (params == null) {
            return new Class<?>[]{};
        }

        Class<?>[] result = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                result[i] = null;
            } else {
                result[i] = params[i].getClass();
            }
        }

        return result;
    }

    /**
     * to equals two arrays is equal, and one thing
     * that you must make sure, the any type is not
     * equal null
     *
     * @param first  first array
     * @param second second array
     * @return if equals return true, else return false
     * @since 3.0
     */
    public static boolean equalsArray(Object[] first, Object[] second) {
        if (first == null || second == null) {
            return first == null && second == null;
        }

        if (first.length != second.length) {
            return false;
        }

        for (int i = 0; i < first.length; i++) {
            if (!Objects.equals(first[i], second[i])) {
                return false;
            }
        }

        return true;
    }
}
