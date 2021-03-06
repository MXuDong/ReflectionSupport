package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * set the string format
 *
 * @author Dong
 * @see RandomLimit
 * @see StringValue
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringFormatValue {
    String value();
}
