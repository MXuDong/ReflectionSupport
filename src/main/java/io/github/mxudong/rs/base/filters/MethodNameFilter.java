package io.github.mxudong.rs.base.filters;

import java.lang.reflect.Method;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface MethodNameFilter {
    /**
     * filter interface
     *
     * @param method the method's name which will be checked
     * @return true:can operation;false:Not filtered (not operated)
     */
    default boolean isDoFilter(Method method) {
        return true;
    }
}
