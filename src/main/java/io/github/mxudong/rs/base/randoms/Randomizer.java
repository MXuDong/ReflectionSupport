package io.github.mxudong.rs.base.randoms;

/**
 * Class Name : Randomizer
 * Create Time : 20:06
 * Create Date : 2019/6/19
 * Project : ReflectionSupport
 *
 * Class randomizer, used to randomly generate objects
 *
 * @author Dong
 * @since 2.1
 */

public class Randomizer <T extends Object> {
    Class<T> tClass;

    public Randomizer(Class<T> tClass){
        this.tClass = tClass;
    }

    public Class<T> gettClass() {
        return tClass;
    }
}
