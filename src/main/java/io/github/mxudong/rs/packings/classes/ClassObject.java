package io.github.mxudong.rs.packings.classes;

import org.jetbrains.annotations.NotNull;

/**
 * this class packing the class, and for this class, you
 * only create from ClassObjectFactor
 *
 * @author Dong
 * @since 3.0
 */

public class ClassObject<T> {

    /*
    规划：
        包含重载方法集合
        包含全部字段集合
        包含全部构造器集合
        检测默认构造器
        包含所有实现的接口信息
        包含所有类级别的注解信息

        可通过此类实现实例方法
            默认和指定参数类型两种方式
        获取方法
        获取字段
        调用方法
        获取字段值
        判断是否私有、公开、默认、保护的权限

     */

    /**
     * packing class
     */
    private Class<T> packingClass;

    /**
     * super class object of packing class, will it null, it
     * is {@code Object}
     */
    private ClassObject<?> superClassObject;

    /**
     * construction method
     *
     * @param c packing class
     */
    protected ClassObject(@NotNull Class<T> c) {
        this.packingClass = c;
        superClassObject = ClassFactory.getInstance().getClassObject(c.getSuperclass());
    }

    /**
     * return the packing class
     *
     * @return the packing class
     */
    public Class<T> getPackingClass() {
        return packingClass;
    }

    /**
     * getter method of superClassObject;
     *
     * @return super class object
     */
    public ClassObject<?> getSuperClassObject() {
        return superClassObject;
    }
}
