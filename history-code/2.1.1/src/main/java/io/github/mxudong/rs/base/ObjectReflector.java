package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.encapsulator.*;
import io.github.mxudong.rs.base.strings.StringExtension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
 * Including packings of statistics,
 * example packings, static packings,
 * array generation and other common
 * interfaces.
 *
 * @author Dong
 * @since 2.0
 */

final public class ObjectReflector<T> {

    /**
     * inner class
     */
    private Class<T> innerClass;
    private String packageName;
    private String className;

    private int defaultConstructorIndex = -1;
    private AbsConstructor<T>[] constructors;

    private Map<String, GetterMethodInvoker> readableProperty;
    private Map<String, SetterMethodInvoker> writableProperty;

    private Map<String, List<Invoker>> staticMethods;
    private Map<String, List<Invoker>> commonMethods;

    private List<String> properties;

    private Annotation[] classAnnotations;

    public Annotation[] getClassAnnotations() {
        return classAnnotations;
    }

    /**
     * the inner class's super class
     * <p>
     * if the inner class is of the  object , this field must be null.
     */
    private ObjectReflector fatherObjectReflector;

    /**
     * construction method
     *
     * @param c inner class
     */
    protected ObjectReflector(Class<T> c) {
        this(c, false);
    }

    /**
     * construction method
     *
     * @param c              innerClass
     * @param loadSuperClass true:load this class's supperClass until Object, else do nothing
     */
    protected ObjectReflector(Class<T> c, boolean loadSuperClass) {
        this.innerClass = c;

        this.classAnnotations = innerClass.getAnnotations();

        if (c.equals(Object.class) || !loadSuperClass) {
            fatherObjectReflector = null;
        } else {
            fatherObjectReflector = ReflectorFactory.getInstance().getObjectReflector(c.getSuperclass(), true);
        }

        //write base information of class:c
        String[] packages = c.getName().split("\\.");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < packages.length - 1; i++) {
            stringBuffer.append(packages[i]).append(".");
        }
        this.packageName = stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        this.className = packages[packages.length - 1];


        //================================Choose construction
        Constructor[] constructors = c.getConstructors();
        this.constructors = new AbsConstructor[constructors.length];
        for (int i = 0; i < constructors.length; i++) {
            this.constructors[i] = new AbsConstructor<T>(constructors[i]);
            if (this.constructors[i].getParamCount() == 0) {
                this.defaultConstructorIndex = i;
            }
        }

        //================================Create property list
        properties = new ArrayList<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            properties.add(field.getName());
        }

        //================================Create packings list
        readableProperty = new HashMap<>();
        writableProperty = new HashMap<>();
        staticMethods = new HashMap<>();
        commonMethods = new HashMap<>();

        for (Method m : c.getDeclaredMethods()) {
            String name = m.getName();
            if (MethodInvoker.isGetterMethod(name)) {
                name = StringExtension.getGetterMethodProperty(name);
                if (properties.contains(name)) {
                    readableProperty.put(name, new GetterMethodInvoker(m));
                    continue;
                }
            } else if (MethodInvoker.isSetterMethod(name)) {
                name = StringExtension.getSetterMethodProperty(name);
                if (properties.contains(name)) {
                    writableProperty.put(name, new SetterMethodInvoker(m));
                    continue;
                }
            }

            if (MethodInvoker.isStaticMethod(m)) {
                if (!staticMethods.containsKey(name)) {
                    staticMethods.put(name, new ArrayList<>());
                }
                staticMethods.get(name).add(new MethodInvoker(m));
            } else {
                if (!commonMethods.containsKey(name)) {
                    commonMethods.put(name, new ArrayList<>());
                }
                commonMethods.get(name).add(new MethodInvoker(m));
            }
        }
    }

    /**
     * Generate an array of InnerClass instances
     *
     * @param length the length of array
     * @return new array
     */
    public Object[] createArray(int length) {
        if (length < 0) {
            return null;
        }
        Object[] result = (Object[]) Array.newInstance(innerClass, length);
        return result;
    }

    /**
     * Generate and populate a new array
     *
     * @param length the length of array
     * @return new array
     */
    public Object[] createArrayAndFill(int length) {
        if (length < 0) {
            return null;
        }
        Object[] result = createArray(length);
        for (int i = 0; i < result.length; i++) {
            result[i] = this.getInstance();
        }

        return result;
    }

    /**
     * get super class's  ObjectReflector  object
     *
     * @return super class  ObjectReflector  object
     */
    public ObjectReflector<?> getSuperObjectReflector() {
        return fatherObjectReflector;
    }

    /**
     * to load super class's  ObjectReflector 's object
     *
     * @param isOnce is true, only load first super class else until Object class
     * @return inner class's super object reflector
     */
    public ObjectReflector<?> loadSuperObjectReflector(boolean isOnce) {
        fatherObjectReflector = ReflectorFactory.getInstance().getObjectReflector(innerClass.getSuperclass(), isOnce);
        return fatherObjectReflector;
    }

    /**
     * invoke common method from common method list
     *
     * @param methodName method name
     * @param args       invoke params
     * @param target     be invoke object
     * @return invoke result
     */
    public Object invokeCommonMethod(String methodName, Object target, Object... args) {
        return invokeCommonMethod(methodName, target, 0, args);
    }

    /**
     * invoke common method form method list, if not exits, will search from super class
     * until  superClassSearchDeep  equals  0
     *
     * @param methodName           method name
     * @param superClassSearchDeep search super class deep, is little then 0, then will until Object.class
     * @param args                 params
     * @param target               be invoke object
     * @return invoke result
     */
    public Object invokeCommonMethod(String methodName, Object target, int superClassSearchDeep, Object... args) {
        Object result = null;
        if (commonMethods.containsKey(methodName)) {
            result = getObject(methodName, target, result, commonMethods, args);
        } else {
            if (superClassSearchDeep != 0) {
                if (innerClass.equals(Object.class)) {
                    return null;
                }
                if (fatherObjectReflector == null) {
                    loadSuperObjectReflector(true);
                }
                result = fatherObjectReflector.invokeCommonMethod(methodName, target, superClassSearchDeep - 1, args);
            }
        }

        return result;
    }

    private Object getObject(String methodName, Object target, Object result, Map<String, List<Invoker>> commonMethods, Object[] args) {
        List<Invoker> invokers = commonMethods.get(methodName);
        for (Invoker invoker : invokers) {
            if (invoker.isThisArgs(args)) {
                result = invoker.invoke(target, args);
                break;
            }
        }
        return result;
    }

    /**
     * invoke getter method from getter method list
     *
     * @param propertyName be got property name
     * @return property value
     */
    public Object invokeGetterMethod(String propertyName, Object target) {
        return invokeGetterMethod(propertyName, target, 0);
    }

    /**
     * invoke getter method form method list, if not exits, will search from super class
     * until  superClassSearchDeep  equals  0
     *
     * @param propertyName         property name
     * @param target               be invoke object
     * @param superClassSearchDeep search super class deep, is little then 0, then will until Object.class
     * @return getter return
     */
    public Object invokeGetterMethod(String propertyName, Object target, int superClassSearchDeep) {
        Object result = null;
        if (readableProperty.containsKey(propertyName)) {
            result = readableProperty.get(propertyName).invoke(target);
        } else {
            if (superClassSearchDeep != 0) {
                if (innerClass.equals(Object.class)) {
                    return null;
                }
                if (fatherObjectReflector == null) {
                    loadSuperObjectReflector(true);
                }
                result = fatherObjectReflector.invokeGetterMethod(propertyName, target, superClassSearchDeep - 1);
            }
        }

        return result;
    }

    /**
     * invoke setter method from setter method list
     *
     * @param propertyName be set property name
     * @param param        be set value
     * @param target       target object
     */
    public void invokeSetterMethod(String propertyName, Object target, Object param) {
        invokeSetterMethod(propertyName, target, param, 0);
    }

    /**
     * invoke setter method form method list, if not exits, will search from super class
     * until  superClassSearchDeep  equals  0
     *
     * @param propertyName         property name
     * @param target               be invoke object
     * @param param                input params
     * @param superClassSearchDeep search super class deep, is little then 0, then will until Object.class
     */
    public void invokeSetterMethod(String propertyName, Object target, Object param, int superClassSearchDeep) {
        if (writableProperty.containsKey(propertyName)) {
            writableProperty.get(propertyName).invoke(target, param);
        } else {
            if (superClassSearchDeep != 0) {
                if (innerClass.equals(Object.class)) {
                    return;
                }
                if (fatherObjectReflector == null) {
                    loadSuperObjectReflector(true);
                }
                fatherObjectReflector.invokeSetterMethod(propertyName, target, param, superClassSearchDeep - 1);
            }
        }
    }

    /**
     * invoke static method form static method list
     *
     * @param methodName be invoke name
     * @param params     invoke params
     * @param target     target object
     * @return invoke result
     */
    synchronized public Object invokeStaticMethod(String methodName, Object target, Object... params) {
        return invokeStaticMethod(methodName, target, 0, params);
    }

    /**
     * to invoke static method, if this inner class hasn't, will search from super class
     * this method is synchronized, but please use as little as possible
     *
     * @param methodName           be invoke name
     * @param target               target object
     * @param superClassSearchDeep search super class deep, is little then 0, then will until Object.class
     * @param params               input params
     * @return invoke result
     */
    synchronized public Object invokeStaticMethod(String methodName, Object target, int superClassSearchDeep, Object... params) {
        Object result = null;
        if (staticMethods.containsKey(methodName)) {
            result = getObject(methodName, target, result, staticMethods, params);
        } else {
            if (superClassSearchDeep != 0) {
                if (innerClass.equals(Object.class)) {
                    return null;
                }
                if (fatherObjectReflector == null) {
                    loadSuperObjectReflector(true);
                }
                result = fatherObjectReflector.invokeStaticMethod(methodName, target, superClassSearchDeep - 1, params);
            }
        }

        return result;
    }

    /**
     * is the super class loaded, if the inner class is objectClass then return true;
     *
     * @return true/false
     */
    public boolean isLoadSuperClass() {
        if (innerClass.equals(Object.class)) {
            return true;
        }
        return fatherObjectReflector != null;
    }

    /**
     * get all can be read property
     *
     * @return readable property set
     */
    public Set<String> getReadableProperty() {
        return readableProperty.keySet();
    }

    /**
     * get all can be wrote property
     *
     * @return writable property set
     */
    public Set<String> getWritableProperty() {
        return writableProperty.keySet();
    }

    /**
     * get all properties of inner class
     *
     * @return the properties list
     */
    public List<String> getAllProperties() {
        return this.properties;
    }

    /**
     * do instance
     *
     * @return object
     */
    public T getInstance() {
        if (defaultConstructorIndex < 0) {
            return null;
        }

        return constructors[defaultConstructorIndex].invoke(new Object[]{});
    }

    /**
     * do instance
     *
     * @param args init params
     * @return Object of class
     */
    public T getInstance(Object... args) {
        for (AbsConstructor<T> absConstructor : constructors) {
            if (absConstructor.isThisParams(args)) {
                return absConstructor.invoke(args);
            }
        }

        return null;
    }

    /**
     * get package name
     *
     * @return package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * get class name
     *
     * @return class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * getter of innerClass
     *
     * @return inner class
     */
    public Class<T> getInnerClass() {
        return innerClass;
    }

    /**
     * get getter packings
     *
     * @return getter packings' iterator
     */
    public Iterator<GetterMethodInvoker> getterMethodIterator() {
        return readableProperty.values().iterator();
    }

    /**
     * get setter packings
     *
     * @return setter packings' iterator
     */
    public Iterator<SetterMethodInvoker> setterMethodIterator() {
        return writableProperty.values().iterator();
    }

    /**
     * Get common method iterators, overload packings in the same list
     *
     * @return common method's iterator
     */
    public Iterator<List<Invoker>> getCommonMethodsIterator() {
        return commonMethods.values().iterator();
    }

    /**
     * get static method iterators, overload packings in the same list
     *
     * @return static method's iterator
     */
    public Iterator<List<Invoker>> getStaticMethodsIterator() {
        return staticMethods.values().iterator();
    }
}