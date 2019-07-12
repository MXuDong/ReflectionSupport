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
     * the construction
     *
     * @param annotation traget annotation
     */
    public AnnotationObject(Annotation annotation) {
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
}
