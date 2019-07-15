package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * to limit integer create over
 *
 * @author Dong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegerLimit {
    int minValue() default Integer.MIN_VALUE;

    int maxValue() default Integer.MAX_VALUE;
}
