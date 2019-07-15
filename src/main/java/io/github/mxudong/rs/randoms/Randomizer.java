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
                return createRandomLong(field);
            case "java.lang.Character":
            case "char":
                return createRandomChar(field);
            case "java.lang.Double":
            case "double":
                return createRandomDouble(field);
            case "java.lang.Float":
            case "float":
                return createRandomFloat(field);
            case "java.lang.Boolean":
            case "boolean":
                return createRandomBoolean(field);
            case "java.lang.String":
                return createRandomString(field);
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

    /**
     * like {@link Randomizer#createRandomInt(CommonField)}, the method is same as that,
     * only the type change from {@code int} to {@code long}
     *
     * @param field target field
     * @return long of random
     * @see LongLimit
     * @see LongValue
     * @see RandomLimit
     */
    private long createRandomLong(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(LongValue.class);
        if (annotationObject != null) {
            return (long) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(LongLimit.class);
        if (annotationObject != null) {
            return BaseRandom.getRandomLong((long) annotationObject.getInfo("minValue"),
                    (long) annotationObject.getInfo("maxValue"));
        }

        return BaseRandom.getRandomLong(this.defaultLongMinValue, this.defaultLongMaxValue);
    }

    /**
     * create random char, if the target field declared CharValue, will return CharValue.value,
     * if not and the target field declared CharLimit, will create from CharLimit.
     * <p>
     * if not, it will create from default chars
     *
     * @param field target field
     * @return char of random
     * @see RandomLimit
     * @see CharLimit
     * @see CharValue
     */
    private char createRandomChar(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(CharValue.class);
        if (annotationObject != null) {
            return (char) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(CharLimit.class);
        String createFrom = this.defaultChars;
        if (annotationObject != null) {
            createFrom = (String) annotationObject.getInfo("chars");
        }

        return BaseRandom.getRandomChar(createFrom);
    }

    /**
     * create the random double, and check the annotation of DoubleValue and DoubleIndexValue
     *
     * @param field target field
     * @return double of random
     * @see DoubleIndexValue
     * @see DoubleValue
     * @see RandomLimit
     */
    private double createRandomDouble(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(DoubleValue.class);
        if (annotationObject != null) {
            return (double) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(DoubleIndexValue.class);
        double index = this.defaultDoubleIndex;
        if (annotationObject != null) {
            index = (double) annotationObject.getInfo("indexValue");
        }
        return BaseRandom.getRandomDouble(index);
    }

    /**
     * create the random float, and check the annotation of floatValue and floatIndexValue
     *
     * @param field target field
     * @return float of random
     * @see FloatIndexValue
     * @see FloatValue
     * @see RandomLimit
     */
    private double createRandomFloat(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(FloatValue.class);
        if (annotationObject != null) {
            return (float) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(FloatIndexValue.class);
        float index = this.defaultFloatIndex;
        if (annotationObject != null) {
            index = (float) annotationObject.getInfo("indexValue");
        }
        return BaseRandom.getRandomFloat(index);
    }

    /**
     * create the random String, and check the annotation of:StringValue, StringFormatValue
     *
     * @param field target field
     * @return String
     * @see StringFormatValue
     * @see StringValue
     * @see RandomLimit
     */
    private String createRandomString(CommonField field) {
        AnnotationObject annotationObject = field.getAnnotation(StringValue.class);
        if (annotationObject != null) {
            return (String) annotationObject.getInfo("value");
        }

        annotationObject = field.getAnnotation(StringFormatValue.class);
        String format = this.defaultStringFormat;
        if (annotationObject != null) {
            format = (String) annotationObject.getInfo("value");
        }
        return RandomFormatString.createRandomStringBase(format);
    }
}
