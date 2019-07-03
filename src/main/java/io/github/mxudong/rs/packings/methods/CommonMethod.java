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

    /**
     * save the class of {@code packingMethod} return class
     */
    private Class<?> returnType;

    /**
     * save the classes of {@code packingMethod} required params' class
     */
    private Class[] paramClasses;

    /**
     * construction method of {@code CommonMethod}, in this part,
     * the method will be append for packingMethod.
     *
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     */
    public CommonMethod(Method packingMethod) {
        this.packingMethod = packingMethod;
    }

    @Override
    public Object invoke(Object target, Object... params) {
        try {
            return packingMethod.invoke(target, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

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
