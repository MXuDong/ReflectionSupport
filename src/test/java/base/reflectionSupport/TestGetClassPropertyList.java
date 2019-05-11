package base.reflectionSupport;

import base.ReflectionSupport;
import base.entity.People;
import base.entity.Student;
import org.junit.Test;

import java.util.List;

/**
 * @author Dong
 * <p>
 * Class Name : TestGetClassPropertyList
 * Create Time : 20:54
 * Create Date : 2019/5/11
 * Project : ReflectionSupport
 */

public class TestGetClassPropertyList {

    @Test
    public void testCommon(){
        System.out.println(new People().getClass().getSuperclass().getSuperclass());
    }

    @Test
    public void testGetCommon(){
        List<String> res = ReflectionSupport.getClassFieldList(new Student(), 0, null, true);
        System.out.println(res);
    }

    @Test
    public void testSupperClass(){
        List<String> res = ReflectionSupport.getClassFieldList(new Student(), 1, null, false);
        System.out.println(res);
    }
}
