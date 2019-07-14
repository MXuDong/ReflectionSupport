package io.github.mxudong.rs.randoms;

import io.github.mxudong.rs.bean.BoyStudent;
import org.junit.Test;

public class RandomizerTest {

    @Test
    public void test01() {
        Randomizer<BoyStudent> randomizer = new Randomizer<>(BoyStudent.class);
    }

}