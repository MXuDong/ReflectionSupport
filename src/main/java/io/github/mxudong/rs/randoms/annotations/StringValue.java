package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated string data value
 *
 * @author Dong
 * @see RandomLimit
 * @see StringFormatValue
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringValue {
    String value();
}
