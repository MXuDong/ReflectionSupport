package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * Restricting Integer's Generation Interval
 * <p>
 * so it only affects Integer type data, and it has no impact on any other type of data.
 * <p>
 * and priority lower than IntegerValue but high then RandomLimit
 *
 * @author Dong
 * @see RandomLimit
 * @see IntegerValue
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegerLimit {
    int minValue() default Integer.MIN_VALUE;

    int maxValue() default Integer.MAX_VALUE;
}
