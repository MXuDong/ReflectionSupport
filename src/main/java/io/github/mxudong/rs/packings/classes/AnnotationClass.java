package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.packings.methods.CommonMethod;
import io.github.mxudong.rs.packings.methods.Invoker;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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

    public Object invokeMethod(String methodName, Object target) {
        return super.invokeMethod(methodName, target);
    }


}
