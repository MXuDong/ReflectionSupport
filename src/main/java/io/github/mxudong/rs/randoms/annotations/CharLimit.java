package io.github.mxudong.rs.randoms.annotations;

import java.lang.annotation.*;

/**
 * Restricting char's Generation Interval
 * <p>
 * so it only affects char type data, and it has no impact on any other type of data.
 * <p>
 * and priority lower than char Value but high then RandomLimit
 *
 * @author Dong
 * @see CharValue
 * @see RandomLimit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CharLimit {

    String chars();
}
