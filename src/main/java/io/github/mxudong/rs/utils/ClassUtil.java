package io.github.mxudong.rs.utils;

/**
 * this class support some classes' operation method
 *
 * @author Dong
 * @since 3.0
 */

public class ClassUtil {

    /**
     * to judge a class is base type's class
     *
     * @param type aim class
     * @return if class is base type's class, it will return true, else return false
     * @since 3.0
     */
    public static boolean isBaseType(Class type) {
        if (type.equals(int.class)) {
            return true;
        }
        if (type.equals(short.class)) {
            return true;
        }
        if (type.equals(byte.class)) {
            return true;
        }
        if (type.equals(long.class)) {
            return true;
        }
        if (type.equals(float.class)) {
            return true;
        }
        if (type.equals(double.class)) {
            return true;
        }
        if (type.equals(boolean.class)) {
            return true;
        }
        return type.equals(char.class);
    }

    /**
     * to judge that a type is packing of base type
     *
     * @param type aim class
     * @return if the aim class is packing of base type, will return true, else return false
     */
    public static boolean isBasePackingType(Class type) {
        if (type.equals(Integer.class)) {
            return true;
        }
        if (type.equals(Short.class)) {
            return true;
        }
        if (type.equals(Byte.class)) {
            return true;
        }
        if (type.equals(Long.class)) {
            return true;
        }
        if (type.equals(Float.class)) {
            return true;
        }
        if (type.equals(Double.class)) {
            return true;
        }
        if (type.equals(Boolean.class)) {
            return true;
        }
        return type.equals(Character.class);
    }
}
