package io.github.mxudong.rs.packings.methods;

import io.github.mxudong.rs.packings.AnnotationAble;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.utils.MethodUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class is packing the common method.
 * <p>
 * common methods do not include constructors,
 * static methods, Setter Method, and Getter Method.
 *
 * @author Dong
 * @since 3.0
 */

public class CommonMethod implements Invoker, AnnotationAble {

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
     * the field annotations
     */
    private AnnotationObject[] annotationObjects;

    /**
     * method of {@code CommonMethod}, in this part,
     * the method will be append for packingMethod.
     * <p>
     * the method info will be set in this part
     *
     * @param packingMethod the packing method
     * @param classObject   method belong class object
     */
    public CommonMethod(Method packingMethod, ClassObject<?> classObject) {
        this.packingMethod = packingMethod;
        this.belongClass = classObject;
        this.returnType = packingMethod.getReturnType();
        this.paramClasses = packingMethod.getParameterTypes();

        Annotation[] annotations = this.packingMethod.getDeclaredAnnotations();
        this.annotationObjects = new AnnotationObject[annotations.length];
        for (int i = 0; i < annotations.length; i++) {
            this.annotationObjects[i] = new AnnotationObject(annotations[i]);
        }
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

    @Override
    public String getMethodName() {
        return this.packingMethod.getName();
    }

    @Override
    public String toString() {
        return METHOD_TYPE.getType() + " - " + getMethodName() + " : " + getBelongClass().getName();
    }

    @Override
    public int getAnnotationCount() {
        return this.annotationObjects.length;
    }

    @Override
    public AnnotationObject getAnnotation(Class annotationClass) {
        for (AnnotationObject annotationObject : this.annotationObjects) {
            if (annotationObject.getAnnotationClass().getPackingClass().equals(annotationClass)) {
                return annotationObject;
            }
        }

        return null;
    }

    @Override
    public List<AnnotationObject> getAllAnnotation() {
        return new ArrayList<>(Arrays.asList(this.annotationObjects));
    }
}