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
    private double defaultDoubleIndex = 15;
    private float defaultFloatIndex = 15;
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
        if(annotationObject != null){
            this.defaultChars = (String) annotationObject.getInfo("defaultChars");
            this.defaultStringFormat = (String) annotationObject.getInfo("defaultFormat");
            this.defaultByteMaxValue = (byte) annotationObject.getInfo("maxByteValue");
            this.defaultByteMinValue = (byte) annotationObject.getInfo("minByteValue");
            this.defaultShortMaxValue = (short) annotationObject.getInfo("maxShortValue");
            this.defaultShortMinValue = (short) annotationObject.getInfo("minShortValue");
            this.defaultIntMaxValue = (int) annotationObject.getInfo("maxIntegerValue");
            this.defaultIntMinValue = (int) annotationObject.getInfo("minIntegerValue");
            this.defaultLongMaxValue = (long) annotationObject.getInfo("maxLongValue");
            this.defaultLongMinValue = (long) annotationObject.getInfo("minLongValue");
            this.defaultDoubleIndex = (double) annotationObject.getInfo("doubleIndex");
            this.defaultFloatIndex = (float) annotationObject.getInfo("floatIndex");
        }
    }

}
