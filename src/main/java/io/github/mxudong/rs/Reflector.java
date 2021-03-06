package io.github.mxudong.rs;

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import io.github.mxudong.rs.packings.fields.CommonField;
import io.github.mxudong.rs.packings.methods.Invoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * it has packing the object operation of reflection
 *
 * @author Dong
 * @since 3.0
 */
public class Reflector<T> {

    /**
     * packing the classObject
     *
     * @see ClassObject
     */
    private ClassObject<T> classObject;

    /**
     * packing the object
     */
    private T packingObject;

    /**
     * the object factory
     *
     * @see ObjectFactory
     */
    ObjectFactory objectFactory = ObjectFactory.getInstance();

    /**
     * the construction method, and it can receive the object
     *
     * @param object target object which will be packed
     */
    public Reflector(T object) {
        classObject = (ClassObject<T>) objectFactory.getClassObject(object.getClass());
        this.packingObject = object;
    }

    /**
     * the construction method, and it can receive the object's class,
     * and the aim class must have the default construction, if not,
     * it will throw some exception {@link ClassObject#getNewInstance()}
     * <p>
     * so by this construction, will create a new object from tClass
     *
     * @param tClass target class of packing object
     */
    public Reflector(Class<T> tClass) {
        this.classObject = (ClassObject<T>) objectFactory.getClassObject(tClass);
        this.packingObject = getNewInstance();
    }

    /**
     * it like {@link #Reflector(Class)}, but it can receive ClassObject
     *
     * @param tReflector aim ClassObject
     */
    public Reflector(ClassObject<T> tReflector) {
        this.classObject = tReflector;
        this.packingObject = getNewInstance();
    }

    /**
     * get the classObject
     *
     * @return ClassObject
     */
    public ClassObject<T> getClassObject() {
        return classObject;
    }

    /**
     * get the inner object
     *
     * @return inner object
     */
    public T getInnerObject() {
        return packingObject;
    }


    /**
     * get the class name
     *
     * @return class name
     */
    public String getClassName() {
        return this.classObject.getPackingClassName();
    }

    /**
     * turn the object to map
     *
     * @param canBeNull if true, the params will be not append which is null
     * @return the map
     */
    public Map<String, Object> turnToMap(boolean canBeNull) {
        ArrayList<CommonField> commonFields = classObject.getAllFields();
        Map<String, Object> map = new HashMap<>(commonFields.size());
        for (CommonField commonField : commonFields) {
            Object result = commonField.getValue(this.packingObject);
            if (result == null) {
                if (canBeNull) {
                    map.put(commonField.getFieldName(), null);
                }
                continue;
            }
            map.put(commonField.getFieldName(), result);
        }
        return map;
    }

    /**
     * set the object from infos
     *
     * @param infos be setter infos
     */
    public void setFromMap(Map<String, Object> infos) {
        for (String fieldName : infos.keySet()) {
            Object params = infos.get(fieldName);

            CommonField commonField = this.classObject.getField(fieldName);
            if (commonField != null && !commonField.isFinal()) {
                commonField.setValue(this.packingObject, params);
            }
        }
    }

    /**
     * get the new instance
     *
     * @return new instance
     */
    public T getNewInstance() {
        return this.classObject.getNewInstance();
    }


    /**
     * get the new instance
     *
     * @param args init object params
     * @return new instance
     */
    public T getNewInstance(Object... args) {
        return this.classObject.getNewInstance(args);
    }

    /**
     * get the new array
     *
     * @param length the new array length
     * @return new array
     */
    public T[] getNewArray(int length) {
        return this.classObject.getArray(length);
    }

    /**
     * invoke the setter method
     *
     * @param fieldName field name
     * @param args      new values
     */
    public void setFieldValue(String fieldName, Object... args) {
        this.classObject.setFieldValue(fieldName, this.packingObject, args);
    }

    /**
     * invoke the getter method
     *
     * @param fieldName field name
     * @return the field value
     */
    public Object getFieldValue(String fieldName) {
        return this.classObject.getFieldValue(fieldName, this.packingObject);
    }

    /**
     * invoke method
     *
     * @param methodName method name
     * @param args       method required params
     * @return result of invoke
     */
    public Object invokeMethod(String methodName, Object... args) {
        Invoker invoker = this.classObject.getMethod(methodName, args);
        if (invoker == null) {
            return null;
        }

        return invoker.invoke(this, packingObject, args);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.classObject.getPackingClass().isInstance(obj)) {
            return this.packingObject.equals(obj);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reflector : " + this.packingObject.toString();
    }
}
