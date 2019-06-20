package io.github.mxudong.rs.base.methods;

import io.github.mxudong.rs.base.utils.ClassUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Class Name : AbsConstructor
 * Create Time : 22:47
 * Create Date : 2019/6/7
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.0
 */

public class AbsConstructor<T>{
    private Constructor<T> constructor;
    private int paramCount;
    private Class[] paramClass;

    /**
     * Construction method
     *
     * @param c aim constructor
     */
    public AbsConstructor(Constructor<T> c) {
        this.constructor = c;
        this.paramCount = c.getParameterCount();
        this.paramClass = c.getParameterTypes();
    }

    /**
     * get Constructor param count
     *
     * @return the param count
     */
    public int getParamCount() {
        return paramCount;
    }

    /**
     * get new instance
     *
     * @param args init params
     * @return new instance of this constructor
     */
    public T invoke(Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * if the args is equals of this constructor method's init params,
     * will return true, else return false;
     *
     * @param args init params
     * @return is args equals of this constructor method's init params
     */
    public boolean isThisParams(Object... args) {
        if (args == null) {
            return this.paramCount == 0;
        }
        if (args.length != this.paramCount) {
            return false;
        }

        for (int i = 0; i < args.length; i++) {
            if (!paramClass[i].isInstance(args[i])) {
                if (ClassUtil.isBaseType(paramClass[i])) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    /**
     * getter of param-classes
     *
     * @return paramClass
     */
    public Class[] getParamClass() {
        return paramClass;
    }
}
