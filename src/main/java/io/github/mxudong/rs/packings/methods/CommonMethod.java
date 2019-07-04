package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.utils.ArrayUtil;
import io.github.mxudong.rs.utils.MethodUtil;

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

public class CommonMethod implements Invoker {

    /**
     * all the common method type is MethodType.CommonMethod
     *
     * @see MethodType
     */
    private final static MethodType METHOD_TYPE = MethodType.CommonMethod;

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
    private Class<?>[] paramClasses;

    /**
     * save the class which it belong to
     *
     * @see io.github.mxudong.rs.packings.classes.ClassObject
     */
    private ClassObject<?> belongClass;

    /**
     * construction method of {@code CommonMethod}, in this part,
     * the method will be append for packingMethod.
     * <p>
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     */
    public CommonMethod(Method packingMethod, ClassObject<?> classObject) {
        this.packingMethod = packingMethod;
        this.belongClass = classObject;
        this.returnType = packingMethod.getReturnType();
        this.paramClasses = packingMethod.getParameterTypes();
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
        return METHOD_TYPE;
    }

    @Override
    public boolean isParamsIsThisMethod(Object... params) {
        return MethodUtil.isParamEquals(this.paramClasses, params);
    }

    @Override
    public Class<?>[] getMethodParamsType() {
        return paramClasses;
    }

    @Override
    public Class getBelongClass() {
        return belongClass.getPackingClass();
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }
}
