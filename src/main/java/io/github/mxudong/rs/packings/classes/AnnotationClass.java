package io.github.mxudong.rs.packings.classes;

import org.jetbrains.annotations.NotNull;

/**
 * the AnnotationClass packing the Annotation object, provide some
 * method for operating the packing annotation object.
 * <p>
 * you can use this class to get all info of the target annotation.
 *
 * @author Dong
 * @since 3.0
 */

public class AnnotationClass extends ClassObject{

    /**
     * construction method
     *
     * @param c packing class
     */
    protected AnnotationClass(@NotNull Class c) {
        super(c);
    }

    /**
     * the annotation type
     */
    private ObjectType objectType = ObjectType.ANNOTATION;

    public Object invokeMethod(String methodName, Object target) {
        return super.invokeMethod(methodName, target);
    }

    @Override
    public String toString() {
        return objectType.getType() + ":" + super.getPackingClassName();
    }
}
