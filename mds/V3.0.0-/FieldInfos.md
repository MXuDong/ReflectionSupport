# Field介绍

CommonField 在``io.github.mxudong.rs.packings.fields``下

CommonField实现了AnnotationAble接口，提供注解解析方法

FieldType包含了字段的类型
    1. COMMON_FIELD
    2. STATIC_FIELD
    3. FINAL_FIELD

# 主要用途
对Field进行了封装，如果一个属性具有getter和setter方法，将会
与相应的方法联动。同时提供了直接设值和直接取值的方法，当然，如果
属性为``private``将会失败。建议在使用相应的方法前判断一下
是否可以set和get

如果要获取字段的注解，使用相应的注解方法即可，已经封装了相应的信息

# CommonField使用示例

