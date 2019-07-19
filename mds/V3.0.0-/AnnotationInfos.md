# AnnotationInfos 简介

AnnotationInfos 对 AnnotationClass 进行了一次封装，提供了实例保留，可直接操作。

提供了转换成Map的方法

提供了获取信息的方法，可以直接利用``new``关键字生成,也可以由实现了AnnotationAble接口的类进行获取

具体方法请看JavaDoc文档

这里给出了一个基本使用示例

    Randomizer部分代码
```java
private byte createRandomByte(CommonField field) {
    AnnotationObject annotationObject = field.getAnnotation(ByteValue.class);
    if (annotationObject != null) {
        return (byte) annotationObject.getInfo("value");
    }

    annotationObject = field.getAnnotation(ByteLimit.class);
    if (annotationObject != null) {
        return BaseRandom.getRandomByte((byte) annotationObject.getInfo("minValue"),
                (Byte) annotationObject.getInfo("maxValue"));
    }

    return BaseRandom.getRandomByte(this.defaultByteMinValue, this.defaultByteMaxValue);
}
```