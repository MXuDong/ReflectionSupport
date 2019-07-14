package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.Reflector;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.fields.CommonField;
import io.github.mxudong.rs.randoms.annotations.RandomLimit;
import io.github.mxudong.rs.utils.StringUtil;

import java.util.ArrayList;

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

    public T getInnerObject() {
        return this.packingObjectReflector.getInnerObject();
    }

    /**
     * random inner object
     *
     * @param doDeep can the super class field be random
     */
    public void doRandom(boolean doDeep) {
        doRandom(this.packingObjectReflector.getInnerObject(), doDeep);
    }

    /**
     * random the object
     *
     * @param object target object
     * @param doDeep can the super class field be random
     */
    private void doRandom(T object, boolean doDeep) {
        ArrayList<CommonField> commonFields = this.packingObjectReflector.getClassObject().getAllFields();

        //===============should check annotation, will be writing
        for (CommonField commonField : commonFields) {
            if (commonField.canSet()) {
                this.packingObjectReflector.setFieldValue(commonField.getFieldName(),
                        createRandomObject(commonField.getFieldType().getName()));
            }
        }
    }

    /**
     * check the annotation of packing object
     */
    private void checkAnnotation() {
        AnnotationObject annotationObject = this.packingObjectReflector.getClassObject().getAnnotation(RandomLimit.class);
        if (annotationObject != null) {
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

    /**
     * create simple object of base type
     *
     * @param values    random limit
     * @param paramType class name
     * @return random type
     */
    private Object createRandomObject(String paramType, Object... values) {
        switch (paramType) {
            case "java.lang.Byte":
            case "byte":
                return BaseRandom.getRandomByte((byte) (values == null ? defaultByteMinValue : values[0]), (byte) (values == null ? defaultByteMaxValue : values[1]));
            case "java.lang.Short":
            case "short":
                return BaseRandom.getRandomShort((short) (values == null ? defaultShortMinValue : values[0]), (short) (values == null ? defaultShortMinValue : values[1]));
            case "java.lang.Integer":
            case "int":
                return BaseRandom.getRandomInt((int) (values == null ? defaultIntMinValue : values[0]), (int) (values == null ? defaultIntMaxValue : values[1]));
            case "java.lang.Long":
            case "long":
                return BaseRandom.getRandomLong((long) (values == null ? defaultLongMinValue : values[0]), (long) (values == null ? defaultLongMaxValue : values[1]));
            case "java.lang.Character":
            case "char":
                return BaseRandom.getRandomChar((String) (values == null ? defaultChars : values[0]));
            case "java.lang.Double":
            case "double":
                return BaseRandom.getRandomDouble((double) (values == null ? defaultDoubleIndex : values[0]));
            case "java.lang.Float":
            case "float":
                return BaseRandom.getRandomFloat((float) (values == null ? defaultFloatIndex : values[0]));
            case "java.lang.Boolean":
            case "boolean":
                return BaseRandom.getRandomBoolean();
            case "java.lang.String":
                return RandomFormatString.createRandomStringBase((String) (values == null ? defaultStringFormat : values[0]));
            default:
                return null;
        }
    }
}
