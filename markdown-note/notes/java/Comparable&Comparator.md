# 关于Comparable和Comparator

如果我们需要比较一些自定义对象的大小关系，例如按照对象内的某些属性去比较大小。

## 1 Comparable 接口

这是一个函数式接口，只含有一个方法 `compareTo(Object o)` ,任何实现了这个接口的类都是可以比较的，比较规则则需要自己去实现 `compareTo(Object o)` 。

## 2 Comparator 比较器

如果类的设计者忘记了在实体类中实现 `Comparable` 接口，那么他仍然可以通过去实现一个比较器，去自定义比较规则。