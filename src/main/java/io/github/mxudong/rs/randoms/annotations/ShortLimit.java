package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;


/**
 * Restricting Short's Generation Interval
 * <p>
 * so it only affects Short type data, and it has no impact on any other type of data.
 * <p>
 * and priority lower than Short Value but high then RandomLimit
 *
 * @author Dong
 * @see RandomLimit
 * @see ShortValue
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShortLimit {

    short minValue() default Short.MIN_VALUE;

    short maxValue() default Short.MAX_VALUE;
}
