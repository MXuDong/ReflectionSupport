package io.github.mxudong.rs.packings.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * this class is packing the common method.
 * <p>
 * common methods do not include constructors,
 * static methods, Setter Method, and Getter Method.
 *
 * @author Dong
 * @since 3.0
 */

public class CommonMethod implements Invoker{

    /**
     * the inner method which is packed.
     */
    private Method packingMethod;


    @Override
    public Object invoke(Object target, Object... params) {
        return null;
    }

    @Override
    public MethodType getMethodType() {
        return null;
    }

    @Override
    public boolean isParamsIsThisMethod(Object... params) {
        return false;
    }

    @Override
    public Class[] getMethodParamsType() {
        return new Class[0];
    }

    @Override
    public Class getBelongClass() {
        return null;
    }

    @Override
    public Class<?> getReturnType() {
        return null;
    }
}
