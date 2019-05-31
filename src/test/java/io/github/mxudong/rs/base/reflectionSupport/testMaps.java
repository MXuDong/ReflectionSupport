package io.github.mxudong.rs.base.reflectionSupport;

import io.github.mxudong.rs.base.ReflectionSupport;
import io.github.mxudong.rs.base.entity.People;
import io.github.mxudong.rs.base.entity.Student;
import io.github.mxudong.rs.exceptions.ReflectionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dong
 * <p>
 * Class Name : testMaps
 * Create Time : 20:02
 * Create Date : 2019/5/17
 * Project : rs
 */

public class testMaps {
    @Test
    public void testGetCanBeNullMaps() throws ReflectionException {
        People people = new People();
        people.setAge(15);
        System.out.println(ReflectionSupport.objectTurnToMap(people, null, false));
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
