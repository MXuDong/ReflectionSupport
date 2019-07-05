package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.utils.MethodUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * this class packing the construction method, so you
 * can create instance from this
 *
 * @author Dong
 * @since 3.0
 */

public class ConstructMethod<T> {

    /**
     * the packing constructor
     */
    private Constructor<T> constructor;

    /**
     * if want to invoke the construction, the params is required
     */
    private Class<?>[] paramClasses;

    /**
     * the class which ClassObject belong to
     */
    private ClassObject<T> belongClassObject;

    private final static MethodType METHOD_TYPE = MethodType.ConstructionMethod;


    /**
     * invoke the construction method
     *
     * @param params invoke the construction needed params
     * @return new instance
     * @since 3.0
     */
    public Object newInstance(Object... params) {
        Object result = null;
        try {
            result = constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * get the method type
     *
     * @return method type
     * @see MethodType
     * @since 3.0
     */
    public MethodType getMethodType() {
        return METHOD_TYPE;
    }

    /**
     * determine whether the incoming parameters are the required parameters for
     * this method
     *
     * @param params be judge params
     * @return if the params is packing method required param type
     * @since 3.0
     */
    public boolean isParamsIsThisMethod(Object... params) {
        return MethodUtil.isParamEquals(this.paramClasses, params);
    }

    /**
     * get the params type
     *
     * @return classes from param
     */
    public Class<?>[] getMethodParamsType() {
        return paramClasses;
    }

    /**
     * get the class of packaging method attribution
     *
     * @return the class which packing method belong to
     * @since 3.0
     */
    public Class getBelongClass() {
        return belongClassObject.getPackingClass();
    }

    /**
     * get the params count
     *
     * @return params count
     */
    public int getParamsCount() {
        return paramClasses.length;
    }
}
