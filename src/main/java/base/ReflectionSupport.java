package base;

import base.filters.FieldNameFilter;
import base.filters.MapObjectTurnFilter;
import base.filters.MethodNameFilter;
import exceptions.ReflectionException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private static final String MAP_TURN_TO_OBJECT = "ReflectionSupport.mapTurnToObject()";
    private static final String OBJECT_TURN_TO_MAP = "ReflectionSupport.objectTurnToMap";
    private static final String SET_PROPERTY = "setProperty";

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
            if (!canRepeat && arrayList.contains(m.getName())) {
                continue;
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
     *                        一次类推; -2:所有父类）
     * @param fieldNameFilter 字段过滤器
     * @param canRepeat       是否允许重复出现
     * @return 字段列表
     * @since 1.0
     */
    public static List<String> getFieldList(Object o, int superClassDeep, FieldNameFilter fieldNameFilter, boolean canRepeat) {
        if (o == null) {
            return new ArrayList<>();
        }
        if (fieldNameFilter == null) {
            fieldNameFilter = new FieldNameFilter() {
            };
        }
        //如果搜索深度小于0则不做处理
        if (superClassDeep < -1) {
            return new ArrayList<>();
        }
        Class c = o.getClass();
        List<String> result = new ArrayList<>();

        while (c != null && ((superClassDeep >= 0) || superClassDeep == -2)) {
            if (superClassDeep != -2) {
                superClassDeep--;
            }
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

    /**
     * 执行设值操作
     *
     * @param setMethodName 被执行的参数方法
     * @param o             被设置的对象
     * @param value         设置的值
     * @return 操作后的对象
     * @throws ReflectionException       方法操作异常
     * @throws NoSuchMethodException     没有找到方法
     * @throws InvocationTargetException 反射处理异常
     * @throws IllegalAccessException    非法权限
     */
    public static Object setProperty(String setMethodName, Object o, Object value) throws ReflectionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the Object is null.", SET_PROPERTY);
        }

        if (getMethodList(o, null, false).contains(setMethodName)) {
            o.getClass().getMethod(setMethodName).invoke(value);
        }

        return o;
    }

    /**
     * @param o                   传入的Object, 不能为空
     * @param params              需要填写的参数
     * @param mapObjectTurnFilter 过滤器，决定是否会被注入
     * @return 被注入的Object类型
     * @throws ReflectionException 参数异常
     */
    public static Object mapTurnToObject(Object o, Map<String, Object> params, MapObjectTurnFilter mapObjectTurnFilter) throws ReflectionException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the Object is null.", MAP_TURN_TO_OBJECT);
        }

        for(String key : params.keySet()){
            if(mapObjectTurnFilter.isDoFilter(key)){

            }
        }
    }


    /**
     * @param o                   传入的Object， 不能为空
     * @param mapObjectTurnFilter 过滤器， 决定是否会被转换为Map对象
     * @return 全部的属性键值对
     * @throws ReflectionException 参数异常
     */
    public static Map<String, Object> objectTurnToMap(Object o, MapObjectTurnFilter mapObjectTurnFilter) throws ReflectionException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the object is null", OBJECT_TURN_TO_MAP);
        }
    }
}
