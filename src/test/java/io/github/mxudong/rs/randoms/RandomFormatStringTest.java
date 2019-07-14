package io.github.mxudong.rs.randoms;

import org.junit.Test;

public class RandomFormatStringTest {

    @Test
    public void test1(){
        System.out.println(RandomFormatString.createRandomStringBase("n5"));
        String region = "([今天是星期]{一二三四五六七})2";
        System.out.println(RandomFormatString.createRandomStringBase(region));
        region = "({1}n3[--]{10}n[--]{123}n[\n])<2|5>";
        System.out.println(RandomFormatString.createRandomStringBase(region));
    }

}