package io.github.mxudong.rs.packings.methods;

import java.lang.reflect.Constructor;

/**
 * this class packing the construction method, so you
 * can create instance from this
 *
 * @author Dong
 * @since 3.0
 */

public class ConstructMethod<T> implements Invoker{

    /**
     * the packing constructor
     */
    private Constructor<T> constructor;



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

    @Override
    public String getMethodName() {
        return null;
    }
}
