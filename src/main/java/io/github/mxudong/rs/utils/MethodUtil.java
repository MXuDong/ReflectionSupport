package io.github.mxudong.rs.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

    /**
     * is the method static
     *
     * @param method be check method
     * @return if is static return true, else return false
     */
    public static boolean isStaticMethod(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    /**
     * is method getter
     * @param method be check method
     * @return if is getter method return true, else return false
     */
    public static boolean isGetterMethod(Method method){
        String methodName = method.getName();
        return methodName.startsWith("get") || methodName.startsWith("is");
    }
}
