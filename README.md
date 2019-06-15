# ReflectionSupport 反射工具箱

# 版本 : 2.0
# 状态
开发中

# 使用
[1.* 版本](https://github.com/MXuDong/ReflectionSupport/blob/master/README-Version1.X.md)

# 2.0 已经发布
## [2.0说明文档](mds/Instructions.md)


# 功能介绍
1. 封装了常用的反射操作
2. 提供了常用的处理方案

支持最小的JDK版本:JDK 1.8

# Maven 依赖

```xml
<!-- https://mvnrepository.com/artifact/io.github.mxudong/ReflectionSupport -->
<dependency>
    <groupId>io.github.mxudong</groupId>
    <artifactId>ReflectionSupport</artifactId>
    <version>2.0</version>
</dependency>

```

# 安装说明：
请参考：ReflectionSupport安装说明（Install.md）

## 介绍
包含大量常用的反射或普通方法的封装，可以方便开发的过程和流程

# 本版本不和1.X版本兼容！ 请注意

# 目前以支持功能：
对任意对象完全反射支持
    
    支持数据转换：对象转map， map转对象
    
    支持部分方法反射调用
# 将被添加的功能
1. 随机生成支持

    1. 扩展随机生成的局限性
    2. 支持定制随机生成
    3. 任意类型的随机生成
    4. 列表等集合类单独处理
    5. 数组定制