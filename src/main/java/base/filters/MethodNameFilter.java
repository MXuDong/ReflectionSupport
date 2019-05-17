package base.filters;

import java.lang.reflect.Method;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface MethodNameFilter {
    /**
     * 过滤接口
     *
     * @param method 当前需要判断的method
     * @return 是否允许被添加
     */
    default boolean isDoFilter(Method method) {
        return true;
    }
}
