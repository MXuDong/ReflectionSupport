package io.github.mxudong.rs.base.methods;

import io.github.mxudong.rs.base.strings.StringExtension;
import io.github.mxudong.rs.base.utils.ClassUtil;
import io.github.mxudong.rs.exceptions.NullParamException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Class Name : MethodInvoker
 * Create Time : 10:19
 * Create Date : 2019/5/31
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since 2.0
 */

public class MethodInvoker implements Invoker {

    /**
     * method body
     */
    protected Method method;

    /**
     * method return type
     */
    protected Class<?> returnType;

    /**
     * construction method
     *
     * @param method the insert method
     */

    private int paramCount;
    private Class[] paramClass;

    public MethodInvoker(Method method) {
        //==================method can't be null
        if (method == null) {
            try {
                throw new NullParamException("MethodInvoker", "method");
            } catch (NullParamException e) {
                e.printStackTrace();
            }
        }
        this.method = method;
        this.returnType = method.getReturnType();
        this.paramCount = method.getParameterCount();
        this.paramClass = method.getParameterTypes();
    }

    @Override
    public Object invoke(Object target, Object ... args) {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public boolean isThisArgs(Object... args) {
        if (args == null) {
            return this.paramCount == 0;
        }else if (args.length != this.paramCount) {
            return false;
        }

        for (int i = 0; i < args.length; i++) {
            if (!paramClass[i].isInstance(args[i])) {
                if (!ClassUtil.isBaseType(paramClass[i])) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * split the method by upper little
     *
     * @param methodName be split method's name
     * @return the split-result
     */
    public static String[] splitMethodName(String methodName) {
        //======================resolve null point
        if (methodName == null) {
            methodName = "";
        }

        int upperLittleCount = StringExtension.StringCount(methodName, StringExtension.UPPER_LITTLE_REGION) + 1;
        int index = 0;
        StringBuffer[] result = new StringBuffer[upperLittleCount];
        if (result.length > 0) {
            result[index] = new StringBuffer();
        }
        for (char c : methodName.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                index++;
                if (index < result.length) {
                    result[index] = new StringBuffer();
                }
            }
            result[index].append(c);
        }
        return StringExtension.toStringArray(result);
    }

    /**
     * judge any method is static
     *
     * @param method be judged method
     * @return is static
     */
    public static boolean isStaticMethod(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }


    /**
     * is this method a getter method
     *
     * @param methodName be judged method's name
     * @return is getter method
     */
    public static boolean isGetterMethod(String methodName) {
        return methodName.startsWith("get") || methodName.startsWith("is");
    }


    /**
     * is a method setter method
     *
     * @param methodName be judged method
     * @return is a setter method
     */
    public static boolean isSetterMethod(String methodName) {
        return methodName.startsWith("set");
    }
}
