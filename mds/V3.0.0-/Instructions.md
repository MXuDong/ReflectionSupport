# ReflectionSupport V 3.0 使用说明
请注意，该版本和之前的版本并不兼容，如果想要
了解之前的版本，请阅读：[V 2.1.1 使用说明](../V2.1.1-/README.md)

### ReflectionSupport的引用方式
请查看 [Install.md](Install.md) 进行了解

# 模块目录
1. 反射模块
    1. Reflector
    2. ClassObjectPacking
    3. AnnotationPacking
    4. MethodPacking
    5. FieldPacking
2. 随机化模块
    1. BaseRandom
    2. RandomFormatString
    3. Randomizer
    4. Annotations
        1. @RandomLimit
        2. @(Type)Limit
        3. @(Type)Value
        
值得注意的是，3.0.0版本内的所有方法（ObjectFactory类除外）都没有支持线程同步

# 模块详细介绍
1. [反射模块](ReflectionModle.md)
2. [随机化模块](RandomModle.md)