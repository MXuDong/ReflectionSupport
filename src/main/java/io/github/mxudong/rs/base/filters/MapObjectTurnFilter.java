package io.github.mxudong.rs.base.filters;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface MapObjectTurnFilter {
    /**
     * 通过传入参数名判断该名字段是否需要被过滤，
     * 如果返回值为True 则将放行本次操作，（
     * 该操作会被执行）
     * 如果返回值为False 则将驳回本次操作，（
     * 不处理该操作）
     *
     * @param propertyName 是否进行过滤的参数名
     * @return true:可以操作;false:不通过过滤（不被操作）
     */
    default boolean isDoFilter(String propertyName) {
        return true;
    }
}
