package io.github.mxudong.rs.base;

import io.github.mxudong.beans.Man;
import io.github.mxudong.beans.People;
import io.github.mxudong.beans.Student;
import io.github.mxudong.rs.base.methods.AbsConstructor;
import org.junit.Test;

import static org.junit.Assert.*;


public class ReflectorTest {

    @Test
    public void testBase() {
        Reflector<Student> studentReflector = new Reflector<>(new Student(15, "name", "number"));

        ObjectReflector absConstructor = ReflectorFactory.getInstance().getObjectReflector(Student.class);
        System.out.println(absConstructor.equals(ReflectorFactory.getInstance().getObjectReflector(studentReflector.getObjectClass())));
        System.out.println(studentReflector);
        System.out.println(studentReflector.getNewInstance());
        System.out.println(studentReflector.getObject());
        System.out.println(studentReflector.getObjectClass());
        System.out.println(int.class.isInstance(15));

        Reflector<People> peopleReflector = new Reflector<>(new People("test1", "tes2"));
        ObjectReflector people = ReflectorFactory.getInstance().getObjectReflector(peopleReflector.getObjectClass());
        System.out.println(people.getInstance("test3", "test4"));

        System.out.println(people.getClassName());
        System.out.println(people.getPackageName());

        System.out.println(people.getReadableProperty());
    }


    @Test
    public void testExtends() {
        Man man = new Man();
        ObjectReflector manObject = ReflectorFactory.getInstance().getObjectReflector(man.getClass(), true);
        System.out.println(manObject.getReadableProperty());
        System.out.println(manObject.getSuperObjectReflector().getAllProperties());
    }
}