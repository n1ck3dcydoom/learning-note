# Java中关于 == 和 equals 的区别

## 基本数据类型

1. 浮点型：float(4 Byte), double(8 Byte)

2. 整型：byte(1 Byte), short(2 Byte), int(4 Byte), long(8 Byte)

3. 字符型: char(2 Byte)

4. 布尔型: boolean (JVM规范没有明确规定其所占的空间大小，仅规定其只能够取字面值"true"和"false")

## 其他数据类型

对于基本数据类型，在java中有它们的封装类型：

1. 浮点型：Float, Double

2. 整型：Byte, Short, Integer, Long

3. 字符型: Character

4. 布尔型: Boolean

算上这些包装类型和其他所有类型，都称作非基本类型，也有称为 "引用类型" 。

## ==

1. 对于基本类型来说，变量是直接存储的 **值**，因此使用 "==" 对基本数据类型作比较时，是直接比较的它们的值。而且需要注意的是，除了 `char` 和 `boolean` 其他都是有符号的。

2. 对于非基本类型也就是 "引用类型" 来说，由于引用类型存储的不是 "值" ，而是与这个值相关联的 **地址** ，因此使用 "==" 对引用类型作比较时，其实比较的是它们指向的地址是否相同。

例如：
```
int n = 3;
int m = 3;
String str1 = new String("test");
String str2 = new String("test");
      
System.out.println(n == m);       // bool1
System.out.println(str1 == str2); // bool2
```
因此上述比较中：`bool1 = true` 而 `bool2 = false` 。因为 str1 和 str2 分别指向了两个地址不同的 "test" 字符串，所以 == 操作结果为 false

## equals

`equals()` 方法为 `Object` 类中的方法，因此所有的类都有此方法，只是有的类中重写了，有的类中没有重写。

Object类中源代码 (jdk8)：
```
public boolean equals(Object obj) {
    return (this == obj);
}
```
可以看到，其实 Object 类中的 equals 方法也是直接使用的 == 进行比较。

所以如果一个类没有重写 equals 方法的话，那么使用 equals 和使用 == 是一样的结果。