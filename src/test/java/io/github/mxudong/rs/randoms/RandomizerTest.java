package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.bean.BoyStudent;
import io.github.mxudong.rs.bean.TestRandomBean;
import org.junit.Test;

public class RandomizerTest {

    @Test
    public void test01() {
        Randomizer<BoyStudent> randomizer = new Randomizer<>(BoyStudent.class);
    }

    @Test
    public void test02(){
        Randomizer<BoyStudent> randomizer = new Randomizer<>(BoyStudent.class);
        System.out.println(randomizer.getInnerObject());
        randomizer.doRandom(false);
        System.out.println(randomizer.getInnerObject());
    }

    @Test
    public void test03(){
        TestRandomBean randomBean = new TestRandomBean();
        Randomizer<TestRandomBean> testRandomBeanRandomizer = new Randomizer(randomBean);
        System.out.println(testRandomBeanRandomizer.getInnerObject());
        testRandomBeanRandomizer.doRandom(true);
        System.out.println(testRandomBeanRandomizer.getInnerObject());
    }

}