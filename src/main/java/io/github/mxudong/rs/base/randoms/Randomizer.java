package io.github.mxudong.rs.base.randoms;

import io.github.mxudong.rs.base.ObjectReflector;
import io.github.mxudong.rs.base.ReflectorFactory;
import io.github.mxudong.rs.base.methods.SetterMethodInvoker;
import io.github.mxudong.rs.base.strings.StringExtension;

import java.util.Iterator;

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

    /**
     * create a new object and do random
     *
     * @return new object and do random
     */
    public T getNewInstance() {
        T object = objectReflector.getInstance();
        return doRandom(object, false);
    }

    public T doRandom() {
        if (innerObject == null) {
            innerObject = objectReflector.getInstance();
        }
        return doRandom(innerObject, true);
    }

    /**
     * random a object
     *
     * @param object be random object
     * @param doDeep is do random until object
     * @return random processing objects
     */
    private T doRandom(T object, boolean doDeep) {
        Iterator<SetterMethodInvoker> setterMethodInvokerIterator = objectReflector.setterMethodIterator();
        while (setterMethodInvokerIterator.hasNext()) {
            SetterMethodInvoker setterMethodInvoker = setterMethodInvokerIterator.next();
            Object[] params = setterMethodInvoker.getParams();
            Object[] setterParams = new Object[setterMethodInvoker.getParamsCount()];

            for (int i = 0; i < params.length; i++) {
                Object param = createRandomObject(params[i].toString());
                if (param == null && doDeep) {
                    param = new Randomizer(param.getClass()).doRandom();
                }
                setterParams[i] = param;
            }
            setterMethodInvoker.invoke(object, setterParams);
        }
        return object;
    }

    /**
     * create a new object and update inner object to new object
     *
     * @return new object
     */
    public T loadAndGetNewInstance() {
        T newInstance = getNewInstance();
        this.innerObject = newInstance;
        return newInstance;
    }

    /**
     * create simple object of base type
     *
     * @param paramType class name
     * @return random type
     */
    private Object createRandomObject(String paramType) {
        switch (paramType) {
            case "java.lang.Byte":
            case "byte":
                return BaseRandom.getRandomByte();
            case "java.lang.Short":
            case "short":
                return BaseRandom.getRandomShort();
            case "java.lang.Integer":
            case "int":
                return BaseRandom.getRandomInt();
            case "java.lang.Long":
            case "long":
                return BaseRandom.getRandomLong();
            case "java.lang.Character":
            case "char":
                return BaseRandom.getRandomCharFromAllChar();
            case "java.lang.Double":
            case "double":
                return BaseRandom.getRandomDouble();
            case "java.lang.Float":
            case "float":
                return BaseRandom.getRandomFloat();
            case "java.lang.Boolean":
            case "boolean":
                return BaseRandom.getRandomBoolean();
            case "java.lang.String":
                return StringExtension.createRandomString();
            default:
                return null;
        }
    }
}
