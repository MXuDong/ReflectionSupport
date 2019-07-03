package io.github.mxudong.rs.base;

import io.github.mxudong.beans.Classic;
import io.github.mxudong.beans.Man;
import io.github.mxudong.beans.People;
import io.github.mxudong.beans.Student;
import org.junit.Test;

import java.util.Map;


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
        System.out.println(studentReflector.getObjectInfo());
        Reflector<Student> student = new Reflector<Student>(Student.class, studentReflector.getObjectInfo());
        System.out.println(student);

    }


    @Test
    public void testExtends() {
        Man man = new Man();
        Reflector<Man> manReflector = new Reflector<>(man);
        ObjectReflector manObject = ReflectorFactory.getInstance().getObjectReflector(man.getClass(), true);
        System.out.println(manObject.getReadableProperty());
        System.out.println(manObject.getSuperObjectReflector().getAllProperties());
    }

    @Test
    public void testArray(){
        ObjectReflector objectReflector = ReflectorFactory.getInstance().getObjectReflector(Man.class);
        Man[] mans = (Man[]) objectReflector.createArray(5);
        System.out.println(mans);
        for(Man m : mans){
            System.out.println(m);
        }

        mans = (Man[]) objectReflector.createArrayAndFill(5);
        for(Man m : mans){
            System.out.println(m);
        }
    }

    @Test
    public void testInfos(){
        Classic classic = new Classic();
        Student student = new Student();
        student.setAge(16);
        student.setName("Test");
        student.setNumber("123123123");

        classic.setStudent(student);

        Reflector reflector = new Reflector(classic);
        Map<String, Object> map = reflector.getObjectInfo();
        for(String key : map.keySet()){
            System.out.println(key + map.get(key));
        }

        System.out.println(reflector.getObjectInfoAll());
    }
}