package io.github.mxudong.rs.base.methods;

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

public class AbsConstructor extends MethodInvoker{
    private Constructor constructor;
    private int paramCount;
    private Class[] paramClass;

    /**
     * Construction method
     *
     * @param c aim constructor
     */
    public AbsConstructor(Constructor c) {
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
        return ;
    }

    /**
     * get new instance
     *
     * @param args init params
     * @return new instance of this constructor
     */
    public Object invoke(Object... args) {
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
