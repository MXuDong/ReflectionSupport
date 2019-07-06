package io.github.mxudong.rs.exceptions;

/**
 * this exception will show how wrong you did.
 * <p>
 * this exception is the base exception.
 * <p>
 * the exception format is : [info] [in [className].[methodName]]
 *
 * @author Dong
 * @since 3.0
 */

public class ReflectionException extends Exception {

    public ReflectionException(String className, String methodName, String info) {
        super("[" + info + "]" +
                "[in [" + className + "].["
                + methodName + "]]");
    }
}
