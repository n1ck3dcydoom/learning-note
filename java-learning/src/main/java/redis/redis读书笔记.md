## 0 redis常见用法

1. 缓存
2. 分布式锁



# 1 redis基础数据结构

1. `string`  字符串
2. `list`  列表
3. `set`  集合
4. `hash`  哈希表
5. `zset`  有序集合

redis所有数据结构都是通过唯一的 `key` 字符串作为名称，通过唯一 `key` 获取对应的 `value` 值

不同的数据结构唯一的差别就在于 `value` 的结构不一样


## 1.1 String (字符串)

redis的字符串是动态字符串，是可以修改的，实现原理类似于 `java` 的 `arrayList` ，采用预分配冗余空间来减少内存的频繁分配。

扩容方式：字符串长度小于1M时，都是直接翻倍。超过1M后，每次增加1M，最大为512M

### 1.1.1 键值对

`set name test`     其中 `name` 作为唯一key，`test`作为`key name`对应的`value`值

### 1.1.2 批量键值对

mget 批量获取  mset 批量设置

mset name1 John name2 Nick name3 Noone

### 1.1.3 过期时间

对`key`设置过期时间，到点后自动删除，达到缓存的目的

`set name John`

`expire name 5  # 5s后过期`

复合指令  `setex  name 5 John`  其效果等同于上述两条指令

复合指令 `setnx name John  # 如果name不存在，就执行set创建`

### 1.1.4 计数

如果`value`是一个整数，可以对它进行自增操作，最大的范围是有符号长整型 `signed long`的最大值和最小值，超出这个返回会报错

`set count 5`

`incr count`

指定参数的自增：`incrby count 5 # count在当前基础上自增5`



总结：字符串是由多个字节组成，每个字节由8个bit组成，所以字符串可以看作是由若干个`bit`组成的集合，这就是`bitmap` “位图” 数据结构。

## 1.2 list 列表

相当于java里面的LinkedList，是一个链表而非数组。所以操作链表的头部和尾部的效率非常快，但是遍历索引链表的某个位置效率很低。

当链表的最后一个元素被移除掉后，这个数据结构将会自动删除，内存被redis自动回收。

**列表结构通常用于做 异步队列 使用：**

