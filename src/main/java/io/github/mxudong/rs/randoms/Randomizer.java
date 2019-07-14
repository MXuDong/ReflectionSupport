package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.Reflector;

/**
 * the randomizer is for random any object,
 * you can use this create an object in random.
 *
 * @author Dong
 * @since 3.0
 */

public class Randomizer<T> {

    /**
     * packing the object in reflector
     */
    private Reflector<T> packingObjectReflector;

    /**
     * construct method
     *
     * @param object target object
     */
    public Randomizer(T object) {
        packingObjectReflector = new Reflector<>(object);
    }

    /**
     * construct method
     *
     * @param tClass target object's classes
     */
    public Randomizer(Class<T> tClass) {
        packingObjectReflector = new Reflector<>(tClass);
    }
}
