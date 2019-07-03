package io.github.mxudong.rs.exceptions;

/**
 * Class Name : NullParamException
 * Create Time : 11:07
 * Create Date : 2019/6/1
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.0
 * <p>
 * if method needs param is null, will throw this Exception
 * <p>
 * exception info format common : method [method] need param[param], but it is null
 * exception info format default : one of method[{unknown}] needs param is null
 * exception info format only method : one of method [method] need param is null
 */

public class NullParamException extends NullPointerException {

    public NullParamException() {
        this("{unknown}");
    }

    public NullParamException(String methodName) {
        super("one of method [" + methodName + "] need param is null.");
    }

    public NullParamException(String methodName, String paramName) {
        super("method [" + methodName + "]need param [" + paramName + "], but is is null.");
    }
}
