package io.github.mxudong.rs.others;

import io.github.mxudong.rs.Reflector;
import io.github.mxudong.rs.annotations.TestAnnotation;
import io.github.mxudong.rs.bean.BoyStudent;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import io.github.mxudong.rs.packings.classes.ObjectFactory;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Dong
 * @since 3.0
 */

public class Test2 {

    @Test
    public void Test1() {
        Class<BoyStudent> boyStudentClass = BoyStudent.class;

        Annotation testAnnotation = boyStudentClass.getDeclaredAnnotation(TestAnnotation.class);
        Class testAnnotationClass = testAnnotation.getClass();
        System.out.println(Arrays.toString(testAnnotationClass.getDeclaredMethods()));
        Method[] methods = testAnnotationClass.getDeclaredMethods();
        System.out.println("==================================================");
        for(Method m : methods){
            System.out.println(m.getName());
        }
        System.out.println("==================================================");
        try {
            System.out.println(Arrays.toString(testAnnotationClass.getFields()));
            System.out.println(testAnnotationClass.getMethod("annotationName").invoke(testAnnotation));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        AnnotationObject annotationObject = new AnnotationObject(testAnnotation);
        System.out.println(annotationObject.getAnnotationClass().getAnnotationCount());
        System.out.println(annotationObject.turnToMap());
        System.out.println(annotationObject.getInfo("annotationName"));
        BoyStudent boyStudent = new BoyStudent();
        Reflector<BoyStudent> studentReflector = new Reflector<BoyStudent>(boyStudentClass);
        System.out.println(studentReflector.getClassObject().getAnnotationCount());

        System.out.println(ObjectFactory.getInstance().getClassObject());
    }

    @Test
    public void Test2(){
        Class<BoyStudent> boyStudentClass = BoyStudent.class;
        Annotation testAnnotation = boyStudentClass.getDeclaredAnnotation(TestAnnotation.class);
        Documented documented = testAnnotation.annotationType().getAnnotation(Documented.class);
        System.out.println(Arrays.toString(documented.annotationType().getAnnotations()));
    }
}
