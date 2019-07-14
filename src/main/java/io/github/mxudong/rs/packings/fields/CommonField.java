package io.github.mxudong.rs.packings.fields;

import io.github.mxudong.rs.exceptions.ReflectionException;
import io.github.mxudong.rs.packings.AnnotationAble;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.methods.GetterMethod;
import io.github.mxudong.rs.packings.methods.SetterMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * the CommonField packing the Field in class, and
 * provides method to manage Field
 * <p>
 * for every field, will check setterMethod and check getterMethod
 * <p>
 * and it not sync
 *
 * @author Dong
 * @since 3.0
 */

public class CommonField implements AnnotationAble {

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
     * the packing field type
     */
    private FieldType fieldType;

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
     * the field annotations
     */
    private AnnotationObject [] annotationObjects;


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
        if (Modifier.isFinal(packingField.getModifiers())) {
            this.fieldType = FieldType.FINAL_FIELD;
        } else if (Modifier.isStatic(packingField.getModifiers())) {
            this.fieldType = FieldType.STATIC_FIELD;
        } else {
            this.fieldType = FieldType.COMMON_FIELD;
        }

        Annotation [] annotations = this.packingField.getDeclaredAnnotations();
        annotationObjects = new AnnotationObject[annotations.length];
        for(int i = 0; i < annotations.length; i++){
            annotationObjects[i] = new AnnotationObject(annotations[i]);
        }
    }

    /**
     * get the packing field name
     *
     * @return packing field name
     */
    public String getFieldName() {
        return this.packingFieldName;
    }

    /**
     * get the belong class
     *
     * @return belong class
     */
    public Class getFieldBelongClass() {
        return this.classObject.getPackingClass();
    }

    /**
     * get the belong class object
     *
     * @return belong class object
     */
    public ClassObject getFieldBelgonClass() {
        return this.classObject;
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
     * is field final
     *
     * @return if field is final return true, else return false
     */
    public boolean isFinal() {
        return fieldType.equals(FieldType.FINAL_FIELD);
    }

    /**
     * is field static
     *
     * @return if field is static return true, else return false
     */
    public boolean isStatic() {
        return fieldType.equals(FieldType.STATIC_FIELD);
    }

    /**
     * get field value, in fact, it invoke getter method,
     * if you want to get direct please use
     * {@code getValueDirect(Object target)}
     * <p>
     * if the field has no getter method, it will return null
     *
     * @param target aim object
     * @param params invoke getter method required params
     * @return value of field
     * @see GetterMethod
     */
    public Object getValue(Object target, Object... params) {
        if (this.fieldGetterMethod == null) {
            try {
                throw new ReflectionException("CommonField", "getValue", "the field has no getter method");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }

            return null;
        }
        for (GetterMethod getterMethod : this.fieldGetterMethod) {
            if (getterMethod != null && getterMethod.isParamsIsThisMethod(params)) {
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
        if (!isPublic()) {
            try {
                throw new ReflectionException("CommonField", "getValueDirect", "the field is not public");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return packingField.get(target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * set field value, in fact, it invoke setter method,
     * if you want to set direct please use
     * {@code setValueDirect(Object target, Object value)}
     * <p>
     * if the field has no setter method, it will do nothing
     * <p>
     * if the field is final, will throw new ReflectionException
     *
     * @param target aim object which you want to invoke
     * @param value  you expect value
     * @see SetterMethod
     */
    public Object setValue(Object target, Object... value) {

        if (isFinal()) {
            try {
                throw new ReflectionException("CommonField", "setValue", "the field is final");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        if (this.fieldSetterMethod == null) {
            try {
                throw new ReflectionException("CommonField", "setValue", "the field has no setter method");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
        for (SetterMethod setterMethod : this.fieldSetterMethod) {
            if (setterMethod != null && setterMethod.isParamsIsThisMethod(value)) {
                return setterMethod.invoke(target, value);
            }
        }

        return null;
    }

    /**
     * set field value, but set direct, if you want to invoke
     * setter method, please use {@code setValue(Object target, Object value)}
     * <p>
     * if the field is not public it will throw {@code ReflectionException}, and set value failed
     * <p>
     * if the field is final, will throw new ReflectionException
     *
     * @param target aim object
     * @param value  new value
     */
    public void setValueDirect(Object target, Object value) {
        if (isFinal()) {
            try {
                throw new ReflectionException("CommonField", "setValueDirect", "the field is final");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        if (!isPublic()) {
            try {
                throw new ReflectionException("CommonField", "setValueDirect", "the field is not public");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        } else {
            try {
                packingField.set(target, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * is this field public
     *
     * @return if is public return true, else return false;
     */
    public boolean isPublic() {
        return Modifier.isPublic(this.packingField.getModifiers());
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

    @Override
    public String toString() {
        return this.fieldType.getValue() + " - " + packingFieldName + " - " + classObject.toString();
    }

    @Override
    public int getAnnotationCount() {
        return 0;
    }

    @Override
    public AnnotationObject getAnnotation(Class annotationClass) {
        return null;
    }

    @Override
    public List<AnnotationObject> getAllAnnotation() {
        return null;
    }
}
