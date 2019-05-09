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

    public static String removeSetOrGetPrefix(String methodName) throws ReflectionException {
        int baseSize = 3;

        if (methodName == null) {
            throw new ReflectionException("Method name can't be null!", "method is null");
        }

        boolean isStartWithSetOrGet = methodName.startsWith("get") || methodName.startsWith("set");

        if (methodName.length() <= baseSize) {
            throw new ReflectionException("Method name must bigger than 3",
                    methodName + " length is : " + methodName.length());
        }
        if (!isStartWithSetOrGet) {
            throw new ReflectionException("Method name must start with 'set' or 'get'",
                    methodName + " is not start with 'set' or 'get'");
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
}
