package io.github.mxudong.rs.packings.classes;

import io.github.mxudong.rs.bean.People;
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
    }
}
