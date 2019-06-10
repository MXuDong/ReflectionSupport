package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.methods.AbsConstructor;

import java.lang.reflect.Constructor;
import java.util.List;
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
     * we call this object the <tt>target object</tt>.
     */
    private T object;
    /**
     * the class of <tt>target object</tt>
     */
    private Class tClass;

    /**
     * <tt>target object</tt>'s default construction method
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
     * @param object An instance of the <tt>target object</tt>
     */
    public Reflector(T object) {
        this.object = object;
        this.tClass = object.getClass();
        this.objectReflector = ReflectorFactory.getInstance().getObjectReflector(this.tClass);
        readablePropertyNames = this.objectReflector.getReadableProperty();
        writablePropertyNames = this.objectReflector.getWritableProperty();
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
     * get back <tt>target object</tt>
     *
     * @return the <tt>target object</tt>
     */
    public T getObject() {
        return object;
    }

    /**
     * get <tt>target object</tt>'s class
     *
     * @return the <tt>target object</tt>'s class
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
    public boolean isPropertyReadable(String propertyName){
       return this.readablePropertyNames.contains(propertyName);
    }

    /**
     * judge a property can be wrote
     * @param propertyName property name
     * @return is can be wrote
     */
    public boolean isPropertyWritable(String propertyName){
        return this.writablePropertyNames.contains(propertyName);
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
     * @return is <tt>target object</tt> equals obj ?
     */
    @Override
    public boolean equals(Object obj) {
        return object.equals(obj);
    }

    /**
     * override the method : {@code toString()}
     *
     * @return <tt>target object</tt>'s to string.
     */
    @Override
    public String toString() {
        return object.toString();
    }
}