package io.github.mxudong.rs.packings.fields;

import io.github.mxudong.rs.exceptions.ReflectionException;
import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.methods.GetterMethod;
import io.github.mxudong.rs.packings.methods.SetterMethod;

import java.lang.reflect.Field;

/**
 * the CommonField packing the Field in class, and
 * provides method to manage Field
 * <p>
 * for every field, will check setterMethod and check getterMethod
 *
 * @author Dong
 * @since 3.0
 */

public class CommonField {

    /**
     * the packing field
     */
    private Field packingField;
    /**
     * the packing field type
     */
    private Class<?> packingFieldType;
    /**
     * the packing field name
     */
    private String packingFieldName;

    /**
     * the class which has packing field
     */
    private ClassObject<?> classObject;

    /**
     * the getter method of this field
     */
    private GetterMethod[] fieldGetterMethod;

    /**
     * the setter method of this field
     */
    private SetterMethod[] fieldSetterMethod;


    /**
     * construction method, in this method, filed info will be save
     *
     * @param packingField packing field
     * @param classObject  the class which packing field from
     */
    public CommonField(Field packingField, ClassObject<?> classObject) {
        this.packingField = packingField;
        this.classObject = classObject;
        this.packingFieldName = packingField.getName();
        this.packingFieldType = packingField.getType();
        this.fieldSetterMethod = classObject.getSetterMethod(getSetterMethodName());
        this.fieldGetterMethod = classObject.getGetterMethod(getGetterMethodName());
    }

    /**
     * get the field getter method name
     *
     * @return getter method name
     */
    public String getGetterMethodName() {
        if (packingFieldType.equals(boolean.class) || packingFieldType.equals(Boolean.class)) {
            return addPreFix("is", packingFieldName);
        } else {
            return addPreFix("get", packingFieldName);
        }
    }

    /**
     * get the field setter method name
     *
     * @return setter method name
     */
    public String getSetterMethodName() {
        return addPreFix("set", packingFieldName);
    }

    /**
     * get field value, in fact, it invoke getter method,
     * if you want to get direct please use
     * {@code getValueDirect(Object target)}
     *
     * @param target aim object
     * @return value of field
     * @see GetterMethod
     */
    public Object getValue(Object target) {
        return getValue(target);
    }

    /**
     * get field value, in fact, it invoke getter method,
     * if you want to get direct please use
     * {@code getValueDirect(Object target)}
     *
     * @param target aim object
     * @param params invoke getter method required params
     * @return value of field
     * @see GetterMethod
     */
    public Object getValue(Object target, Object... params) {
        for (GetterMethod getterMethod : this.fieldGetterMethod) {
            if (getterMethod.isParamsIsThisMethod(params)) {
                return getterMethod.invoke(target, params);
            }
        }

        try {
            throw new ReflectionException("CommonField", "getValue", "the params can't convert to getter method params");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get field value, but get direct, if you want to invoker getter
     * method, please use {@code getValue(Object target, Object... param)}
     * or {@code getValue(Object target)}
     *
     * @param target aim object
     * @return value of field
     */
    public Object getValueDirect(Object target) {
        try {
            return packingField.get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set field value, in fact, it invoke setter method,
     * if you want to set direct please use
     * {@code setValueDirect(Object target, Object value)}
     *
     * @param target aim object which you want to invoke
     * @param value  you expect value
     * @see SetterMethod
     */
    public void setValue(Object target, Object... value) {
        for (SetterMethod setterMethod : this.fieldSetterMethod) {
            if (setterMethod.isParamsIsThisMethod(value)) {
                setterMethod.invoke(target, value);
                break;
            }
        }
    }

    /**
     * set field value, but set direct, if you want to invoke
     * setter method, please use {@code setValue(Object target, Object value)}
     *
     * @param target aim object
     * @param value  new value
     */
    public void setValueDirect(Object target, Object value) {
        if (packingFieldType.isInstance(value)) {
            try {
                packingField.set(target, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new ReflectionException("CommonField", "setValueDirect", "the aim value can't convert field type");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add prefix for packingFieldName, if first char is little char, it will
     * be upper
     *
     * @param preFix           be add prefix
     * @param packingFieldName aim packingFieldName
     * @return add prefix and packingFieldName
     */
    private static String addPreFix(String preFix, String packingFieldName) {
        StringBuilder result = new StringBuilder(preFix);

        char[] name = packingFieldName.toCharArray();
        if (name[0] >= 'a' && name[0] <= 'z') {
            name[0] = (char) (name[0] - 'a' + 'A');
        }

        return result.append(name).toString();
    }
}
