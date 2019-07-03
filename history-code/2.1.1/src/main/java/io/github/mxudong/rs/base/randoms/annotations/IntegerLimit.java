package io.github.mxudong.rs.base.randoms.annotations;

import java.lang.annotation.*;

/**
 * Restrict the generation of integer types
 *
 * @author Dong
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IntegerLimit {

    /**
     * set min integer value
     */
    int minIntegerValue();

    /**
     * set max integer value
     */
    int maxIntegerValue();
}
