package io.github.mxudong.rs.base.reflectionSupport;

import io.github.mxudong.rs.base.ReflectionSupport;
import io.github.mxudong.rs.base.entity.People;
import io.github.mxudong.rs.base.entity.Student;
import org.junit.Test;

import java.util.List;

/**
 * @author Dong
 * <p>
 * Class Name : TestGetClassPropertyList
 * Create Time : 20:54
 * Create Date : 2019/5/11
 * Project : rs
 */

public class TestGetClassPropertyList {

    @Test
    public void testCommon(){
        System.out.println(new People().getClass().getSuperclass().getSuperclass());
    }

    @Test
    public void testGetCommon(){
        List<String> res = ReflectionSupport.getFieldList(new Student(), 0, null, true);
        System.out.println(res);
    }

    @Test
    public void testSupperClass(){
        List<String> res = ReflectionSupport.getFieldList(new Student(), 1, null, false);
        System.out.println(res);
    }
}
