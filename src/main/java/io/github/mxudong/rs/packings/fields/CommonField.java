package io.github.mxudong.rs.packings.fields;

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
     * the class which has packing field
     */
    private ClassObject<?> classObject;

    /**
     * the getter method of this field
     */
    private GetterMethod fieldGetterMethod;

    /**
     * the setter method of this field
     */
    private SetterMethod fieldSetterMethod;

    /**
     * construction method, in this method, filed info will be save
     *
     * @param packingField packing field
     * @param classObject  the class which packing field from
     */
    public CommonField(Field packingField, ClassObject<?> classObject) {
        this.packingField = packingField;
        this.classObject = classObject;
    }
}
