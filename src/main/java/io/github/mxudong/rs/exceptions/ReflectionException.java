package io.github.mxudong.rs.exceptions;

/**
 * @author Dong
 * @since 1.0
 * <p>
 * Class Name : ReflectionException
 * Create Time : 21:21
 * Create Date : 2019/5/9
 * Project : rs
 * <p>
 * ReflectionException
 */

public class ReflectionException extends Exception {
    public ReflectionException(String msg, String field, String methodName) {
        super(msg + "|" + "method:" + methodName + ":" + "[" + field + "]");
    }
}