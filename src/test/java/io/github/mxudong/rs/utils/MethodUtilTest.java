package io.github.mxudong.rs.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodUtilTest {

    @Test
    public void isParamEquals() {
        Class[] classes1 = new Class[]{};
        Object[] objects1 = null;
        assert MethodUtil.isParamEquals(classes1, objects1);

        Class[] classes2 = new Class[]{};
        Object[] objects2 = new Object[]{};
        assert MethodUtil.isParamEquals(classes2, objects2);

        classes2 = null;
        objects2 = new Object[]{};
        assert MethodUtil.isParamEquals(classes2, objects2);

        classes2 = null;
        objects2 = null;
        assert MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{int.class};
        objects2 = new Object[]{};
        assert !MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{int.class};
        objects2 = new Object[]{1};
        assert MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{int.class};
        objects2 = new Object[]{1,2};
        assert !MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{int.class};
        objects2 = new Object[]{null};
        assert !MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{Integer.class};
        objects2 = new Object[]{2};
        assert MethodUtil.isParamEquals(classes2, objects2);

        classes2 = new Class[]{Integer.class};
        objects2 = new Object[]{null};
        assert MethodUtil.isParamEquals(classes2, objects2);
    }
}