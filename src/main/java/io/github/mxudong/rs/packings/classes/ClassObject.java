package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.exceptions.ReflectionException;
import io.github.mxudong.rs.packings.AnnotationAble;
import io.github.mxudong.rs.packings.fields.CommonField;
import io.github.mxudong.rs.packings.methods.*;
import io.github.mxudong.rs.utils.ClassUtil;
import io.github.mxudong.rs.utils.MethodUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * this class packing the class, and for this class, you
 * only create from ClassObjectFactor
 *
 * @author Dong
 * @since 3.0
 */

public class ClassObject<T> implements AnnotationAble {

    /**
     * the method truly info
     */
    private Invoker[] invokers;

    /**
     * all the common methods index, and the key is method's name
     */
    private Map<String, List<Integer>> commonMethods;
    /**
     * all the static methods index, and the key is method's name
     * <p>
     * and if some method is static and at same time it is a
     * getter method or setter method, it will be marked as getter
     * or setter method
     */
    private Map<String, List<Integer>> staticMethods;
    /**
     * all the setter method index, and the key is method's name
     */
    private Map<String, List<Integer>> setterMethods;
    /**
     * all the getter method index, and the key is method's name
     */
    private Map<String, List<Integer>> getterMethods;

    /**
     * all the Field
     */
    private CommonField[] allFields;

    /**
     * the object type
     *
     * @see ObjectType
     */
    private ObjectType objectType;

    /**
     * constructors of packing class
     *
     * @see ConstructMethod
     */
    private ConstructMethod<T>[] constructMethods;
    /**
     * default constructor of packing class
     *
     * @see ConstructMethod
     */
    private int defaultConstructorMethod = -1;

    /**
     * packing class
     */
    private Class<T> packingClass;

    /**
     * the object interfaces
     */
    private ClassObject<?>[] interfaces;

    /**
     * super class object of packing class, will it null, it
     * is {@code Object}
     */
    private ClassObject<?> superClassObject;

    /**
     * annotations
     */
    private AnnotationObject[] annotationObjects;

    /**
     * construction method
     *
     * @param c packing class
     */
    protected ClassObject(@NotNull Class<T> c) {
        this.packingClass = c;
        superClassObject = ObjectFactory.getInstance().getClassObject(c.getSuperclass());
    }

    /**
     * build ClassObject
     */
    public void build() {
    }

    /**
     * build class object type
     */
    private void buildObjectType() {
        if (this.packingClass.isInterface()) {
            objectType = ObjectType.INTERFACE;
        } else if (this.packingClass.isEnum()) {
            objectType = ObjectType.ENUM;
        } else {
            objectType = ObjectType.CLASS;
        }
    }

    /**
     * build the method of this packing class
     */
    private void buildMethod() {
        this.staticMethods = new HashMap<>();
        this.getterMethods = new HashMap<>();
        this.setterMethods = new HashMap<>();
        this.commonMethods = new HashMap<>();

        Method[] methods = this.packingClass.getDeclaredMethods();
        this.invokers = new Invoker[methods.length];

        for (int i = 0; i < methods.length; i++) {

            Invoker invoker;

            if (MethodUtil.isGetterMethod(methods[i])) {
                invoker = new GetterMethod(methods[i], this);
                if (!this.getterMethods.containsKey(invoker.getMethodName())) {
                    this.getterMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.getterMethods.get(invoker.getMethodName()).add(i);
            } else if (MethodUtil.isSetterMethod(methods[i])) {
                invoker = new SetterMethod(methods[i], this);
                if (!this.setterMethods.containsKey(invoker.getMethodName())) {
                    this.setterMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.setterMethods.get(invoker.getMethodName()).add(i);
            } else if (MethodUtil.isStaticMethod(methods[i])) {
                invoker = new StaticMethod(methods[i], this);
                if (!this.staticMethods.containsKey(invoker.getMethodName())) {
                    this.staticMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.staticMethods.get(invoker.getMethodName()).add(i);
            } else {
                invoker = new CommonMethod(methods[i], this);
                if (!this.commonMethods.containsKey(invoker.getMethodName())) {
                    this.commonMethods.put(invoker.getMethodName(), new ArrayList<>());
                }
                this.commonMethods.get(invoker.getMethodName()).add(i);
            }

            this.invokers[i] = invoker;
        }
    }

    /**
     * build the construction of this class
     */
    private void buildConstructionMethod() {
        Constructor<T>[] constructors = (Constructor<T>[]) this.packingClass.getConstructors();
        this.constructMethods = new ConstructMethod[constructors.length];

        for (int i = 0; i < constructors.length; i++) {
            ConstructMethod<T> constructMethod = new ConstructMethod<>(constructors[i], this);
            if (constructMethod.isDefaultConstruction()) {
                defaultConstructorMethod = i;
            }
            this.constructMethods[i] = constructMethod;
        }
    }

    /**
     * build the field of this packing class
     */
    private void buildField() {
        Field[] fields = this.packingClass.getDeclaredFields();
        this.allFields = new CommonField[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.allFields[i] = new CommonField(fields[i], this);
        }
    }

    /**
     * build the interface of this packing class
     */
    private void buildInterface() {
        Class<?>[] interfaces = this.packingClass.getInterfaces();
        this.interfaces = new ClassObject[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            this.interfaces[i] = ObjectFactory.getInstance().getClassObject(interfaces[i]);
        }
    }

    /**
     * build the annotation of this packing class
     */
    private void buildAnnotation() {
        Annotation[] annotations = this.packingClass.getDeclaredAnnotations();
        this.annotationObjects = new AnnotationObject[annotations.length];
        for (int i = 0; i < annotations.length; i++) {
            this.annotationObjects[i] = new AnnotationObject(annotations[i]);
        }
    }

    @Override
    public AnnotationObject getAnnotation(Class annotationClass) {
        for (AnnotationObject annotationObject : this.annotationObjects) {
            if (annotationObject.getAnnotationClass().getPackingClass().equals(annotationClass)) {
                return annotationObject;
            }
        }
        return null;
    }

    @Override
    public List<AnnotationObject> getAllAnnotation() {
        return new ArrayList<>(Arrays.asList(this.annotationObjects));
    }

    @Override
    public int getAnnotationCount() {
        return this.annotationObjects.length;
    }

    /**
     * is has default constructor method
     *
     * @return if has return true, else return false
     */
    public boolean hasDefaultConstructorMethod() {
        return this.defaultConstructorMethod != -1;
    }

    /**
     * get the construction array
     *
     * @return construction array
     */
    public ConstructMethod<T>[] getConstructMethods() {
        return Arrays.copyOf(this.constructMethods, this.constructMethods.length);
    }

    /**
     * get the packing classes' methods
     *
     * @return packing classes' methods
     * @see Invoker
     */
    public Invoker[] getMethods() {
        return Arrays.copyOf(this.invokers, this.invokers.length);
    }

    /**
     * get the all methods
     *
     * @return all methods
     */
    public ArrayList<Invoker> getAllMethods() {
        ArrayList<Invoker> invokers = new ArrayList<>();
        if (this.superClassObject != null) {
            invokers.addAll(superClassObject.getAllMethods());
        }
        invokers.addAll(Arrays.asList(getMethods()));
        return invokers;
    }

    /**
     * get count of method
     *
     * @return the count of method
     */
    public int getMethodCount() {
        return this.invokers.length;
    }

    /**
     * get the method invoker
     *
     * @param methodName aim method's name
     * @param params     the method declared params
     * @return if exits return it, else not in this packing,
     * will find from superClassObject, until null, if not exits, return null
     */
    public Invoker getMethodInvoker(String methodName, Object... params) {
        for (Invoker invoker : this.invokers) {
            if (invoker.getMethodName().equals(methodName)) {
                if (invoker.isParamsIsThisMethod(params)) {
                    return invoker;
                }
            }
        }

        if (superClassObject == null) {
            return null;
        }

        return superClassObject.getMethodInvoker(methodName, params);
    }

    /**
     * get some filed's getter method
     *
     * @param getterName be get name
     * @return getterMethods or if not exits return null
     */
    public GetterMethod[] getGetterMethod(String getterName) {
        if (this.getterMethods.containsKey(getterName)) {
            List<Integer> indexes = this.getterMethods.get(getterName);
            GetterMethod[] getterMethods = new GetterMethod[indexes.size()];
            for (int i = 0; i < indexes.size(); i++) {
                getterMethods[i] = (GetterMethod) invokers[indexes.get(i)];
            }

            return getterMethods;
        }

        return null;
    }

    /**
     * get some field's setter method
     *
     * @param setterName be get name
     * @return setterMethods or if not exits return null
     */
    public SetterMethod[] getSetterMethod(String setterName) {
        if (this.setterMethods.containsKey(setterName)) {
            List<Integer> indexes = this.setterMethods.get(setterName);
            SetterMethod[] setterMethods = new SetterMethod[indexes.size()];
            for (int i = 0; i < indexes.size(); i++) {
                setterMethods[i] = (SetterMethod) invokers[indexes.get(i)];
            }
            return setterMethods;
        }

        return null;
    }

    /**
     * get the construction array length
     *
     * @return construction array length
     */
    public int getConstructionMethodCount() {
        return this.constructMethods.length;
    }

    /**
     * invoke the method which name is methodName and params is args.class
     *
     * @param methodName method name
     * @param target     aim object
     * @param args       aim args
     * @return invoke result
     */
    public Object invokeMethod(String methodName, Object target, Object... args) {
        Invoker invoker = this.getMethodInvoker(methodName, args);
        if (invoker == null) {

            if (superClassObject != null) {
                return superClassObject.invokeMethod(methodName, target, args);
            } else {
                try {
                    throw new ReflectionException("ClassObject", "invokeMethod", "can't find method with name" +
                            "[" + methodName + "] or param wrong");
                } catch (ReflectionException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return invoker.invoke(target, args);
    }

    /**
     * this method will invoke getter method
     * <p>
     * the setter method is all the method which start with get
     *
     * @param methodName the method name
     * @param target     aim object
     * @param args       aim value
     * @return the result of getter
     */
    public Object invokeGetterMethod(String methodName, Object target, Object... args) {
        if (this.getterMethods.containsKey(methodName)) {
            for (int i : this.getterMethods.get(methodName)) {
                if (this.invokers[i].isParamsIsThisMethod(args)) {
                    return this.invokers[i].invoke(target, args);
                }
            }
        } else {
            if (superClassObject != null) {
                return superClassObject.invokeGetterMethod(methodName, target, args);
            }
        }

        try {
            throw new ReflectionException("ClassObject", "invokeGetterMethod", "can't find getter method");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * invoke setter method
     *
     * @param methodName method name
     * @param target     aim obejct
     * @param args       aim value
     * @return invoke result
     */
    public Object invokeSetterMethod(String methodName, Object target, Object... args) {
        if (this.setterMethods.containsKey(methodName)) {
            for (int i : this.setterMethods.get(methodName)) {
                if (this.invokers[i].isParamsIsThisMethod(args)) {
                    return this.invokers[i].invoke(target, args);
                }
            }
        } else {
            if (superClassObject != null) {
                return superClassObject.invokeGetterMethod(methodName, target, args);
            }
        }

        try {
            throw new ReflectionException("ClassObject", "invokeGetterMethod", "can't find setter method");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * invoke static method
     *
     * @param methodName method name
     * @param target     aim object
     * @param args       aim params
     * @return invoke result
     */
    public Object invokeStaticMethod(String methodName, Object target, Object... args) {
        if (this.staticMethods.containsKey(methodName)) {
            for (int i : this.staticMethods.get(methodName)) {
                if (this.invokers[i].isParamsIsThisMethod(args)) {
                    return this.invokers[i].invoke(target, args);
                }
            }
        } else {
            if (superClassObject != null) {
                return superClassObject.invokeGetterMethod(methodName, target, args);
            }
        }

        try {
            throw new ReflectionException("ClassObject", "invokeGetterMethod", "can't find static method");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * get the newInstance of this packing class,
     * but if has no default construction, it will return null
     *
     * @return new instance
     */
    public T getNewInstance() {
        if (hasDefaultConstructorMethod()) {
            return this.constructMethods[defaultConstructorMethod].newInstance();
        } else {
            try {
                throw new ReflectionException("ClassObject", "getNewInstance()", "has no default construction");
            } catch (ReflectionException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    /**
     * get the newInstance of this packing class,
     * but if can't match construction of params, it will return null
     *
     * @param params aim params
     * @return new instance
     */
    public T getNewInstance(Object... params) {
        for (ConstructMethod<T> constructMethod : this.constructMethods) {
            if (constructMethod.isParamsIsThisMethod(params)) {
                return constructMethod.newInstance(params);
            }
        }

        try {
            throw new ReflectionException("ClassObject", "getNewInstance()", "can find param match construction");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create an array of class
     *
     * @param length the array length
     * @return an new array
     */
    public T[] getArray(int length) {
        if (length < 0) {
            return null;
        }
        return (T[]) Array.newInstance(this.packingClass, length);
    }

    /**
     * return the packing class
     *
     * @return the packing class
     */
    public Class<T> getPackingClass() {
        return packingClass;
    }

    /**
     * getter method of superClassObject;
     *
     * @return super class object
     */
    public ClassObject<?> getSuperClassObject() {
        return superClassObject;
    }

    /**
     * return packing class' name
     *
     * @return class' name
     */
    public String getPackingClassName() {
        return this.packingClass.getName();
    }

    /**
     * is class public
     *
     * @return if class is public return true, else return false
     */
    public boolean isPublic() {
        return Modifier.isPublic(packingClass.getModifiers());
    }

    /**
     * is class static
     *
     * @return if class is static return true, else return false
     */
    public boolean isStatic() {
        return Modifier.isStatic(packingClass.getModifiers());
    }

    /**
     * get the object type
     *
     * @return object type
     */
    public ObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * get common field
     *
     * @param fieldName the field name
     * @return common field if not exit will find it in super class object until null
     * if not exits in super class will return null
     */
    public CommonField getField(String fieldName) {
        for (CommonField commonField : this.allFields) {
            if (commonField.getFieldName().equals(fieldName)) {
                return commonField;
            }
        }

        if (superClassObject != null) {
            return superClassObject.getField(fieldName);
        }

        try {
            throw new ReflectionException("ClassObject", "getField", "can't find field name [" + fieldName + "]");
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * get the field value of target
     *
     * @param fieldName field name
     * @param target    target object
     * @return field value
     */
    public Object getFieldValue(String fieldName, Object target) {
        return getFieldValue(fieldName, target, (Object[]) null);
    }

    /**
     * get the field value of target
     *
     * @param fieldName field name
     * @param target    target object
     * @param args      required params
     * @return field value
     */
    public Object getFieldValue(String fieldName, Object target, Object... args) {
        CommonField commonField = this.getField(fieldName);
        if (commonField == null) {
            if (superClassObject != null) {
                return superClassObject.getFieldValue(fieldName, target, args);
            }
            return null;
        }

        return commonField.getValue(target, args);
    }

    /**
     * set the field value of target
     *
     * @param fieldName field name
     * @param target    target object
     * @param args      bet set value
     * @return invoke result, an some time, it cloud null
     */
    public Object setFieldValue(String fieldName, Object target, Object... args) {
        CommonField commonField = this.getField(fieldName);
        if (commonField == null) {
            if (superClassObject != null) {
                return superClassObject.setFieldValue(fieldName, target, args);
            }
            return null;
        }
        return commonField.setValue(target, args);
    }

    /**
     * get the packing class declared fields
     *
     * @return packing class declared fields
     */
    public CommonField[] getFields() {
        return Arrays.copyOf(this.allFields, this.allFields.length);
    }

    /**
     * get the all fields
     *
     * @return all fields
     */
    public ArrayList<CommonField> getAllFields() {
        ArrayList<CommonField> commonFields = new ArrayList<>();
        if (this.superClassObject != null) {
            commonFields.addAll(superClassObject.getAllFields());
        }
        commonFields.addAll(Arrays.asList(this.allFields));
        return commonFields;
    }

    /**
     * get the interface count
     *
     * @return get the interface count
     */
    public int getInterfacesCount() {
        return this.interfaces.length;
    }

    @Override
    public String toString() {
        return this.packingClass.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClassObject) {
            return this.packingClass.equals(obj);
        }
        return false;
    }
}
