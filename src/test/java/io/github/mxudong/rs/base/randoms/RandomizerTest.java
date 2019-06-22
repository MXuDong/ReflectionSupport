package io.github.mxudong.rs.base.randoms;

import io.github.mxudong.beans.Man;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomizerTest {

    @Test
    public void getInnerObject() {
        Randomizer<Man> manRandomizer = new Randomizer<>(Man.class);
        System.out.println(manRandomizer.getNewInstance());
        System.out.println(manRandomizer.doRandom());
        RandomFilter filter = new RandomFilter() {

            @Override
            public boolean isSpecifiedGeneration(String methodName, Object[] params) {
                if(methodName.equals("setAge")){
                    return true;
                }
                return false;
            }

            @Override
            public Object[] methodParamCreater(String methodName, Object[] params) {
                return new Object[]{15};
            }
        };
        manRandomizer.addRandomFilter(filter);
        System.out.println(manRandomizer.doRandom());
    }

    @Test
    public void getNewInstance() {
    }

    @Test
    public void doRandom() {
    }

    @Test
    public void loadAndGetNewInstance() {
    }
}