package io.github.mxudong.rs;

import io.github.mxudong.rs.bean.BoyStudent;
import io.github.mxudong.rs.bean.Man;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import io.github.mxudong.rs.packings.methods.Invoker;
import org.junit.Test;

import java.util.Arrays;

public class ReflectorTest {

    @Test
    public void test1(){
        Man man = new Man();
        Reflector<Man> manReflector = new Reflector<>(man);
        System.out.println(Arrays.toString(manReflector.getClassObject().getMethods()));
        System.out.println(manReflector.turnToMap(true));
        man.setAge(15);
        System.out.println(manReflector.turnToMap(true));
    }

    @Test
    public void test2(){
        BoyStudent boyStudent = new BoyStudent();
        boyStudent.setGrade("123");
        boyStudent.setAge(15);
        boyStudent.setName("zhaobenshan");
        boyStudent.setNumber("12343515");

        Reflector<BoyStudent> reflector = new Reflector<>(boyStudent);
        System.out.println(reflector.getInnerObject());
        System.out.println(reflector.turnToMap(true));
        for(Invoker invoker : reflector.getClassObject().getAllMethods()){
            System.out.println(invoker);
        }
        System.out.println(reflector.getClassObject().getAllFields());
        System.out.println(reflector.getClassObject().getObjectType().getType());
        System.out.println(reflector.getClassObject().getPackingClassName());
        System.out.println(ObjectFactory.getInstance().getClassObject());
        System.out.println(reflector.getClassObject().getInterfacesCount());
    }

}