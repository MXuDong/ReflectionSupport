package io.github.mxudong.rs.others;

import io.github.mxudong.rs.annotations.TestAnnotation;
import io.github.mxudong.rs.bean.BoyStudent;
import io.github.mxudong.rs.packings.classes.AnnotationObject;
import org.junit.Test;

import java.lang.annotation.Annotation;
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
        System.out.println(annotationObject.turnToMap());
        System.out.println(annotationObject.getInfo("annotationName"));
        System.out.println(annotationObject);
    }
}
