package io.github.mxudong.rs.packings.classes;

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
     * construction method
     * @param c packing class
     */
    protected ClassObject(Class<T> c){

    }

    /**
     * return the packing class
     *
     * @return the packing class
     */
    public Class<T> getPackingClass() {
        return packingClass;
    }
}
