package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated char data value
 * <p>
 * so it only affects char type data, and it has no impact on any other type of data.
 * <p>
 * priority is higher than Random Limit and char Limit
 *
 * @author Dong
 * @see RandomLimit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CharValue {
    char value();
}
