package base.reflectionSupport;

import base.ReflectionSupport;
import base.entity.Student;
import exceptions.ReflectionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dong
 * <p>
 * Class Name : testMaps
 * Create Time : 20:02
 * Create Date : 2019/5/17
 * Project : ReflectionSupport
 */

public class testMaps {
    @Test
    public void testGetCanBeNullMaps() throws ReflectionException {
        Student student = new Student();
        student.setNumber(123);
        System.out.println(ReflectionSupport.objectTurnToMap(student, null, true));
    }

    @Test
    public void testDoSetMaps() throws ReflectionException {
        Map<String, Object> map = new HashMap<>();
        map.put("number", 15);
        map.put("age", 16);

        Student student = new Student();
        student = ReflectionSupport.mapTurnToObject(student, map, null);
        System.out.println(student);
    }

    @Test
    public void testDoMethod() throws ReflectionException {
        System.out.println(ReflectionSupport.doMethod("getNumber", new Student(), null));
    }
}
