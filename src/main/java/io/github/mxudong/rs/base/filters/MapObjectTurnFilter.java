package io.github.mxudong.rs.base.filters;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface MapObjectTurnFilter {
    /**
     * If the return value is True, the operation will
     * be released. (The operation will be executed.)
     * If the return value is False, the operation will
     * be rejected. (The operation will not be processed.)
     *
     * @param propertyName Parameter names for filtering or not
     * @return true:can operation;false:Not filtered (not operated)
     */
    default boolean isDoFilter(String propertyName) {
        return true;
    }
}
