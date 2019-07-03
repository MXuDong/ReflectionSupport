package io.github.mxudong.rs.packings.methods;

/**
 * it marked the class is packing of {@code method}, and it can be invoked.
 *
 * @author Dong
 */
public interface Invoker {

    /**
     * the method {@code object invoke(Object target, Object ... params} will invoke method
     * which is packing.
     *
     * @param target the aim target of invoking method
     * @param params the parameters required for the method to be invoked
     * @return the result of invoke
     * @since 3.0
     */
    Object invoke(Object target, Object... params);

    /**
     * get the method's type
     *
     * @return the method's type
     * @see MethodType
     * @since 3.0
     */
    MethodType getMethodType();
}
