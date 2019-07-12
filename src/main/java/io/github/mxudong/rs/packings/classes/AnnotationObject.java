package io.github.mxudong.rs.packings.classes;

import java.lang.annotation.Annotation;

/**
 * the AnnotationObject packing the Annotation object, provide some
 * method for operating the packing annotation object.
 * <p>
 * you can use this class to get all info of the target annotation.
 *
 * @author Dong
 * @since 3.0
 */

public class AnnotationObject {
    private Class packingAnnotation;

    /**
     * construction method
     *
     * @param packingAnnotation target annotation's class
     */
    public AnnotationObject(Class packingAnnotation) {
        this.packingAnnotation = packingAnnotation;
    }

    /**
     * get the packing annotation
     *
     * @return packing annotation
     */
    public Class getPackingAnnotation() {
        return this.packingAnnotation;
    }
}
