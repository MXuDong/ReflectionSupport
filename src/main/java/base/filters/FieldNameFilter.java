package base.filters;

import java.lang.reflect.Field;

/**
 * @author Dong
 * @version 1.0
 * @since 1.0
 */
public interface FieldNameFilter {
    /**
     * 是否忽略某个字段
     * 当返回True的时候，不对fieldName
     * 进行拦截
     * 当返回False的时候，将会拦截
     * fieldName
     *
     * @param field 某个字段名字
     * @return true:不忽略;false:忽略
     */
    default boolean isDoFilter(Field field) {
        return true;
    }
}
