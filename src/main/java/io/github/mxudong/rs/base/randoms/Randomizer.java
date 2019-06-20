package io.github.mxudong.rs.base.randoms;

import io.github.mxudong.rs.base.ObjectReflector;
import io.github.mxudong.rs.base.ReflectorFactory;

/**
 * Class Name : Randomizer
 * Create Time : 20:06
 * Create Date : 2019/6/19
 * Project : ReflectionSupport
 * <p>
 * Class randomizer, used to randomly generate objects
 *
 * @author Dong
 * @since 2.1
 */

public class Randomizer<T extends Object> {

    private ObjectReflector<T> objectReflector;
    private T innerObject = null;

    /**
     * construction method
     *
     * @param tClass for input class of randomizer
     */
    public Randomizer(Class<T> tClass) {
        this.objectReflector = (ObjectReflector<T>) ReflectorFactory.getInstance().getObjectReflector(tClass, true);
        innerObject = (T) objectReflector.getInstance();
    }

    /**
     * getter inner object
     *
     * @return innerObject
     */
    public T getInnerObject() {
        return innerObject;
    }
}
