# ReflectionSupport 反射模块

# 模块详细介绍
1. 类对象反射解析``Object.Class``对象
    使用反射解析对象可以对任意类进行反射解析
    1. 获取全部字段信息 
    2. 获取方法信息    
    3. 获取实现的接口信息
    4. 获取类级别注解信息
    
2. 方法包装``Method``对象
    使用Method包装对象可以对方法进行控制
    1. 调用方法
    2. 获取方法参数列表
    3. 获取方法返回值信息
    4. 获取方法声明的注解信息
    5. 获取方法类型
    
    方法类型
    1. 普通方法
    2. 构造器方法
    3. Setter方法
    4. Getter方法
    
    具体请查阅 [MethodInfos.md](MethodInfos.md)
3. 字段包装``Field``对象
    使用Field包装对象可以对字段进行控制
    1. 获取字段声明类型
    2. 获取字段标志类型
        1. static
        2. final
        3. common
    3. 设置字段值
    4. 获取字段值
    
    具体请查阅 [FieldInfos.md](FieldInfos.md)
4. 注解类包装``Annotation.Class``对象
    使用Annotation.class包装对象可以对注解进行解析
    功能与ClassObject相似
    具体请查阅[AnnotationClassInfos.md](AnnotationClassInfos.md)
5. 注解对象包装``Annoatation``对象
    使用Annotation的包装对象可以快速检查注解信息
    1. 获取注解信息
    2. 将注解信息转为``Map<String, Object>``对象
 
    具体请查阅[AnnotationInfos.md](AnnotationInfos.md)
6. 对象解析类``Object``
    1. 对对象的包装，包括所有有关对象的操作
    2. 封装ClassObject对象

# 使用过程

##### 定义
    所有代码中对象Human的结构
```java
class Human{
    private int age;
    private String name;
    public String number;
}
```
    以上三个属性均包含Setter方法和Getter方法
    包含默认构造器并重写 toString() 方法

### 1. 声明一个Reflector对象
声明一个Reflector对象即可操作如下
```java
class Test{
    public static void main(String[] args) {
        Reflector<Human> humanReflector = new Reflector(Human.class );
    }
} 
```
接下来使用Reflector进行操作即可
这里给出了Reflector的常用方法列表，其他具体方法请查看JavaDoc文档

|方法声明|说明|备注|
|:---|:---|---:|
|``public Reflector(T object)``|构造方法||
|``public Map<String, Object> turnToMap(boolean canBeNull)``|将对象转为Map对象|参数``canBeNull``表示被转换对象属性如果为空是否添加|
|``public void setFromMap(Map<String, Object> infos)``|按照``infos``进行设置字段信息||
|``public Object invokeMethod(String methodName, Object... args)``|调用方法||
|``public ClassObject<T> getClassObject()``|获取``ClassObject<T>``对象|此方法最常用，通常通过ClassObject对象获取很多对象信息|

### 2. 通过Reflector获取一个ClassObject对象
```java
public class Test{
    public static void main(String[] args) {
        Reflector<Human> humanReflector = new Reflector(Human.class);
        ClassObject<Human> humanClassObject = humanReflector.getClassObject(); 
    }
}
```
或者通过工厂直接创建
```java
public class Test{
    public static void main(String[] args) {
        ClassObject<Human> humanClassObject = ObjectFactory.getInstance().getClassObject(Human.class);
    }
}
```
拿到了ClassObject对象后，我们可以拿到相关的信息

这里给出了一些常用的方法，具体的方法请参考Java文档

|方法声明|说明|备注|
|:---|:---|---:|
|``public List<AnnotationObject> getAllAnnotation()``|获取全部的注解信息||
|``public ArrayList<CommonField> getAllFields()``|获取全部的字段信息||
|``public ArrayList<Invoker> getAllMethods()``|获取全部方法|在父类中声明的方法也会被获取|
|``public Invoker[] getMethods()``|获取本类声明的方法||