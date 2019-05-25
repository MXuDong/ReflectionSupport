package io.github.mxudong.rs.base;

import io.github.mxudong.rs.base.filters.FieldNameFilter;
import io.github.mxudong.rs.base.filters.MapObjectTurnFilter;
import io.github.mxudong.rs.base.filters.MethodNameFilter;
import io.github.mxudong.rs.exceptions.ReflectionException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dong
 * @since 1.0
 * <p>
 * Class Name : rs
 * Create Time : 20:42
 * Create Date : 2019/5/9
 * Project : rs
 */

public class ReflectionSupport {

    private static final String METHOD_REMOVE_SET_OR_GET_PREFIX = "rs.removeSetOrGetPrefix()";
    private static final String METHOD_ADD_SET_OR_GET_PREFIX = "rs.addSetOrGetPrefix()";
    private static final String MAP_TURN_TO_OBJECT = "rs.mapTurnToObject()";
    private static final String OBJECT_TURN_TO_MAP = "rs.objectTurnToMap()";
    private static final String SET_PROPERTY = "doMethod()";

    /**
     * Extracting parameter names from method names
     * <p>
     * 'setName' will return 'name'
     *
     * @param methodName be extracted method name
     * @return param name
     * @throws ReflectionException Data Format Exception
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
     * Setter or getter method for parameter name generation
     * <p>
     * name, true will return setName
     * name, false will reutrn getName
     *
     * @param propertyName param's name
     * @param isSet        is it create 'set' method,if not will be 'get'
     * @return the setter of getter method's name from param's name
     * @throws ReflectionException Data format Exception
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
     * get one class all methods' name
     *
     * @param o                be extracted class
     * @param methodNameFilter method name filter
     * @param canRepeat        is can repeat
     * @return a list of methods' name
     * @see MethodNameFilter
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
     * get properties' name from one class
     *
     * @param o               be extracted class
     * @param superClassDeep  The deep of the extracting
     *                        (start 0, less than 0 returns empty, 1 looks up for a class (this class + direct parent class).
     *                        2. Look up for two parent classes (this class + direct parent + direct parent).
     *                        One-time analogy; - 2: All parent classes)
     * @param fieldNameFilter Field name filter
     * @param canRepeat       is can be repeat
     * @return file's name list
     * @see FieldNameFilter
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
        if (superClassDeep < -1 && superClassDeep != -2) {
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
     * do a method of a class
     *
     * @param methodName be invoked method
     * @param o          be invoked with class
     * @param value      the method's param
     * @return the method return
     * @throws ReflectionException method operation exception
     */
    public static Object doMethod(String methodName, Object o, Object... value) throws ReflectionException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the Object is null.", SET_PROPERTY);
        }

        Object param = null;

        try {

            Method[] methods = o.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    if (value == null) {
                        param = m.invoke(o);
                    } else {
                        param = m.invoke(o, value);
                    }
                    break;
                }
            }
        } catch (InvocationTargetException e) {
            throw new ReflectionException("Set property field.", "Value illegal", MAP_TURN_TO_OBJECT);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Illegal access exception", "please make method is public", MAP_TURN_TO_OBJECT);
        }

        return param;
    }

    /**
     * set a class value from a map
     *
     * @param o                   be set class
     * @param params              the value's map
     * @param mapObjectTurnFilter the map object turn filter
     * @param <T>                 The aim object type
     * @return o, be set class
     * @throws ReflectionException param error
     */
    public static <T> T mapTurnToObject(T o, Map<String, Object> params, MapObjectTurnFilter mapObjectTurnFilter) throws ReflectionException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the Object is null.", MAP_TURN_TO_OBJECT);
        }

        //=================================
        if (mapObjectTurnFilter == null) {
            mapObjectTurnFilter = new MapObjectTurnFilter() {
            };
        }

        for (String key : params.keySet()) {
            if (mapObjectTurnFilter.isDoFilter(key)) {
                Object values = params.get(key);
                doMethod(addSetOrGetPrefix(key, true), o, values);
            }
        }

        return o;
    }


    /**
     * get one class's all value of property
     *
     * @param o                   the class for get values
     * @param mapObjectTurnFilter the map object turn filter
     * @param canBeNull           null filter, true:not ignore, false ignore(false：not marked)
     * @return all the value from the object
     * @throws ReflectionException param error
     */
    public static Map<String, Object> objectTurnToMap(Object o, MapObjectTurnFilter mapObjectTurnFilter, boolean canBeNull) throws ReflectionException {
        if (o == null) {
            throw new ReflectionException("Object can't be null.", "the object is null", OBJECT_TURN_TO_MAP);
        }

        //===========================
        if (mapObjectTurnFilter == null) {
            mapObjectTurnFilter = new MapObjectTurnFilter() {
            };
        }

        //===========================
        Map<String, Object> result = new HashMap<>(getFieldList(o, -1, null, false).size());

        Class clazz = o.getClass();
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            String methodName = m.getName();
            if (methodName.startsWith("get")) {
                if (mapObjectTurnFilter.isDoFilter(methodName)) {
                    Object param = doMethod(methodName, o, null);
                    if (param == null) {
                        if (canBeNull) {
                            result.put(removeSetOrGetPrefix(methodName), param);
                        }
                    } else {
                        result.put(removeSetOrGetPrefix(methodName), param);
                    }
                }
            }
        }

        return result;
    }
    
    public static String getClassName(Object o, int flag){
        return getClassName(o.getClass(), flag);
    }

    public static String getClassName(Class clazz, int flag){

    }
}
