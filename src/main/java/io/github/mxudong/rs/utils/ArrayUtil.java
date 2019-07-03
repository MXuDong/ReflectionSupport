package io.github.mxudong.rs.utils;

/**
 * for array operation, such as equal array
 *
 * @author Dong
 * @since 3.0
 */

public class ArrayUtil {

    /**
     * to equals two arrays is equal
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
            if (!first[i].equals(second[i])) {
                return false;
            }
        }

        return true;
    }
}
