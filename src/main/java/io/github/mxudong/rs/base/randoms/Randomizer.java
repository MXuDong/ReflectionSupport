package io.github.mxudong.rs.base.randoms;

import io.github.mxudong.rs.base.ObjectReflector;
import io.github.mxudong.rs.base.ReflectorFactory;
import io.github.mxudong.rs.base.methods.SetterMethodInvoker;
import io.github.mxudong.rs.base.strings.StringExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private List<RandomFilter> randomFilters;

    /**
     * construction method
     *
     * @param tClass for input class of randomizer
     */
    public Randomizer(Class<T> tClass) {
        randomFilters = new ArrayList<>();
        this.objectReflector = (ObjectReflector<T>) ReflectorFactory.getInstance().getObjectReflector(tClass, true);
        innerObject = (T) objectReflector.getInstance();
    }

    /**
     * add random filter
     * <p>
     * When at least one filter result is not random among
     * multiple filters, the randomization of the method will stop.
     * <p>
     * When a filter can assign a specified value, other filters
     * will be ignored, whether randomization is allowed or not.
     * <p>
     * The filter order depends on the assignment order.
     *
     * @param randomFilter be added random filter
     */
    public void addRandomFilter(RandomFilter randomFilter) {
        randomFilters.add(randomFilter);
    }

    /**
     * remove random filter
     *
     * @param randomFilter be remove filter
     * @return is not exits return false, else return true
     */
    public boolean removeRandomFilter(RandomFilter randomFilter) {
        return randomFilters.remove(randomFilter);
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

    /**
     * do random of object
     * <p>
     * Reference types will be nulled
     *
     * @return random object
     */
    public T doRandom() {
        if (innerObject == null) {
            innerObject = objectReflector.getInstance();
        }
        return doRandom(innerObject, false);
    }

    /**
     * Random Depth
     * <p>
     * All reference types and primitive types are randomly assigned
     *
     * @return random object
     */
    public T doRandomDeep() {
        if (innerObject == null) {
            innerObject = objectReflector.getInstance();
        }
        return doRandom(innerObject, false);
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

            boolean doRandom = true;

            for (RandomFilter randomFilter : randomFilters) {
                if (!randomFilter.canDoRandom(setterMethodInvoker.getMethodName(), params)) {
                    doRandom = false;
                    break;
                }
                if (randomFilter.isSpecifiedGeneration(setterMethodInvoker.getMethodName(), params)) {
                    setterParams = randomFilter.methodParamCreater(setterMethodInvoker.getMethodName(), params);
                    setterMethodInvoker.invoke(object, setterParams);
                    doRandom = false;
                    break;
                }
            }

            if (!doRandom) {
                continue;
            }

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
