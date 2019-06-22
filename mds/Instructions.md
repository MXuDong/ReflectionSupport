# ReflectionSupport 使用说明

[toc]

# 安装说明
[请参考：mds/Install.md](Install.md)

# 版本限制
    项目限制在JDK1.8及以上版本

# 2.0说明文档
版本：2.0

## 声明关键名词
1. targetObject 目标对象，被反射处理的对象
2. targetClass 目标对象的类
3. Reflector 实例反射对象，需要targetObject
4. ObjectReflector 类反射对象，需要targetClass

## 说明简化

在所有的代码中，省略POJO类的声明

目标中存在的POJO类如下：
1. People(int age, String name);
2. Student(int number) extends People;

## 实例化Reflector对象，并传入目标对象（实例化实例反射对象）
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
    }
}
```
    如上代码中，使用People初始化一个Reflector对象，同时利用Reflector对象可对people进行反射操作。
    reflector包含大量的反射方法提供使用，使用时，直接调用即可。
    
## 获取目标对象的键值对格式数据
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      
      Map<String, Object> peopleInfos = peopleReflector.getObjectInfo();
    }
}
```
    如上代码中,peopleInfos中保存着对象people的属性值
    这些属性值必须保证是可读的（具有getter方法），否则无法获取
    当属性值为null的时候，依旧获取

## 通过Reflector实例一个新对象
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      
      People newPeople = peopleReflector.getNewInstance();
    }
}
```
    当然，必须保证people具有默认构造方法，如果没有将返回 null
    其次，如果需要通过特定的传值初始化,可通过Reflector.getNewInstance(Object ... args)方法实现
    在调用getNewInstance(Object ... args)的时候，没有构造方法能够接收args,将返回 null
    getNewInstance()的方法调用了ObjectReflector的getNewInstance()方法

## 获取类反射对象
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      // 通过peopleReflector获得类反射对象
      ObjectReflector of = peopleReflector.getObjectReflector();
    }
}
```
    ObjectReflector 不可实例化，由反射工厂生成并管理
    反射工厂支持两种初始化方法，单加载和父级加载两种，单加载不会加载父类对象，父级加载会加载到Object对象为止
    反射工厂为单例模式
    ObjectReflector支持大量的反射支持包括方法调用，属性获取等方法
    
## 获取所有可读\可写属性列表
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      ObjectReflector of = peopleReflector.getObjectReflector();
      
      Set<String> readablePropertyNames = of.getReadableProperty();
      Set<String> writablePropertyNames = of.getWritableProperty();
    }
}
```
    在调用这两个放的时候，只有一个属性存在相应的getter/setter方法，才会出现在集合中
    可以通过属性名获取某个对象的属性值，调用getter方法即可
    
## 调用getter\setter方法
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      ObjectReflector of = peopleReflector.getObjectReflector();
      
      String name = of.invokeGetterMethod("name", people);
      of.invokeSetterMethod("age", people, 21);
    }
}
```
    其他方法类似，包含静态方法，普通方法，setter/getter方法
    以上四种方法均包含另一个重载方法，新增参数为superClassSearchDeep，
    此含义表示，如果当前类中没有找到目标方法，则是否继续调用
    当supperClassSearchDeep为大于0的数字是，会向上查找该数值次，直到Object对象
    如果小于0，则知道Object对象，否则不会停止
    如果等于0，不查找，仅在本类中执行一次

## 获取父类反射对象
```java
class Test{
    public static void main(String[] args){
      People people = new People(20, "People");
      Reflector<People> peopleReflector = new Reflector<>(people);
      ObjectReflector of = peopleReflector.getObjectReflector();
      
      ObjectReflector ofSupper = of.getSuperObjectReflector();
    }
}
```
    获取父级类反射对象
    
## 随机化方法
```java
class Test{
    public static void main(String[] args){
        //获取随机化对象
      Randomizer<Man> manRandomizer = new Randomizer<>(Man.class);
        //随机化操作
      manRandomizer.doRandom();
        //获取随机化对象（本代码直接输出）
      System.out.println(manRandomizer.getInnerObject());
    }
}
```
    获取对象的随机化支持

## 字符串格式化输出
```java
class Test{
    public static void main(String[] args){
        StringExtension stringExtension = new StringExtension();
        System.out.println(stringExtension.createRandomStringBase("*26"));
        System.out.println(stringExtension.createRandomStringBase("[https://]n9[@]{q}2[.com]"));
    }
}
```
    '*26'表示任意字符共生成26个
    '([https://]n9[@]{q}2[.com][\n])5'表示生成如下格式字符串：
        https://405323393@qq.com
        https://194214554@qq.com
        https://827324312@qq.com
        https://777167717@qq.com
        https://000924846@qq.com
有关格式化字符输出请看[格式化字符输出](FormatString.md)