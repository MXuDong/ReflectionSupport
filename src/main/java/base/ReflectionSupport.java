package base;

import exceptions.ReflectionException;

/**
 * @author Dong
 * <p>
 * Class Name : ReflectionSupport
 * Create Time : 20:42
 * Create Date : 2019/5/9
 * Project : ReflectionSupport
 */

public class ReflectionSupport {

    private static final String METHOD_REMOVE_SET_OR_GET_PREFIX = "ReflectionSupport.removeSetOrGetPrefix()";
    private static final String METHOD_ADD_SET_OR_GET_PREFIX = "ReflectionSupport.addSetOrGetPrefix()";

    /**
     * 提取方法名中的参数名
     * <p>
     * setName方法将返回name
     *
     * @param methodName 方法名
     * @return 提取后的参数名
     * @throws ReflectionException 数据格式异常
     */
    public static String removeSetOrGetPrefix(String methodName) throws ReflectionException {
        int baseSize = 3;

        if (methodName == null) {
            throw new ReflectionException("Method name can't be null!", "method is null", METHOD_REMOVE_SET_OR_GET_PREFIX);
        }

        boolean isStartWithSetOrGet = methodName.startsWith("get") || methodName.startsWith("set");

        if (methodName.length() <= baseSize) {
            throw new ReflectionException("Method name must bigger than 3",
                    methodName + " length is : " + methodName.length(),
                    METHOD_REMOVE_SET_OR_GET_PREFIX);
        }
        if (!isStartWithSetOrGet) {
            throw new ReflectionException("Method name must start with 'set' or 'get'",
                    methodName + " is not start with 'set' or 'get'",
                    METHOD_REMOVE_SET_OR_GET_PREFIX);
        }

        char[] letters = methodName.toCharArray();
        if (letters[3] >= 'A' && letters[3] <= 'Z') {
            letters[3] = (char) (letters[3] - 'A' + 'a');
        }
        StringBuilder result = new StringBuilder();
        for (int i = 3; i < letters.length; i++) {
            result.append(letters[i]);
        }

        return result.toString();
    }

    /**
     * 参数名生成setter或getter方法
     * <p>
     * name, true将放回setName
     * name, false将返回getName
     *
     * @param propertyName 参数名
     * @param isSet        是否为set，如果不是 则为get
     * @return 生成后的方法名
     * @throws ReflectionException 数据格式异常
     */
    public static String addSetOrGetPrefix(String propertyName, boolean isSet) throws ReflectionException {
        if (propertyName == null || propertyName.length() == 0) {
            throw new ReflectionException("Property name con't be null or length equals 0",
                    "property name is can't be turn to method name.",
                    METHOD_ADD_SET_OR_GET_PREFIX);
        }
        char[] arrays = propertyName.toCharArray();
        if (arrays[0] >= 'a' && arrays[0] <= 'z') {
            arrays[0] = (char) (arrays[0] - 'a' + 'A');
        }
        StringBuilder stringBuilder = new StringBuilder((isSet ? "set" : "get"));
        for (char c : arrays) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
