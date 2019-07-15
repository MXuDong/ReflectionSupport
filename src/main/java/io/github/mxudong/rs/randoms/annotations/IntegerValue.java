package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * @author Dong
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IntegerValue {
    int value();
}
