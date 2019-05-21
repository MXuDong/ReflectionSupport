package io.github.mxudong.rs.base.filters;

import java.lang.reflect.Field;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface FieldNameFilter {
    /**
     * Whether to ignore a field when returning to
     * True, not to intercept fieldName when returning
     * to False, will intercept fieldName
     *
     * @param field A field name
     * @return true:not ignore;false:ignore
     */
    default boolean isDoFilter(Field field) {
        return true;
    }
}
