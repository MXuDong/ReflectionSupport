package io.github.mxudong.rs.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * innerClass Name : ReflectorFactory
 * Create Time : 21:58
 * Create Date : 2019/6/7
 * Project : ReflectionSupport
 * <p>
 * This class is a reflection factory class,
 * which provides generation, acquisition and
 * destruction of reflection classes.
 * <p>
 * Provide a simple way to reuse reflection classes, save space.
 * <p>
 * {@code ReflectorFactory} is a singleton pattern.
 * <p>
 * ReflectorFactory.getInstance() can get a reflectorFactory object.
 *
 * @author Dong
 * @see ObjectReflector
 * @since 2.0
 */

public class ReflectorFactory {
    /**
     * single pattern
     */
    private static ReflectorFactory reflectorFactory = new ReflectorFactory();

    /**
     * for the single pattern, so it should be <tt>ConcurrentHashMap</tt>
     * <p>
     * the map is for class and ObjectReflector.
     *
     * @see ObjectReflector
     */
    private Map<Class, ObjectReflector> objectReflectorMap;

    /**
     * construction method
     */
    private ReflectorFactory() {
        objectReflectorMap = new ConcurrentHashMap<>();
    }

    /**
     * for <tt>reflectorFactory</tt> is singleton pattern, you can't use {@code new} to
     * get a example of this class, but you can get instance by this method.
     *
     * @return the <tt>reflectorFactory</tt>'s unique global instance.
     */
    public static ReflectorFactory getInstance() {
        return reflectorFactory;
    }

    /**
     * get the class {@code ObjectReflector} which handle the <tt>c</tt>.
     *
     * @param c aim class, if it is null, the return is null.
     * @return the <tt>ObjectReflector</tt> which handle the <tt>c</tt>.
     */
    public ObjectReflector getObjectReflector(Class c) {
        if (c == null) {
            return null;
        }
        if (objectReflectorMap.containsKey(c)) {
            return objectReflectorMap.get(c);
        }

        ObjectReflector objectReflector = new ObjectReflector(c);
        objectReflectorMap.put(c, objectReflector);
        return objectReflector;
    }
}