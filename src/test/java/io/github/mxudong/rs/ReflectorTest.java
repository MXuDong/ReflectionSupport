package io.github.mxudong.rs;

import io.github.mxudong.rs.annotations.TestAnnotation;
import io.github.mxudong.rs.bean.BoyStudent;
import io.github.mxudong.rs.bean.Man;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import io.github.mxudong.rs.packings.methods.GetterMethod;
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

    @Test
    public void test3(){
        Man[] men = new Man[5];

        Reflector<Man[]> menR = new Reflector<>(men);

        System.out.println(menR.getClassObject());
        System.out.println(ObjectFactory.getInstance().getClassObject());
        System.out.println(menR.getClassObject().getAllMethods());
        System.out.println(menR.getClassObject().getAllFields());
        System.out.println(menR.getClassObject().getSuperClassObject());

        System.out.println(Arrays.toString(menR.getClassObject().getConstructMethods()));
        System.out.println(Arrays.deepToString(menR.getNewArray(3)));
    }


    @Test
    public void test4(){
        BoyStudent boyStudent = new BoyStudent();
        boyStudent.setGrade("123");
        boyStudent.setAge(15);
        boyStudent.setName("zhaobenshan");
        boyStudent.setNumber("12343515");

        Reflector<BoyStudent> reflector = new Reflector<>(boyStudent);
        System.out.println(reflector.turnToMap(true));

        BoyStudent b = new BoyStudent();
        Reflector<BoyStudent> reflector1 = new Reflector<>(b);
        System.out.println(reflector1.turnToMap(true));

        reflector1.setFromMap(reflector.turnToMap(true));
        System.out.println(reflector.getInnerObject());
        System.out.println(reflector1.turnToMap(true));
    }

    @Test
    public void test5(){
        BoyStudent boyStudent = new BoyStudent();
        Reflector<BoyStudent> boyStudentReflector = new Reflector<>(BoyStudent.class);
        System.out.println("==================");
        System.out.println(boyStudentReflector);
        System.out.println("==================");
        System.out.println(boyStudentReflector.getClassObject());
        Invoker invoker = boyStudentReflector.getClassObject().getMethod("getNumber");
        System.out.println("==================");
        System.out.println(invoker);
        GetterMethod[] getterMethod = boyStudentReflector.getClassObject().getGetterMethod("getNumber");
        System.out.println("==================");
        System.out.println(getterMethod[0]);
        System.out.println("==================");
        System.out.println(getterMethod[0].getAllAnnotation());
        System.out.println("==================");
        AnnotationObject annotationObject = getterMethod[0].getAnnotation(TestAnnotation.class);
        System.out.println(annotationObject.turnToMap());

    }
}