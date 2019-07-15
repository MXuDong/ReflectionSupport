package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * specify the generated double data value
 *
 * @author Dong
 * @see DoubleIndexValue
 * @see RandomLimit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DoubleValue {
    double value();
}
