package io.github.mxudong.rs.packings.classes;

/**
 * this class is the ClassObject creator, you can think that the {@code ClassFactory}
 * is the ClassObject's construction.
 * <p>
 * this class also save the info of the class object, so you can quick get the class object
 * second.
 * <p>
 * the ClassFactory is singleton, please keep the singleton feature.
 * <p>
 * if you want to load a class, all the super class from this will be load too. Until object or
 * head, if the super class is loaded, it will stop.
 *
 * @author Dong
 * @since 3.0
 */

public class ClassFactory {
    /**
     * the ClassFactory's instance
     */
    private static ClassFactory INSTANCE;

    /**
     * the {@code ClassFactory} is singleton, so
     * the construction is private
     */
    private ClassFactory() {
    }

    /**
     * get ClassFactory instance
     *
     * @return ClassFactory instance
     */
    public static ClassFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ClassFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClassFactory();
                }
            }
        }

        return INSTANCE;
    }
}
