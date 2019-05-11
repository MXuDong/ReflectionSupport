package base;

import base.filterInterface.FieldNameFilter;
import base.filterInterface.MethodNameFilter;
import exceptions.ReflectionException;
import sun.util.resources.cldr.ar.CalendarData_ar_YE;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong
 * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
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

    /**
     * 获取一个类的全部方法列表
     *
     * @param o                被获取信息的类
     * @param methodNameFilter 方法名过滤器
     * @return 方法名集合
     * @since 1.0
     */
    public static List<String> getMethodList(Object o, MethodNameFilter methodNameFilter, boolean canRepeat) {
        if (o == null) {
            return new ArrayList<>();
        }
        if (methodNameFilter == null) {
            methodNameFilter = new MethodNameFilter() {
            };
        }
        Class c = o.getClass();
        Method[] methods = c.getMethods();
        List<String> arrayList = new ArrayList<>();
        for (Method m : methods) {
            if (!canRepeat) {
                if (arrayList.contains(m.getName())) {
                    continue;
                }
            }
            if (methodNameFilter.isDoFilter(m)) {
                arrayList.add(m.getName());
            }
        }
        return arrayList;
    }


    /**
     * 获取类的所有属性
     *
     * @param o               被获取信息的类
     * @param superClassDeep  检索深度
     *                        （从0开始，小于0返回空，1向上找一个类（本类 + 直接父类），
     *                        2向上找两个父类（本类 + 直接父类 + 直接父类的直接父类），
     *                        一次类推）
     * @param fieldNameFilter 字段过滤器
     * @param canRepeat       是否允许重复出现
     * @return 字段列表
     * @since 1.0
     */
    public static List<String> getClassFieldList(Object o, int superClassDeep, FieldNameFilter fieldNameFilter, boolean canRepeat) {
        if (o == null) {
            return new ArrayList<>();
        }
        if (fieldNameFilter == null) {
            fieldNameFilter = new FieldNameFilter() {
            };
        }
        //如果搜索深度小于0则不做处理
        if (superClassDeep < 0) {
            return new ArrayList<>();
        }
        Class c = o.getClass();
        List<String> result = new ArrayList<>();

        while (c != null && superClassDeep-- >= 0) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (!canRepeat && result.contains(field.getName())) {
                    continue;
                }
                if (fieldNameFilter.isDoFilter(field)) {
                    result.add(field.getName());
                }
            }
            c = c.getSuperclass();
        }
        return result;
    }
}
