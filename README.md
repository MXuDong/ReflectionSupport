# Web 反射依赖

## 版本说明：
Version:1.0

支持最小的JDK版本:JDK 1.8

## 介绍
包含大量常用的反射或普通方法的封装，可以方便开发的过程和流程

### 提供基本的反射封装
详情请看 Version:1.0 的信息

# 版本说明
## Version:1.0
提供基本的反射支持，包含如下方法    

|方法名|说明|
|:-|-:|
| removeSetOrGetPrefix | 删除Get/Set方法的前缀 |
| addSetOrGetPrefix | 为字段生成Setter/Getter方法名 |
| GetMethodList | 获取类的方法列表 |
| getFieldList | 获取类的字段列表 |
| doMethod | 执行某个方法 |
| mapTurnToObject | 将包含类属性的map注入到类中 |
| objectTurnToMap | 将类的所有数据生成一个Map类型对象 |
## 后续版本将持续更新

### 下一版本等待更新的方法
|方法名|作用|
|:-|-:|
| getClassName | 获取一个类的名称|
### 一下版本加入的扩展
1. 字符串特殊处理(类名分割支持)

# 使用说明
所有方法均为静态方法，所以直接引用即可：
> ReflectionSupport.method(Object ... argms)
## 例如：调用removeSetOrGetPrefix方法
> ReflectionSupport.removeSetOrGetPrefix("getName");

### 在非法状态下的操作将会抛出异常
> ReflectionException