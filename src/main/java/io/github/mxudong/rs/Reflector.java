package io.github.mxudong.rs;

//it is writing

import io.github.mxudong.rs.packings.classes.ClassObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import io.github.mxudong.rs.packings.fields.CommonField;

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
}
