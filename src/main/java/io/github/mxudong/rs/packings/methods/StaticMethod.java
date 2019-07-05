package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;

import java.lang.reflect.Method;

/**
 * this class packing static method, support some
 * common operation method
 *
 * @author Dong
 * @since 3.0
 */

public class StaticMethod extends CommonMethod {

    /**
     * all the static method type is MethodType.StaticMethod
     *
     * @see MethodType
     */
    private final static MethodType METHOD_TYPE = MethodType.StaticMethod;

    /**
     * construction method of {@code StaticMethod}, in this part,
     * the method will be append for packingMethod.
     * <p>
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     * @param classObject   method belong class object
     */
    public StaticMethod(Method packingMethod, ClassObject<?> classObject) {
        super(packingMethod, classObject);
    }


    /**
     * the static method invoke is not required target object
     *
     * @param params be invoked required params
     * @return the result of invoking
     */
    public Object invoke(Object... params) {
        return super.invoke(null, params);
    }

    @Override
    public MethodType getMethodType() {
        return METHOD_TYPE;
    }
}
