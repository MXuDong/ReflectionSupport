# MethodInfos 介绍
所有Method包装类必须实现 ``Invoker`` 和 ``AnnotationAble`` 接口（构造方法除外）

ReflectorSupport 内中的所有Method方法都继承自CommonMethod方法

共有五种方法
1. CommonMethod
2. GetterMethod
3. SetterMethod
4. StaticMethod
5. ConstructionMethod 
    
    (构造方法比较特殊，如果需要自定义，请实现Invoker接口和AnnotationAble接口)

GetterMethod、SetterMethod、StaticMethod继承自CommonMethod

本篇说明以CommonMethod为主，其他信息请查阅JavaDoc文档

# CommonMethod 使用示例
获取一个对象的方法集合
```java
public class Test{
    public static void main(String[] args) {
        Reflector<Human> humanReflector = new Reflector(Human.class);
        // 获取所有 Invoker, Invoker 只提供调用方法，如果需要获取信息，请转换为CommonMethod
        List<Invoker> invokers = humanReflector.getClassObject().getAllMethods();
    }
}
```
有关方法的全部使用方法，请查阅JavaDoc文档
Method包装类的信息在io.github.mxudong.rs.packings.methods包下