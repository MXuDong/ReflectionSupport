package io.github.mxudong.rs.base.methods;

import io.github.mxudong.rs.exceptions.NullParamException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class Name : MethodInvoker
 * Create Time : 10:19
 * Create Date : 2019/5/31
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.0
 */

public class MethodInvoker implements Invoker {

    /**
     * method body
     */
    private Method method;

    /**
     * method return type
     */
    private Class<?> returnType;

    /**
     * construction method
     * @param method the insert method
     */
    public MethodInvoker(Method method){
        //==================method can't be null
        if (method == null){
            try {
                throw new NullParamException("MethodInvoker", "method");
            } catch (NullParamException e) {
                e.printStackTrace();
            }
        }
        this.method = method;
        this.returnType = method.getReturnType();
    }

    @Override
    public Object invoke(Object target, Object[] args) {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }
}
