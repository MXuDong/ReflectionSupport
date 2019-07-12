package io.github.mxudong.rs.packings.classes;

import java.lang.annotation.Annotation;

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
        this.annotationClass = ObjectFactory.getInstance().getAnnotationClass(annotation.getClass());
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
}
