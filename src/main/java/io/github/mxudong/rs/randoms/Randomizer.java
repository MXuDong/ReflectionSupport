package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.Reflector;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.fields.CommonField;
import io.github.mxudong.rs.randoms.annotations.*;
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

        for (CommonField commonField : commonFields) {
            if (commonField.canSet()) {
                commonField.setValue(this.getInnerObject(), createRandomObject(commonField));
            }
        }
    }

    /**
     * check the annotation of packing object, main from RandomLimit annotations
     *
     * @see RandomLimit
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
     * @param field target field name
     * @return random type
     */
    private Object createRandomObject(CommonField field) {
        String paramType = field.getFieldType().getName();
        switch (paramType) {
            case "java.lang.Byte":
            case "byte":
                return createRandomByte(field);
            case "java.lang.Short":
            case "short":
                return createRandomShort(field);
            case "java.lang.Integer":
            case "int":
                return createRandomInt(field);
            case "java.lang.Long":
            case "long":
                return BaseRandom.getRandomLong((long) (values == null || values.length == 0 ? defaultLongMinValue : values[0]), (long) (values == null || values.length == 0 ? defaultLongMaxValue : values[1]));
            case "java.lang.Character":
            case "char":
                return BaseRandom.getRandomChar((String) (values == null || values.length == 0 ? defaultChars : values[0]));
            case "java.lang.Double":
            case "double":
                return BaseRandom.getRandomDouble((double) (values == null || values.length == 0 ? defaultDoubleIndex : values[0]));
            case "java.lang.Float":
            case "float":
                return BaseRandom.getRandomFloat((float) (values == null || values.length == 0 ? defaultFloatIndex : values[0]));
            case "java.lang.Boolean":
            case "boolean":
                return createRandomBoolean(field);
            case "java.lang.String":
                return RandomFormatString.createRandomStringBase((String) (values == null || values.length == 0 ? defaultStringFormat : values[0]));
            default:
                return null;
        }
    }

    /**
     * get the boolean value, if the field declared BooleanValue, it will use value
     * of BooleanValue
     *
     * @param field target field
     * @return boolean of random
     */
    private boolean createRandomBoolean(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(BooleanValue.class);
        if (annotationObject != null) {
            return (boolean) annotationObject.getInfo("value");
        }

        return BaseRandom.getRandomBoolean();
    }

    /**
     * like {@link Randomizer#createRandomInt(CommonField)}, the method is same as that,
     * only the type change from {@code int} to {@code byte}
     *
     * @param field target field
     * @return byte of random
     * @see ByteLimit
     * @see ByteValue
     * @see RandomLimit
     */
    private byte createRandomByte(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(ByteValue.class);
        if (annotationObject != null) {
            return (byte) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(ByteLimit.class);
        if (annotationObject != null) {
            return BaseRandom.getRandomByte((byte) annotationObject.getInfo("minValue"),
                    (Byte) annotationObject.getInfo("maxValue"));
        }

        return BaseRandom.getRandomByte(this.defaultByteMinValue, this.defaultByteMaxValue);
    }

    /**
     * like {@link Randomizer#createRandomInt(CommonField)}, the method is same as that,
     * only the type change from {@code int} to {@code short}
     *
     * @param field target field
     * @return short of random
     * @see ShortLimit
     * @see ShortValue
     * @see RandomLimit
     */
    private short createRandomShort(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(ShortValue.class);
        if (annotationObject != null) {
            return (short) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(ShortLimit.class);
        if (annotationObject != null) {
            return BaseRandom.getRandomShort((short) annotationObject.getInfo("minValue"),
                    (short) annotationObject.getInfo("maxValue"));
        }

        return BaseRandom.getRandomShort(this.defaultShortMinValue, this.defaultShortMaxValue);
    }

    /**
     * this methdo will check annotation of target field, and
     * if it use annotation will check IntegerValue first, if not
     * exits, will check IntegerValue.
     * <p>
     * if both of IntegerLimit and IntegerValue are not exits, it will create from class
     * annotation : RandomLimit, if it not exits, it will use default values.
     * <p>
     * the default value create by RandomLimit, and it is equals
     *
     * @param field target field
     * @return int of random
     * @see IntegerLimit
     * @see IntegerValue
     * @see RandomLimit
     */
    private int createRandomInt(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(IntegerValue.class);
        if (annotationObject != null) {
            return (int) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(IntegerLimit.class);
        if (annotationObject != null) {
            return BaseRandom.getRandomInt((int) annotationObject.getInfo("minValue"),
                    (int) annotationObject.getInfo("maxValue"));
        }

        return BaseRandom.getRandomInt(this.defaultIntMinValue, this.defaultIntMaxValue);
    }
}
