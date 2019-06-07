package io.github.mxudong.rs.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * innerClass Name : ObjectReflector
 * Create Time : 21:55
 * Create Date : 2019/6/7
 * Project : ReflectionSupport
 * <p>
 * The purpose of this class is to handle the basic
 * calls and static method calls of the parent class,
 * ignoring the differences caused by instances.
 * <p>
 * Including methods of statistics,
 * example methods, static methods,
 * array generation and other common
 * interfaces.
 *
 * @author Dong
 * @since 2.0
 */

public class ObjectReflector {

    /**
     * inner class
     */
    Class innerClass = null;

    private Constructor defaultConstructor;
    private Constructor [] allConstructor;

    /**
     * construction method
     *
     * @param c innerClass
     */
    public ObjectReflector(Class c) {
        this.innerClass = c;

        Constructor[] constructors = c.getConstructors();
        if (constructors.length == 1) {
            defaultConstructor = constructors[0];
        }

        allConstructor = constructors;
    }

    /**
     * do instance
     *
     * @param args constructor params
     * @return object
     */
    public Object getInstance(Object... args) {
        Object o = null;
        try {
            o = defaultConstructor.newInstance(args);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}