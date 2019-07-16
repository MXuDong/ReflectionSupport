# ReflectionSupport 反射模块

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