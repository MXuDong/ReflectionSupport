package io.github.mxudong.rs;

import io.github.mxudong.rs.bean.Man;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ReflectorTest {

    @Test
    public void test1(){
        Reflector<Man> manReflector = new Reflector<>(new Man());
        System.out.println(Arrays.toString(manReflector.getClassObject().getAllmethods()));
        System.out.println(manReflector.turnToMap(true));
    }

}