package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * Restricting Long's Generation Interval
 * <p>
 * so it only affects Long type data, and it has no impact on any other type of data.
 * <p>
 * and priority lower than Long Value but high then RandomLimit
 *
 * @author Dong
 * @see RandomLimit
 * @see LongValue
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LongLimit {

    long minValue() default Long.MIN_VALUE;

    long maxValue() default Long.MAX_VALUE;
}
