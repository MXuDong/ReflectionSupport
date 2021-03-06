package io.github.mxudong.rs.base.encapsulator;

import java.lang.reflect.Method;

/**
 * Class Name : SetterMethodInvoker
 * Create Time : 12:03
 * Create Date : 2019/6/9
 * Project : ReflectionSupport
 * <p>
 * {@code SetterMethodInvoker} extends {@code MethodInvoker}
 *
 * @author Dong
 * @since 2.0
 */

public class SetterMethodInvoker extends MethodInvoker {
    /**
     * construction method
     *
     * @param method the insert method
     */
    public SetterMethodInvoker(Method method) {
        super(method);
    }

    /**
     * invoke setter method
     *
     * @param target aim object
     * @param args   be set value
     * @return should be null
     */
    public Object invoke(Object target, Object args) {
        return super.invoke(target, args);
    }
}
