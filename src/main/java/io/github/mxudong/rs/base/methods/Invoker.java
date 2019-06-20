package io.github.mxudong.rs.base.methods;

/**
 * @author Dong
 * @see java.lang.reflect.Method
 * @since 2.0
 * <p>
 * The interface {@code invoker} mark class can be
 * invoke.
 * <p>
 * And this interface support some {@code method}'s method
 */
public interface Invoker {

    /**
     * invoke method
     *
     * @param target the invoke target
     * @param args   the method needs params
     * @return invoke result
     */
    Object invoke(Object target, Object... args);

    /**
     * Get the parameters required for the call
     *
     * @return the parameters
     */
    Class[] getParams();

    /**
     * get the parameters' count required for the call
     *
     * @return the count of parameters
     */
    int getParamsCount();

    /**
     * get the method return type.
     *
     * @return if return null:the method return type is void,else return return type
     */
    Class<?> getReturnType();

    /**
     * to equals args with inner params type;
     *
     * @param args
     * @return
     */
    boolean isThisArgs(Object... args);
}
