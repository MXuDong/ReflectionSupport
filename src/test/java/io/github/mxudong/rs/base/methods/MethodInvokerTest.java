package io.github.mxudong.rs.base.methods;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodInvokerTest {


    @Test
    public void baseTest(){
        String [] temp = MethodInvoker.splitMethodName("testMethodSplit");
        for(String str : temp){
            System.out.println(str);
        }
    }

}