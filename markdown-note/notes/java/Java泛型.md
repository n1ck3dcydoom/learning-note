### 类型擦除

java的泛型有个很重要的知识点就是类型擦除，什么是类型擦除呢。

类型擦除：指Java泛型中所有的参数类型在编译后都会被还原。

如下面的例子：
```
public void foo(List<String> strList) {
    // do something with List<String>
}

public void foo(List<Integer> intList) {
    // do something with List<Integer>
}
```
上述代码编译器会报错：
```
'foo(List<String>)' clashes with 'foo(List<Integer>)'; both method have same erasure
```
也就是说这两个方法在类型擦除之后有相同的签名。

这里参考[《Java核心技术卷一》第八章有关泛型类型擦除的解释](../1_CoreJavaVolumeI/No8_Generic/1.md)：