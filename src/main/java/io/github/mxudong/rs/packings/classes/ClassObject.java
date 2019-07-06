package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.exceptions.ReflectionException;
import io.github.mxudong.rs.packings.methods.ConstructMethod;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class packing the class, and for this class, you
 * only create from ClassObjectFactor
 *
 * @author Dong
 * @since 3.0
 */

public class ClassObject<T> {

    /*
    规划：
        包含重载方法集合
        包含全部字段集合
        包含所有实现的接口信息
        包含所有类级别的注解信息

        可通过此类实现实例方法
            默认和指定参数类型两种方式
        获取方法
        获取字段
        调用方法
        获取字段值
        判断是否私有、公开、默认、保护的权限

     */

    private List<ConstructMethod<T>> constructMethods;
    private ConstructMethod<T> defaultConstructorMethod = null;

    /**
     * packing class
     */
    private Class<T> packingClass;

    /**
     * super class object of packing class, will it null, it
     * is {@code Object}
     */
    private ClassObject<?> superClassObject;

    /**
     * construction method
     *
     * @param c packing class
     */
    protected ClassObject(@NotNull Class<T> c) {
        this.packingClass = c;
        superClassObject = ClassFactory.getInstance().getClassObject(c.getSuperclass());

        // get the methods for this class =========================================================
        Method[] methods = c.getDeclaredMethods();

        // get the construction of this class =====================================================
        this.constructMethods = new ArrayList<>();
        Constructor<T>[] constructors = (Constructor<T>[]) c.getConstructors();
        for (Constructor<T> constructor : constructors) {
            ConstructMethod<T> constructMethod = new ConstructMethod<>(constructor, this);
            if (constructMethod.isDefaultConstruction()) {
                this.defaultConstructorMethod = constructMethod;
            }
            this.constructMethods.add(constructMethod);
        }
    }

    /**
     * is has default constructor method
     *
     * @return if has return true, else return false
     */
    public boolean hasDefaultConstructorMethod() {
        return this.defaultConstructorMethod != null;
    }

    /**
     * get the newInstance of this packing class,
     * but if has no default construction, it will return null
     *
     * @return new instance
     */
    public T getNewInstance() {
        if (hasDefaultConstructorMethod()) {
            return defaultConstructorMethod.newInstance();
        } else {
            try {
                throw new ReflectionException("ClassObject", "getNewInstance()", "has no default construction");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * get the construction array
     *
     * @return construction array
     */
    public ConstructMethod<T>[] getConstructMethods() {
        ConstructMethod<T>[] constructMethods = new ConstructMethod[this.constructMethods.size()];
        return this.constructMethods.toArray(constructMethods);
    }

    /**
     * get the construction array length
     *
     * @return construction array length
     */
    public int getConstructionMethodCount() {
        return this.constructMethods.size();
    }

    /**
     * get the newInstance of this packing class,
     * but if can't match construction of params, it will return null
     *
     * @param params aim params
     * @return new instance
     */
    public T getNewInstance(Object... params) {
        for (ConstructMethod<T> constructMethod : this.constructMethods) {
            if (constructMethod.isParamsIsThisMethod(params)) {
                return constructMethod.newInstance(params);
            }
        }

        try {
            throw new ReflectionException("ClassObject", "getNewInstance()", "can find param match construction");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * return the packing class
     *
     * @return the packing class
     */
    public Class<T> getPackingClass() {
        return packingClass;
    }

    /**
     * getter method of superClassObject;
     *
     * @return super class object
     */
    public ClassObject<?> getSuperClassObject() {
        return superClassObject;
    }

    /**
     * return packing class' name
     * @return class' name
     */
    public String getPackingClassName(){
        return this.packingClass.getName();
    }
}
