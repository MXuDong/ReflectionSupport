package io.github.mxudong.rs.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

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

    /**
     * get the base type from packing bast type
     *
     * @param packingBaseType aim packing type
     * @return corresponding bast type
     */
    public static Class getBastTypeFromPacking(Class packingBaseType) {
        if (packingBaseType.equals(Integer.class)) {
            return int.class;
        }
        if (packingBaseType.equals(Short.class)) {
            return short.class;
        }
        if (packingBaseType.equals(Byte.class)) {
            return byte.class;
        }
        if (packingBaseType.equals(Long.class)) {
            return long.class;
        }
        if (packingBaseType.equals(Float.class)) {
            return float.class;
        }
        if (packingBaseType.equals(Double.class)) {
            return double.class;
        }
        if (packingBaseType.equals(Boolean.class)) {
            return boolean.class;
        }
        if (packingBaseType.equals(Character.class)) {
            return char.class;
        }

        return null;
    }

    /**
     * is the annotation meta annotation
     * <p>
     * the meta annotation is Document, Retention and Target
     *
     * @param annotationClass target annotation class
     * @return true or false
     * @see Documented
     * @see Retention
     * @see Target
     */
    public static boolean isMetaAnnotation(Class annotationClass) {
        return Documented.class.equals(annotationClass) ||
                Retention.class.equals(annotationClass) ||
                Target.class.equals(annotationClass);
    }
}
