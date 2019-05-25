# Web 反射依赖

## 版本说明：
Version:1.0

支持最小的JDK版本:JDK 1.8

## 介绍

### 提供基本的反射封装

### 版本说明
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