# 随机模块简介

核心类: ``Randomizer<T>``
使用的时候，主要使用Randomizer对对象进行随机化处理，支持注解的使用

扩展类: 
1. ``BaseRandom``,提供了大量封装的随机化方法
2. ``RandomFormatString``,提供了随机格式化字符串的方法

    对于RandomFormatString对象的使用，请查阅[RandomFormatString.md](RandomFormatString.md)
3. Annotations包中提供了大量注解，以RandomLimit为核心的限制数据范围注解，TypeValue\TypeLimit对基本类型和
字符串类型进行约束，Type包括：
    1. byte: ByteLimit, ByteValue
    2. short: ShortLimit, ShortValue
    3. Integer: IntegerLimit, IntegerValue
    4. Long: LongLimit, LongValue
    5. Double: DoubleIndexValue, DoubleValue
    6. Float: FloatIndexValue, FloatValue
    7. Boolean: BooleanValue
    8. Char: CharLimit, CharValue
    9. String: StringFormatValue, StringValue
    
    以上注解在使用的时候，只能标注在字段上，且当字段类型和注解标注类型不一致的时候，不生效，即Integer类型字段不可用ByteLimit注解标注
    
4. 其他具体信息请查阅JavaDoc文档
