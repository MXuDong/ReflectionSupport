package io.github.mxudong.rs.base.utils;

/**
 * Class Name : ClassUtil
 * Create Time : 16:44
 * Create Date : 2019/6/11
 * Project : ReflectionSupport
 *
 * support some static packings for check class.
 *
 * @author Dong
 * @since 2.0
 */

public class ClassUtil {

    /**
     * is type base type(byte, short, int, long, char, boolean, float, double)
     *
     * @param type input type
     * @return is base type
     */
    public static boolean isBaseType(Class type) {
        if(type.equals(short.class)) return true;
        if(type.equals(byte.class)) return true;
        if(type.equals(char.class)) return true;
        if(type.equals(int.class)) return true;
        if(type.equals(float.class)) return true;
        if(type.equals(boolean.class)) return true;
        if(type.equals(long.class)) return true;
        if(type.equals(double.class)) return true;
        return false;
    }

    /**
     * Determine whether the target class is a packaging class or not
     *
     * @param type input type
     * @return is packing type
     */
    public static boolean isPacking(Class type){
        if(type.equals(Short.class)) return true;
        if(type.equals(Byte.class)) return true;
        if(type.equals(Integer.class)) return true;
        if(type.equals(Long.class)) return true;
        if(type.equals(Float.class)) return true;
        if(type.equals(Double.class)) return true;
        if(type.equals(Boolean.class)) return true;
        if(type.equals(Character.class)) return true;
        return false;
    }
}
