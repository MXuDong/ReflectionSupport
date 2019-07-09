package io.github.mxudong.rs;

//it is writing

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import io.github.mxudong.rs.packings.fields.CommonField;

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

    public Reflector(T object) {
        classObject = (ClassObject<T>) objectFactory.getClassObject(object.getClass());
        this.packingObject = object;
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

    public Map<String, Object> turnToMap(boolean canBeNull) {
        CommonField[] commonFields = classObject.getAllFields();
        Map<String, Object> map = new HashMap<>(commonFields.length);
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
}
