package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated boolean data value
 *
 * @author Dong
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BooleanValue {
    boolean value();
}
