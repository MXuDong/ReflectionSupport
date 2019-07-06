package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.bean.People;
import io.github.mxudong.rs.packings.methods.ConstructMethod;
import org.junit.Test;

public class ClassObjectTest {

    @Test
    public void test1() {
        ClassObject peopleClassObject;
        peopleClassObject = ClassFactory.getInstance().getClassObject(People.class);
        ClassObject temp = peopleClassObject;
        while (temp != null) {
            System.out.println(temp.getPackingClass().getName());
            temp = temp.getSuperClassObject();
        }

        System.out.println(peopleClassObject.getPackingClass());
    }

    @Test
    public void test2(){
        ClassObject<?> classObject = ClassFactory.getInstance().getClassObject(People.class);
        System.out.println(classObject.getNewInstance());
        System.out.println(classObject.getNewInstance(15));
        System.out.println(classObject.getNewInstance("testBug"));
    }

    @Test
    public void test3(){
        ClassObject<?> classObject = ClassFactory.getInstance().getClassObject(People.class);
        System.out.println(classObject.getConstructionMethodCount());
        for(ConstructMethod<?> constructMethod : classObject.getConstructMethods()){
            System.out.println(constructMethod);
        }
    }

    @Test
    public void test4(){
        ClassObject<?> classObject = ClassFactory.getInstance().getClassObject(People.class);
    }
}
