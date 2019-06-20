package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.methods.GetterMethodInvoker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * innerClass Name : Reflector
 * Create Time : 7:16
 * Create Date : 2019/5/31
 * Project : ReflectionSupport
 * <p>
 * The {@code Reflector} is a util tools of class.
 * <p>
 * This class needs to specify a target object,
 * you can specify the target by construction method:
 *
 * @param <T> target object class
 * @author Dong
 * @since 2.0
 */

public class Reflector<T> {
    /**
     * this object is the aim of reflector to operation,
     * we call this object the )target object .
     */
    private T object;
    /**
     * the class of )target object
     */
    private Class tClass;

    /**
     * )target object 's default construction method
     * the default construction method is has none param
     * construction method
     */
    private ObjectReflector objectReflector;

    /**
     * Name list of readable property
     */
    private Set<String> readablePropertyNames;

    /**
     * Name list of writable property
     */
    private Set<String> writablePropertyNames;


    /**
     * the construction method
     *
     * @param object An instance of the  target object
     */
    public Reflector(T object) {
        this.object = object;
        this.tClass = object.getClass();
        this.objectReflector = ReflectorFactory.getInstance().getObjectReflector(this.tClass);
        readablePropertyNames = this.objectReflector.getReadableProperty();
        writablePropertyNames = this.objectReflector.getWritableProperty();
    }

    /**
     * the construction method
     * the new object will setter from infos
     *
     * @param tClass aim object's class
     * @param infos  object infos
     */
    public Reflector(Class<T> tClass, Map<String, Object> infos) {
        this(tClass);
        setObjectInfo(infos);
    }

    /**
     * the construction method, auto new object
     *
     * @param tClass aim object's class
     */
    public Reflector(Class<T> tClass) {
        this.tClass = tClass;
        this.objectReflector = ReflectorFactory.getInstance().getObjectReflector(this.tClass);
        this.object = (T) objectReflector.getInstance();
        readablePropertyNames = this.objectReflector.getReadableProperty();
        writablePropertyNames = this.objectReflector.getWritableProperty();
    }

    /**
     * get property value, if this object not exits, return null
     *
     * @param propertyName property's name
     * @return property value
     */
    public Object getProperty(String propertyName) {
        if (readablePropertyNames.contains(propertyName)) {
            return objectReflector.invokeGetterMethod(propertyName, object);
        } else {
            return null;
        }
    }


    /**
     * get object reflector
     *
     * @return object reflector
     */
    public ObjectReflector getObjectReflector() {
        return objectReflector;
    }

    /**
     * get class name
     *
     * @return class name
     */
    public String getObjectName() {
        return objectReflector.getClassName();
    }

    /**
     * get class' package path
     *
     * @return package path
     */
    public String getObjectPackages() {
        return objectReflector.getPackageName();
    }

    /**
     * turn object to map
     * <p>
     * if the property can readable, this property will
     * be write into map.
     * <p>
     * if the property is null, the property also be insert into map.
     * <p>
     * This operation only happens in this class, and parent information will not be added to map.
     * If you want to record parent information, use  getObjectInfoAll()
     *
     * @return map about object info
     */
    public Map<String, Object> getObjectInfo() {

        Map<String, Object> infos = new HashMap<>();

        Set<String> keys = readablePropertyNames;
        for (String key : keys) {
            infos.put(key, objectReflector.invokeGetterMethod(key, object));
        }

        return infos;
    }

    public void setObjectInfo(Map<String, Object> infos) {
        for (String key : infos.keySet()) {
            objectReflector.invokeSetterMethod(key, object, infos.get(key));
        }
    }

    /**
     * get new instance
     *
     * @return T
     */
    public T getNewInstance() {
        return (T) objectReflector.getInstance();
    }

    /**
     * get new instance from args
     *
     * @param args init params
     * @return T
     */
    public T getNewInstance(Object... args) {
        return (T) objectReflector.getInstance(args);
    }

    /**
     * get new instance with Reflector
     *
     * @return contain new instance reflector
     */
    public Reflector<T> getNewReflector() {
        T newInstance = getNewInstance();
        if (newInstance == null) {
            return null;
        }
        return new Reflector<>(newInstance);
    }

    /**
     * get back  target object
     *
     * @return the  target object
     */
    public T getObject() {
        return object;
    }

    /**
     * get  target object 's class
     *
     * @return the  target object 's class
     */
    public Class getObjectClass() {
        return tClass;
    }

    /**
     * judge a property can be read
     *
     * @param propertyName property name
     * @return is can be read
     */
    public boolean isPropertyReadable(String propertyName) {
        return this.readablePropertyNames.contains(propertyName);
    }

    /**
     * judge a property can be wrote
     *
     * @param propertyName property name
     * @return is can be wrote
     */
    public boolean isPropertyWritable(String propertyName) {
        return this.writablePropertyNames.contains(propertyName);
    }

    /**
     * invoke common method from this object
     *
     * @param methodName method's name
     * @param args       params for invoke method
     * @return invoke result
     */
    public Object invokeCommonMethod(String methodName, Object... args) {
        return objectReflector.invokeCommonMethod(methodName, object, args);
    }

    /**
     * invoke static method from this object
     *
     * @param methodName method's name
     * @param args       params for invoke method
     * @return invoke result
     */
    public Object invokeStaticMethod(String methodName, Object... args) {
        return objectReflector.invokeStaticMethod(methodName, args);
    }

    /**
     * override the method : {@code hashCode()}
     *
     * @return the target object's hashCode.
     */
    @Override
    public int hashCode() {
        return object.hashCode();
    }

    /**
     * override the method : {@code equals(Object obj)}
     *
     * @param obj other object
     * @return is  target object  equals obj ?
     */
    @Override
    public boolean equals(Object obj) {
        return object.equals(obj);
    }

    /**
     * override the method : {@code toString()}
     *
     * @return  target object 's to string.
     */
    @Override
    public String toString() {
        return object.toString();
    }
}