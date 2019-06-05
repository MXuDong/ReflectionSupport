package io.github.mxudong.rs.base.methods;

import java.lang.reflect.Method;

/**
 * Class Name : GetterMethodInvoker
 * Create Time : 15:01
 * Create Date : 2019/6/2
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.0
 */

public class GetterMethodInvoker extends MethodInvoker {



    /**
     * Construction method
     * @param method the getter method
     */
    public GetterMethodInvoker(Method method) {
        super(method);
    }
}
