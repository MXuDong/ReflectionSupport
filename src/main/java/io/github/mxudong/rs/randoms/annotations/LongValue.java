package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated long data value
 * <p>
 * so it only affects long type data, and it has no impact on any other type of data.
 * <p>
 * priority is higher than Random Limit and long Limit
 *
 * @author Dong
 * @see RandomLimit
 * @see LongLimit
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LongValue {

    long value();
}
