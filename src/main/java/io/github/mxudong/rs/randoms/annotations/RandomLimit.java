package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * set default create data's range of base type value
 *
 * @author Dong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RandomLimit {

    // default string format
    String defaultFormat() default "c3n3*3l3u3s3";

    // default chars create in
    String defaultChars() default "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.";

    //default byte min value
    byte minByteValue() default Byte.MIN_VALUE;

    //default byte max value
    byte maxByteValue() default Byte.MAX_VALUE;

    //default short min value
    short minShortValue() default Short.MIN_VALUE;

    //default short max value
    short maxShortValue() default Short.MAX_VALUE;

    //default int min value
    int minIntegerValue() default Integer.MIN_VALUE;

    //default int max value
    int maxIntegerValue() default Integer.MAX_VALUE;

    //default long min value
    long minLongValue() default Long.MIN_VALUE;

    //default long max value
    long maxLongValue() default Long.MAX_VALUE;

    //default float index
    float floatIndex() default 15;

    //default double index
    double doubleIndex() default 15;
}
