package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated short data value
 * <p>
 * so it only affects short type data, and it has no impact on any other type of data.
 * <p>
 * priority is higher than Random Limit and short Limit
 *
 * @author Dong
 * @see RandomLimit
 * @see ShortLimit
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShortValue {

    short value();
}
