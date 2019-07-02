package io.github.mxudong.rs.base;

/**
 * support method with annotation
 *
 * @author Dong
 */
public interface AnnotationAble {
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
}
