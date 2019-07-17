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
### 字段获取
```java
public class Test{
    public static void main(String[] args) {
        Reflector<Human> humanReflector = new Reflector<>(Human.class);
        ClassObject<Human> humanClassObject = humanReflector.getClassObject();
        List<CommonField> commonFields = humanClassObject.getAllFields();
    }
}
```
    通过以上方法可以快速获取字段

### 字段信息获取
```java
public class Test {
    public static void main(String[] args) {
        Reflector<Human> humanReflector = new Reflector<>(Human.class);
        ClassObject<Human> humanClassObject = humanReflector.getClassObject();
        List<CommonField> commonFields = humanClassObject.getAllFields();
        for (CommonField commonField : commonFields) {
            boolean canFieldSet = commonField.canSet();
            boolean canFieldGet = commonField.canGet();
            boolean isFieldFinal = commonField.isFinal();
            boolean isFieldPublic = commonField.isPublic();
            boolean isFieldStatic = commonField.isStatic();
            Class belongClass = commonField.getFieldBelongClass();
            String fieldName = commonField.getFieldName();
            Class fieldType = commonField.getFieldType();
            int annotationCount = commonField.getAnnotationCount();
            List<AnnotationObject> annotationObjects = commonField.getAllAnnotation();
            String getterMethodName = commonField.getGetterMethodName();
            String setterMethodName = commonField.getSetterMethodName();
            //值操作
            if (commonField.canSet()) {
                //这种方式实际上调用了setter方法
                commonField.setValue(humanReflector.getInnerObject(),[value]);
                //直接设值
                commonField.setValueDirect(humanReflector.getInnerObject(),[value]);
            }
            if (commonField.canGet()) {
                //这种方式实际上调用了Getter方法
                Object value = commonField.getValue(humanReflector.getInnerObject());
                //直接取值
                Object value = commonField.getValueDirect(humanReflector.getInnerObject());
            }

        }
    }
}
```
    具体的其他方式请查阅Java文档