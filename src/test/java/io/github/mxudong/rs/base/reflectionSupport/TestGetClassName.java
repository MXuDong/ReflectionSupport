package io.github.mxudong.rs.base.reflectionSupport;

import io.github.mxudong.rs.base.ReflectionSupport;
import io.github.mxudong.rs.base.entity.Student;
import org.junit.Test;

/**
 * Class Name : TestGetClassName
 * Create Time : 9:52
 * Create Date : 2019/5/26
 * Project : ReflectionSupport
 *
 * @author Dong
 * @since
 */

public class TestGetClassName {
    @Test
    public void testStringSplit(){
        String test = "package.innerpackage.classone";

        String res = ReflectionSupport.getClassNames(test, 0, 1);
        System.out.println(res);
    }

    @Test
    public void testGetClassNameOnly(){
        String test = "package.classOne";

        String res = ReflectionSupport.getClassNames(test, 1, -1);
        System.out.println(res);
    }

    @Test
    public void testGetClassName(){
        Student student = new Student();
        System.out.println(ReflectionSupport.getClassName(student));
    }
}
