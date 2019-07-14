package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.Reflector;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.randoms.annotations.RandomLimit;
import io.github.mxudong.rs.utils.StringUtil;

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

    // default values

    private byte defaultByteMaxValue = Byte.MAX_VALUE;
    private byte defaultByteMinValue = Byte.MIN_VALUE;
    private short defaultShortMaxValue = Short.MAX_VALUE;
    private short defaultShortMinValue = Short.MIN_VALUE;
    private int defaultIntMaxValue = Integer.MAX_VALUE;
    private int defaultIntMinValue = Integer.MIN_VALUE;
    private long defaultLongMaxValue = Long.MAX_VALUE;
    private long defaultLongMinValue = Long.MIN_VALUE;
    private double defaultDoubleMaxValue = 15;
    private float defaultFloatMaxValue = 15;
    private Boolean defaultBooleanValue = null;
    private String defaultChars = StringUtil.getClassSupportLetters();
    private String defaultStringFormat = "[_]n3l3u3s3";

    /**
     * construct method
     *
     * @param object target object
     */
    public Randomizer(T object) {
        packingObjectReflector = new Reflector<>(object);
        checkAnnotation();
    }

    /**
     * construct method
     *
     * @param tClass target object's classes
     */
    public Randomizer(Class<T> tClass) {
        packingObjectReflector = new Reflector<>(tClass);
        checkAnnotation();
    }

    /**
     * check the annotation of packing object
     */
    private void checkAnnotation() {
        AnnotationObject annotationObject = this.packingObjectReflector.getClassObject().getAnnotation(RandomLimit.class);
        System.out.println(this.packingObjectReflector.getClassObject().getAnnotationCount());
        System.out.println(this.packingObjectReflector.getClassObject().getAllAnnotation());
        System.out.println(annotationObject.turnToMap());
    }

}
