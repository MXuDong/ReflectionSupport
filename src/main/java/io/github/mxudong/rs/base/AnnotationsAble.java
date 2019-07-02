package io.github.mxudong.rs.base;

import java.lang.annotation.Annotation;

/**
 * support method with annotation
 *
 * @author Dong
 */
public interface AnnotationsAble {
    /**
     * judge is had the annotation of aim annotation
     *
     * @param annotationClass be check annotation's class
     * @return true or false
     */
    boolean hasAnnotation(Class annotationClass);

    /**
     * get the annotation's count in some scopes
     *
     * @return count of annotation
     */
    int getAnnotationCount();

    /**
     * get the aim annotation, if not exits, return null
     *
     * @param annotationClass aim annotation's class
     * @param <A>             Default class type
     * @return aim annotation or null
     */
    <A extends Annotation> A getAnnotation(Class<A> annotationClass);
}
