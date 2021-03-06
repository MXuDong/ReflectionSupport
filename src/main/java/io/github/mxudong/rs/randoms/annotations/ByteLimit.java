package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * Restricting Byte's Generation Interval
 * <p>
 * so it only affects Byte type data, and it has no impact on any other type of data.
 * <p>
 * and priority lower than ByteValue but high then RandomLimit
 *
 * @author Dong
 * @see RandomLimit
 * @see ByteValue
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ByteLimit {

    byte minValue() default Byte.MIN_VALUE;

    byte maxValue() default Byte.MAX_VALUE;
}
