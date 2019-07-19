# 格式化字符输出

## 字符串格式化输出
```java
class Test{
    public static void main(String[] args){
        System.out.println(RandomFormatString.createRandomStringBase("*26"));
        System.out.println(RandomFormatString.createRandomStringBase("[https://]n9[@]{q}2[.com]"));
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
        
介绍

|字符|意义|示例(随机生成)|
|:---|:---:|:---:|
|*|任意字符|*3 : (3c|
|n|全部数字|n5 : 22635|
|c|全部字母|c4 : Dong|
|l|小写字母|l4 : DONG|
|u|大写字母|u4 : dong|
|s|全部符号|s4 :(-_-)|
|[]|[]内数数据将直接被打印|[Dong]:Dong|
|{}|使用{}内的数据|{test}2 : ts|
|-|[]、{}转义字符，仅可以表示成：--, []内的-],{}内的-},不可在[]中使用-}|\[-]] : ]|
|()|成组出现|([2]2[t])2 : 22tt22tt|
|\<1?(竖线)2?\>|表示生成数量, 从1?开始到2?之间的任意值，1?不写为0，2不写为15|([2])<1(竖线)3> : 22|

示例

生成长度为10到20之间的任意长度的数字：

    ``n<10|21>``

生成3个任意大写字符加1个任意符号：

    ``u3s``

生成以``http://``开头,8-11位数字，并以``@qq.com``结尾的字符串，最多7条（/n）

    ``([http://]n<8|12>[@qq.com\n])<|8>``
    结果：
    http://5213071174@qq.com
    http://5923803401@qq.com
    http://312027412@qq.com
