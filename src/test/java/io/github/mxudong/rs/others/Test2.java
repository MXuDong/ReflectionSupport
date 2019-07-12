package io.github.mxudong.rs.others;

import io.github.mxudong.rs.annotations.TestAnnotation;
import io.github.mxudong.rs.bean.BoyStudent;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Dong
 * @since 3.0
 */

public class Test2 {

    @Test
    public void Test1(){
        Class<BoyStudent> boyStudentClass = BoyStudent.class;

        TestAnnotation testAnnotation = boyStudentClass.getDeclaredAnnotation(TestAnnotation.class);
        Class testAnnotationClass = testAnnotation.getClass();
        System.out.println(Arrays.toString(testAnnotationClass.getDeclaredMethods()));
        String test = testAnnotation.annotationName();
        System.out.println(test);
    }
}
