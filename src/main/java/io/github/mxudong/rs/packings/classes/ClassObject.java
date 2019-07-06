package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.exceptions.ReflectionException;
import io.github.mxudong.rs.packings.methods.*;
import io.github.mxudong.rs.utils.MethodUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

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

    /**
     * the method truly info
     */
    private Invoker[] invokers;

    /**
     * all the common methods index, and the key is method's name
     */
    private Map<String, List<Integer>> commonMethods;
    /**
     * all the static methods index, and the key is method's name
     * <p>
     * and if some method is static and at same time it is a
     * getter method or setter method, it will in {@code staticMethods},
     * but not in {@code setterMethods} or {@code getterMethods}
     */
    private Map<String, List<Integer>> staticMethods;
    /**
     * all the setter method index, and the key is method's name
     */
    private Map<String, List<Integer>> setterMethods;
    /**
     * all teh getter method index, and the key is method's name
     */
    private Map<String, List<Integer>> getterMethods;

    /**
     * constructors of packing class
     *
     * @see ConstructMethod
     */
    private ConstructMethod<T>[] constructMethods;
    /**
     * default constructor of packing class
     *
     * @see ConstructMethod
     */
    private int defaultConstructorMethod = -1;

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

        // init some properties
        this.staticMethods = new HashMap<>();
        this.getterMethods = new HashMap<>();
        this.setterMethods = new HashMap<>();
        this.commonMethods = new HashMap<>();

        // get the methods for this class =========================================================
        Method[] methods = c.getDeclaredMethods();
        this.invokers = new Invoker[methods.length];

        for (int i = 0; i < methods.length; i++) {

            Invoker invoker;

            if (MethodUtil.isStaticMethod(methods[i])) {
                invoker = new StaticMethod(methods[i], this);
                if (!this.staticMethods.containsKey(invoker.getMethodName())) {
                    this.staticMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.staticMethods.get(invoker.getMethodName()).add(i);
            } else if (MethodUtil.isGetterMethod(methods[i])) {
                invoker = new GetterMethod(methods[i], this);
                if (!this.getterMethods.containsKey(invoker.getMethodName())) {
                    this.getterMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.getterMethods.get(invoker.getMethodName()).add(i);
            } else if (MethodUtil.isSetterMethod(methods[i])) {
                invoker = new SetterMethod(methods[i], this);
                if (!this.setterMethods.containsKey(invoker.getMethodName())) {
                    this.setterMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.setterMethods.get(invoker.getMethodName()).add(i);
            } else {
                invoker = new CommonMethod(methods[i], this);
                if (!this.commonMethods.containsKey(invoker.getMethodName())) {
                    this.commonMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.commonMethods.get(invoker.getMethodName()).add(i);
            }

            this.invokers[i] = invoker;
        }

        // get the construction of this class =====================================================
        Constructor<T>[] constructors = (Constructor<T>[]) c.getConstructors();
        this.constructMethods = new ConstructMethod[constructors.length];

        for (int i = 0; i < constructors.length; i++) {
            ConstructMethod<T> constructMethod = new ConstructMethod<>(constructors[i], this);
            if (constructMethod.isDefaultConstruction()) {
                defaultConstructorMethod = i;
            }
            this.constructMethods[i] = constructMethod;
        }
    }

    /**
     * is has default constructor method
     *
     * @return if has return true, else return false
     */
    public boolean hasDefaultConstructorMethod() {
        return this.defaultConstructorMethod != -1;
    }

    /**
     * get the newInstance of this packing class,
     * but if has no default construction, it will return null
     *
     * @return new instance
     */
    public T getNewInstance() {
        if (hasDefaultConstructorMethod()) {
            return this.constructMethods[defaultConstructorMethod].newInstance();
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
        return Arrays.copyOf(this.constructMethods, this.constructMethods.length);
    }

    /**
     * get the all methods
     *
     * @return all methods
     * @see Invoker
     */
    public Invoker[] getAllmethods() {
        return Arrays.copyOf(this.invokers, this.invokers.length);
    }

    /**
     * get count of method
     *
     * @return the count of method
     */
    public int getMethodCount() {
        return this.invokers.length;
    }

    /**
     * get the method invoker
     *
     * @param methodName aim method's name
     * @param params     the method declared params
     * @return if exits return it, else not in this packing,
     * will find from superClassObject, until null, if not exits, return null
     */
    public Invoker getMethodInvoker(String methodName, Object... params) {
        for (Invoker invoker : this.invokers) {
            if (invoker.getMethodName().equals(methodName)) {
                if (invoker.isParamsIsThisMethod(params)) {
                    return invoker;
                }
            }
        }

        if (superClassObject == null) {
            return null;
        }

        return superClassObject.getMethodInvoker(methodName, params);
    }

    /**
     * get the construction array length
     *
     * @return construction array length
     */
    public int getConstructionMethodCount() {
        return this.constructMethods.length;
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
     *
     * @return class' name
     */
    public String getPackingClassName() {
        return this.packingClass.getName();
    }

    /**
     * is class public
     *
     * @return if class is public return true, else return false
     */
    public boolean isPublic() {
        return Modifier.isPublic(packingClass.getModifiers());
    }

    @Override
    public String toString() {
        return this.packingClass.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClassObject) {
            return this.packingClass.equals(obj);
        }
        return false;
    }
}
