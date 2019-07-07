package io.github.mxudong.rs.packings.classes;

import java.util.Map;
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
     * get the ClassObject, if it is annotation will
     * return null
     * <p>
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

        if (c.isAnnotation() || c.isEnum() || c.isInterface()) {
            return null;
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
}
