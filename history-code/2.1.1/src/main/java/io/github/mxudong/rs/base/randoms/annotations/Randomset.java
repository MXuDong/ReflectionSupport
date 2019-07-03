package io.github.mxudong.rs.base.randoms.annotations;

import java.lang.annotation.*;

/**
 * This annotation indicates that a class can be
 * randomized using customization and contains a
 * large number of default data settings. Include
 * all basic types and string type generation
 * formats, if not written, use the default data
 * generation.
 * <p>
 * If a field has a specific generated comment, the
 * data is overwritten.
 *
 * @author Dong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Randomset {
    // default string format
    String defaultFormat() default "c3n3*3l3u3s3";

    // default chars create in
    String defaultChars() default "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_.";

    //default byte min value
    byte minByteValue() default 0;

    //default byte max value
    byte maxByteValue() default Byte.MAX_VALUE;

    //default short min value
    short minShortValue() default 0;

    //default short max value
    short maxShortValue() default Short.MAX_VALUE;

    //default int min value
    int minIntegerValue() default 0;

    //default int max value
    int maxIntegerValue() default Integer.MAX_VALUE;

    //default long min value
    long minLongValue() default 0;

    //default long max value
    long maxLongValue() default Long.MAX_VALUE;

    //default float index
    float floatIndex() default 15;

    //default double index
    double doubleIndex() default 15;

}
