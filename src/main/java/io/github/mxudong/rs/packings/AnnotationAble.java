package io.github.mxudong.rs.packings;

import io.github.mxudong.rs.packings.classes.AnnotationObject;

import java.util.List;

/**
 * AnnotationAble interface provide some method to
 * operation annotation of this class
 *
 * if implement this interface, that mark the class can
 * operation annotation, and in this class, it must save the annotation info
 *
 * @author Dong
 */
public interface AnnotationAble {

    /**
     * get the annotation info count
     * @return the count of annotation info
     */
    int getAnnotationCount();

    /**
     * get the annotation object
     * @param annotationClass target annotation class
     * @return annotationObject
     */
    AnnotationObject getAnnotation(Class annotationClass);

    /**
     * get the all annotation object
     * @return list of annotation object
     */
    List<AnnotationObject> getAllAnnotation();
}
