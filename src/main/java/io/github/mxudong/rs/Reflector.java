package io.github.mxudong.rs;

//it is writing

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;

/**
 * it has packing the object operation of reflection
 *
 * @author Dong
 * @since 3.0
 */
public class Reflector<T> {

    /**
     * packing the classObject
     *
     * @see ClassObject
     */
    private ClassObject<T> classObject;

    /**
     * packing the object
     */
    private T object;

    /**
     * the object factory
     *
     * @see ObjectFactory
     */
    ObjectFactory objectFactory = ObjectFactory.getInstance();

    public Reflector(T object) {
        classObject = (ClassObject<T>) objectFactory.getClassObject(object.getClass());
        this.object = object;
    }

    /**
     * get the classObject
     *
     * @return ClassObject
     */
    public ClassObject<T> getClassObject() {
        return classObject;
    }

    /**
     * get the inner object
     *
     * @return inner object
     */
    public T getInnerObject() {
        return object;
    }
}
