package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.methods.*;
import io.github.mxudong.rs.base.strings.StringExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String packageName;
    private String className;

    private int defaultConstructorIndex = -1;
    private AbsConstructor[] constructors;

    private Map<String, GetterMethodInvoker> readableProperty;
    private Map<String, SetterMethodInvoker> writableProperty;

    private Map<String, List<Invoker>> staticMethods;
    private Map<String, List<Invoker>> commonMethods;

    private List<String> properties;

    /**
     * construction method
     *
     * @param c innerClass
     */
    protected ObjectReflector(Class c) {
        this.innerClass = c;

        //write base information of class:c
        String[] packages = c.getName().split("\\.");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < packages.length - 1; i++) {
            stringBuffer.append(packages[i]);
        }
        this.packageName = stringBuffer.toString();
        this.className = packages[packages.length - 1];


        //================================Choose construction
        Constructor[] constructors = c.getConstructors();
        this.constructors = new AbsConstructor[constructors.length];
        for (int i = 0; i < constructors.length; i++) {
            this.constructors[i] = new AbsConstructor(constructors[i]);
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

        //================================Create methods list
        readableProperty = new HashMap<>();
        writableProperty = new HashMap<>();
        staticMethods = new HashMap<>();
        commonMethods = new HashMap<>();

        for (Method m : c.getMethods()) {
            String name = m.getName();
            if (MethodInvoker.isGetterMethod(name)) {
                name = StringExtension.getGetterMethodProperty(name);
                readableProperty.put(name, new GetterMethodInvoker(m));
            } else if (MethodInvoker.isSetterMethod(name)) {
                name = StringExtension.getSetterMethodProperty(name);
                writableProperty.put(name, new SetterMethodInvoker(m));
            } else if (MethodInvoker.isStaticMethod(m)) {
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
     * do instance
     *
     * @return object
     */
    public Object getInstance() {
        if (defaultConstructorIndex < 0) {
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
<<<<<<< HEAD
        for(AbsConstructor absConstructor : constructors){
            if(absConstructor.isThisParams(args)){
=======
        for (AbsConstructor absConstructor : constructors) {
            if (absConstructor.isThisParams(args)) {
>>>>>>> test
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
    public Class getInnerClass() {
        return innerClass;
    }
}