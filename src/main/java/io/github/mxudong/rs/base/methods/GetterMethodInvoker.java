package io.github.mxudong.rs.base.methods;

import java.lang.reflect.Method;

/**
 * Class Name : GetterMethodInvoker
 * Create Time : 15:01
 * Create Date : 2019/6/2
 * Project : ReflectionSupport
 * <p>
 * {@code GetterMethodInvoker} extends {@code MethodInvoker}
 * and the )GetterMethodInvoker  override invoke method, so
 * if you want do invoke getter method, needn't any param
 *
 * @author Dong
 * @since 2.0
 * @see MethodInvoker
 * @see Invoker
 */

public class GetterMethodInvoker extends MethodInvoker {


    /**
     * Construction method
     *
     * @param method the getter method
     */
    public GetterMethodInvoker(Method method) {
        super(method);
    }

    /**
     * invoke getter method
     *
     * @param target be invoke method's object
     * @return result of getter method
     */
    public Object invoke(Object target) {
        return super.invoke(target, null);
    }
}
