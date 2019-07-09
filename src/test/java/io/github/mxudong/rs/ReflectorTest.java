package io.github.mxudong.rs;

import io.github.mxudong.rs.bean.Man;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ReflectorTest {

    @Test
    public void test1(){
        Man man = new Man();
        Reflector<Man> manReflector = new Reflector<>(man);
        System.out.println(Arrays.toString(manReflector.getClassObject().getAllmethods()));
        System.out.println(manReflector.turnToMap(true));
        man.setAge(15);
        System.out.println(manReflector.turnToMap(true));
    }

}