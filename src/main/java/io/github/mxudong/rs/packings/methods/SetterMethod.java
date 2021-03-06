package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.classes.ClassObject;

import java.lang.reflect.Method;

/**
 * packing the setter method
 *
 * @author Dong
 * @since 3.0
 */

public class SetterMethod extends CommonMethod {

    private final static MethodType METHOD_TYPE = MethodType.SetterMethod;

    /**
     * method of {@code CommonMethod}, in this part,
     * the method will be append for packingMethod.
     * <p>
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     * @param classObject   method belong class object
     */
    public SetterMethod(Method packingMethod, ClassObject<?> classObject) {
        super(packingMethod, classObject);
    }

    /**
     * invoke the setter method
     *
     * @param target aim object
     * @param value  aim value
     */
    public void invoke(Object target, Object value) {
        super.invoke(target, value);
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
