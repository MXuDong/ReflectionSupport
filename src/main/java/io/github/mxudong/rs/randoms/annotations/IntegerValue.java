package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated Integer data value
 * <p>
 * so it only affects Integer type data, and it has no impact on any other type of data.
 * <p>
 * priority is higher than Random Limit and Integer Limit
 *
 * @author Dong
 * @see IntegerLimit
 * @see RandomLimit
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IntegerValue {
    int value();
}
