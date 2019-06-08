package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.methods.AbsConstructor;

import java.lang.reflect.Constructor;

/**
 * innerClass Name : ObjectReflector
 * Create Time : 21:55
 * Create Date : 2019/6/7
 * Project : ReflectionSupport
 * <p>
 * The purpose of this class is to handle the basic
 * calls calls of the parent class,
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
    private Class innerClass;

    private int defaultConstructorIndex;
    private AbsConstructor[] constructors;

    /**
     * construction method
     *
     * @param c innerClass
     */
    public ObjectReflector(Class c) {
        this.innerClass = c;

        Constructor[] constructors = c.getConstructors();
        this.constructors = new AbsConstructor[constructors.length];

        for (int i = 0; i < constructors.length; i++) {
            this.constructors[i] = new AbsConstructor(constructors[i]);
            if (this.constructors[i].getParamCount() == 0) {
                this.defaultConstructorIndex = i;
            }
        }
    }

    /**
     * do instance
     *
     * @return object
     */
    public Object getInstance() {
        if (defaultConstructorIndex != 0) {
            return null;
        }

        return constructors[defaultConstructorIndex].invoke(null);
    }

    /**
     * do instance
     *
     * @param args init params
     * @return Object of class
     */
    public Object getInstance(Object... args) {
        for(AbsConstructor absConstructor : constructors){
            if(absConstructor.isThisParams(args)){
                return absConstructor.invoke(args);
            }
        }

        return null;
    }

    /**
     * getter of innerClass
     *
     * @return inner class
     */
    public Class getInnerClass() {
        return innerClass;
    }
}