package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.bean.Man;
import io.github.mxudong.rs.bean.People;
import io.github.mxudong.rs.packings.fields.CommonField;
import io.github.mxudong.rs.packings.methods.ConstructMethod;
import io.github.mxudong.rs.packings.methods.Invoker;
import org.junit.Test;

import java.util.Arrays;

public class ClassObjectTest {

    @Test
    public void test1() {
        ClassObject peopleClassObject;
        peopleClassObject = ObjectFactory.getInstance().getClassObject(People.class);
        ClassObject temp = peopleClassObject;
        while (temp != null) {
            System.out.println(temp.getPackingClass().getName());
            temp = temp.getSuperClassObject();
        }

        System.out.println(peopleClassObject.getPackingClass());
    }

    @Test
    public void test2() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        System.out.println(classObject.getNewInstance());
        System.out.println(classObject.getNewInstance(15));
        System.out.println(classObject.getNewInstance("testBug"));
    }

    @Test
    public void test3() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        System.out.println(classObject.getConstructionMethodCount());
        for (ConstructMethod<?> constructMethod : classObject.getConstructMethods()) {
            System.out.println(constructMethod);
        }
    }

    @Test
    public void test4() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        Invoker[] invokers = classObject.getAllmethods();
        for (Invoker invoker : invokers) {
            System.out.println(invoker);
        }
    }

    @Test
    public void test5() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        Invoker invoker = classObject.getMethodInvoker("toString");
        People people = new People();
        System.out.println(invoker);
        System.out.println(invoker.invoke(people));
        System.out.println(invoker.getMethodType());
        System.out.println(invoker.getMethodName());
        System.out.println(invoker.getBelongClass());
        System.out.println(invoker.getReturnType());
        System.out.println(Arrays.toString(invoker.getMethodParamsType()));
        System.out.println(classObject.equals(ObjectFactory.getInstance().getClassObject(People.class)));
    }

    @Test
    public void test6() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        System.out.println(Arrays.toString(classObject.getGetterMethod("getAge")));
        System.out.println(Arrays.toString(classObject.getGetterMethod("getA")));
        System.out.println(Arrays.toString(classObject.getSetterMethod("setAge")));
        System.out.println(Arrays.toString(classObject.getSetterMethod("setAg")));
        System.out.println(Arrays.toString(classObject.getSetterMethod(null)));
    }

    @Test
    public void test7() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        People people = new People();
        CommonField commonField = classObject.getField("age");
        System.out.println(commonField.getFieldName());
        commonField.setValue(people, 13);
        System.out.println(commonField.getValue(people));
        commonField.setValueDirect(people, 13);
        System.out.println(commonField.getValueDirect(people));
        System.out.println(commonField.getValue(people));
    }

    @Test
    public void test8() {
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        People people = new People();
        System.out.println("================================");
        CommonField commonField = classObject.getField("number");
        System.out.println(commonField.getFieldName());
        commonField.setValue(people, "test");
        System.out.println(commonField.getValue(people));
        commonField.setValueDirect(people, "test2");
        System.out.println(commonField.getValueDirect(people));
        System.out.println(commonField.getValue(people));
    }

    @Test
    public void test9(){
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(People.class);
        People people = new People();
        CommonField commonField = classObject.getField("number");
        System.out.println(commonField);
    }

    @Test
    public void test10(){
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(Man.class);
        CommonField commonField = classObject.getField("SEX");
        System.out.println(commonField);
    }

    @Test
    public void test11(){
        ClassObject<?> classObject = ObjectFactory.getInstance().getClassObject(Man.class);
        Man man = new Man();
        CommonField commonField = classObject.getField("SEX");
        System.out.println(classObject.invokeMethod("getSEX", man));
    }
}
