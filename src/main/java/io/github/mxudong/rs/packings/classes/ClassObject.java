package io.github.mxudong.rs.packings.classes;

import org.jetbrains.annotations.NotNull;

/**
 * this class packing the class, and for this class, you
 * only create from ClassObjectFactor
 *
 * @author Dong
 * @since 3.0
 */

public class ClassObject<T> {

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
}
