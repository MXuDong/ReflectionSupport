package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.packings.methods.Invoker;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * packing the annotation object
 *
 * @author Dong
 * @since 3.0
 */

public class AnnotationObject {

    /**
     * target annotation class
     */
    private AnnotationClass annotationClass;

    /**
     * target annotation
     */
    private Annotation packingAnnotation;

    /**
     * the construction
     *
     * @param annotation traget annotation
     */
    public AnnotationObject(Annotation annotation) {
        this.packingAnnotation = annotation;
        this.annotationClass = ObjectFactory.getInstance().getAnnotationClass(annotation.annotationType());
    }

    /**
     * get the annotation class
     *
     * @return target annotation class
     */
    public AnnotationClass getAnnotationClass() {
        return this.annotationClass;
    }

    /**
     * get the annotation field value
     *
     * @param annotationField the value which you want get
     * @return value
     */
    public Object getInfo(String annotationField) {
        return annotationClass.invokeMethod(annotationField, packingAnnotation);
    }

    /**
     * the annotation default method contain
     * 1.equals
     * 2.toString
     * 3.hashCode
     * 4.annotationType
     * so the string is method but not contain these method
     *
     * @return contain the method name and value
     */
    public Map<String, Object> turnToMap() {
        ArrayList<Invoker> invokers = this.annotationClass.getAllMethods();

        Map<String, Object> result = new HashMap<>(invokers.size());
        for (Invoker i : invokers) {
            if ("equals".equals(i.getMethodName()) ||
                    "toString".equals(i.getMethodName()) ||
                    "hashCode".equals(i.getMethodName()) ||
                    "annotationType".equals(i.getMethodName())) {
                continue;
            }

            result.put(i.getMethodName(), i.invoke(this.packingAnnotation));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Annotation : " + this.packingAnnotation.getClass().getName();
    }
}
