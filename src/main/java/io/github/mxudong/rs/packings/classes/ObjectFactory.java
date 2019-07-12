package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.exceptions.ReflectionException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this class is the ClassObject creator, you can think that the {@code ObjectFactory}
 * is the ClassObject's construction.
 * <p>
 * this class also save the info of the class object, so you can quick get the class object
 * second.
 * <p>
 * the ObjectFactory is singleton, please keep the singleton feature.
 * <p>
 * if you want to load a class, all the super class from this will be load too. Until object or
 * head, if the super class is loaded, it will stop.
 *
 * @author Dong
 * @since 3.0
 */

public class ObjectFactory {
    /**
     * the ObjectFactory's instance
     */
    private static ObjectFactory INSTANCE;

    /**
     * save the info of ClassObject with class' name
     */
    private Map<String, ClassObject<?>> classObjectMap;

    /**
     * the {@code ObjectFactory} is singleton, so
     * the construction is private
     */
    private ObjectFactory() {
        classObjectMap = new ConcurrentHashMap<>();
    }

    /**
     * get ObjectFactory instance
     *
     * @return ObjectFactory instance
     */
    public static ObjectFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ObjectFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ObjectFactory();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * if {@code classObjectMap} has loaded the class' info, will return
     * without new instance, else will get new instance and add to
     * {@code classObjectMap} and return it.
     *
     * @param c aim class
     * @return ClassObject
     */
    public ClassObject<?> getClassObject(Class<?> c) {
        if (c == null) {
            return null;
        }

        if (c.isAnnotation()) {
            return getAnnotationClass(c);
        }

        if (!classObjectMap.containsKey(c.getName())) {
            synchronized (ObjectFactory.class) {
                if (!classObjectMap.containsKey(c.getName())) {
                    ClassObject<?> classObject = new ClassObject<>(c);
                    classObjectMap.put(c.getName(), classObject);
                }
            }
        }
        return classObjectMap.get(c.getName());
    }

    /**
     * get the ClassObject, if it is not annotation will throw exception
     *
     * @param targetClass target annotation class
     * @return the annotation class
     */
    public AnnotationClass getAnnotationClass(Class targetClass) {
        if (targetClass.isAnnotation()) {
            if (!classObjectMap.containsKey(targetClass.getName())) {
                synchronized (ObjectFactory.class) {
                    if (!classObjectMap.containsKey(targetClass.getName())) {
                        AnnotationClass annotationClass = new AnnotationClass(targetClass);
                        classObjectMap.put(targetClass.getName(), annotationClass);
                    }
                }
            }
        } else {
            try {
                throw new ReflectionException("ObjectFactory", "getAnnotationClass",
                        "the target class is not an annotation class, please use getClassObject(Class<?> c)");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        return (AnnotationClass) classObjectMap.get(targetClass.getName());
    }

    /**
     * get the all class object
     *
     * @return all class object
     */
    public ArrayList<ClassObject<?>> getClassObject() {
        return new ArrayList<>(this.classObjectMap.values());
    }

}
