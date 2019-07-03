# ReflectionSupport 反射工具箱
简化反射操作

# 当前版本说明
#[ReflectionSupport文档](https://apidoc.gitee.com/MXuDong/ReflectionSupport)

##### 代码升级中。。。。。。。。即将重构开启第三个版本
    第三个版本将全面支持 Annotation、Enum等操作
    同时对Field、Method、Class进行全面挂钩，重构链接关系与方法

# 2.1 已经发布
## [2.1说明文档](V2.1.1-/Instructions.md)
支持最小的JDK版本:JDK 1.8

整合了[类随机化项目](https://gitee.com/MXuDong/RandomDataForClass)
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
[请参考：ReflectionSupport安装说明](V2.1.1-/Install.md)

# 目前以支持功能： 对任意对象完全反射支持
    
    支持数据转换：对象转map， map转对象 
    支持部分方法反射调用
    支持数据随机化
    
# 下一版本支持特性：
    1. 随机化相关注解