# AnnotationClass 介绍

AnnotationClass 继承自 ClassObject， 重写了invokeMethod的方法

AnnotationClass 是用一个注解的类解析

# 本类用途
主要用于AnnotationObject的使用，对ClassObject的一次扩展

与ClassObject相同，只能由ObjectFactory创建，如果需要重写，请重写ObjecFactory，保证数据的统一性。

本类对象并不保证同步


    由于本类基本上不会被使用，因此不做过多介绍，如果需要更多信息，请参考Java文档