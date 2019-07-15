package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * limit the double range, it set the index of random double
 *
 * @author Dong
 * @see RandomLimit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DoubleIndexValue {
    double indexValue();
}
