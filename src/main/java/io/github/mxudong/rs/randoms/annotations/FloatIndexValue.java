package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * limit the float range, it set the index of random float
 *
 * @author Dong
 * @see RandomLimit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FloatIndexValue {
    float indexValue();
}
