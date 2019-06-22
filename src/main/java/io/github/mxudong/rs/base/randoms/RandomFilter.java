package io.github.mxudong.rs.base.randoms;

/**
 * Class Name : RandomFilter
 * Create Time : 21:06
 * Create Date : 2019/6/22
 * Project : ReflectionSupport
 * <p>
 * Restriction and Filtering Random Generation
 *
 * @author Dong
 * @since 2.0
 */

public interface RandomFilter {

    /**
     * Whether Random Generation is Allowed
     *
     * @param methodName method's name
     * @param params     method need params
     * @return is can do random
     */
    default boolean canDoRandom(String methodName, Object[] params) {
        return true;
    }

    /**
     * Whether to skip to this class generation method for generation
     *
     * @param methodName method's name
     * @param params     method need params
     * @return is can skip to this class generation method for generation
     */
    default boolean isSpecifiedGeneration(String methodName, Object[] params) {
        return false;
    }

    /**
     * Method parameter generation
     *
     * @param methodName method's name
     * @param params     method need params
     * @return the params of method
     */
    default Object[] methodParamCreater(String methodName, Object[] params) {
        return null;
    }
}
