package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;

import java.lang.reflect.Method;

/**
 * packing the getter method
 *
 * @author Dong
 * @since 3.0
 */

public class GetterMethod extends CommonMethod {

    private final static MethodType METHOD_TYPE = MethodType.GetterMethod;

    /**
     * method of {@code CommonMethod}, in this part,
     * the method will be append for packingMethod.
     * <p>
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     * @param classObject   method belong class object
     */
    public GetterMethod(Method packingMethod, ClassObject<?> classObject) {
        super(packingMethod, classObject);
    }


    /**
     * the getter method invoke not required params
     *
     * @param target aim object
     * @return field value
     */
    public Object invoke(Object target) {
        return super.invoke(target);
    }

    @Override
    public MethodType getMethodType() {
        return METHOD_TYPE;
    }

    @Override
    public String toString() {
        return METHOD_TYPE.getType() + " - " + getMethodName() + " : " + getBelongClass().getName();
    }
}
