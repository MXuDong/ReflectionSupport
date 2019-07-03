# 随机化支持

## 说明
可以快速随机一个Bean，并支持定制生成，您可以通过随机化的组合生成任意想要的数据格式，这里将会说明如何使用随机化支持
___
随机化支持提供了三种基本随机方案：
1. 完全随机生成(使用默认策略)
2. 指定策略生成，指定某些数据的格式，或者为某个特定的数据指定生成特定格式的数据
3. 使用注解生成（请注意，该策略正在建设中）

## 相关类介绍
1. ``Randomizer``类提供了基本的随机支持，构造时，仅需将类对象传入，调用``getNewInstance()``即可
同时，该类存在随机化方法，调用后可以将内部保存的类随机化。
2. ``RandomFilter``接口声明了三种方法，可以通过该接口的实现类控制某个数据信息是否被随机化、随机化的策略、随机化的结果控制
3. ``BaseRandom`` 类提供了大量的静态方法，封装了Random类，提供了丰富的方法提供调用，StringExtension中定制随机字符生成使用了大量本类中的方法

## 使用方法
随机化一个Man类
```java
public class Test{
    public static void main(String [] args){
        Randomizer<Man> manRandomizer = new Randomizer<>(Man.class);
        Man man = manRandomizer.getNewInstance();
    }
}
```
使用随机化的前提是实例``Randomizer``对象，内置大量的数据方法提供使用。
___
使用过滤器``RandomFilter``
```java
public class Test{
    public static void main(String [] args){
        Randomizer<Man> manRandomizer = new Randomizer<>(Man.class);
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
        Man man = manRandomizer.getNewInstance();
    }
}
```
上例代码中，将对age数据单独处理，将特定设置为 ``15``