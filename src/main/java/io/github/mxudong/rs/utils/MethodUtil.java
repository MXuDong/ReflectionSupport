package io.github.mxudong.rs.utils;

import java.util.Objects;

/**
 * this class support some operation for method
 *
 * @author Dong
 * @since 3.0
 */

public class MethodUtil {

    /**
     * to judge the objects if from classes, such as {@code ArrayUtil.equalsArray}, but
     * the null only not equals bastType
     *
     * @param classes classes from method params
     * @param objects objects which will be input
     * @return if objects is from classes, return true, else return false
     */
    public static boolean isParamEquals(Class[] classes, Object[] objects) {

        if (classes == null || objects == null) {
            if (classes == null) {
                return objects == null || objects.length == 0;
            }
            return classes.length == 0;
        }

        if (classes.length != objects.length) {
            return false;
        }

        for (int i = 0; i < classes.length; i++) {
            if (objects[i] == null) {
                if (ClassUtil.isBaseType(classes[i])) {
                    return false;
                }
                continue;
            }

            if (!Objects.equals(classes[i], objects[i].getClass())) {
                if (ClassUtil.isBasePackingType(objects[i].getClass())) {
                    if (!Objects.equals(classes[i], ClassUtil.getBastTypeFromPacking(objects[i].getClass()))) {
                        return false;
                    }
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}
