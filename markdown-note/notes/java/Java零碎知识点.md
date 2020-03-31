### 1 包装类型不可以直接赋值
Double d = 3 是错误的赋值语句，因为包装类型不可以直接赋值，必须new出对象。

### 2 k++原理
```java
public static void main(String[] args){
  int count = 0;
  int sum = 0;
  for(int i = 0 ; i < 100 ; i++){
      sum += i;
      count = count++;
  }
}
```
请问上述代码最后count的值为多少，答：0


i++ 和 ++i 原理：

i++：先自增，然后返回自增之前的值

++i：先自增，然后返回自增后的值

无论是前置++还是后置++，其计算结果都是 **先自增，但是返回的值不同**。

用代码表示：

++i:
```
i = i + 1;
return i;  // 先自增，然后返回自增后的值
```

i++:
```
int temp = i;
i = i + 1;
i = temp;
return i;  // 先自增，但是返回的是自增前的值
```

### 3 重载（复习）

构成重载的要素：

1. 参数类型不同

2. 参数顺序不同

3. 参数个数不同

**注意：如果参数类型、顺序、个数都相同，但仅仅是返回值类型不同，无法构成重载。**

### 4 关于 synchronized关键字

1. 构造方法每次都是构造出新的对象，不存在多个线程同时读写同一对象中的属性的问题，所以不需要同步 。

2. 如果父类的某个方法被synchronized修饰，那么子类继承得到的方法默认是 **不带有** synchronized修饰的，所以如果子类需要同步这个方法，需要显式地加上synchronized关键字，或者在子类中调用父类的同步方法。

### 5 instanceof

如果C继承B，B继承A，那么：
```
A obj = new C()
obj instanceof A; // true
obj instanceof B; // true
obj instanceof C; // true
```

### 6 基本数据类型的比较

参见 `Java核心技术卷一第43页` ：

两个数值进行二元操作时，会有如下的转换操作：

如果两个操作数其中有一个是double类型，另一个操作就会转换为double类型。

否则，如果其中一个操作数是float类型，另一个将会转换为float类型。

否则，如果其中一个操作数是long类型，另一个会转换为long类型。

否则，两个操作数都转换为int类型。

### 7 局部变量和类中成员变量的默认值

类中的成员变量可以不用初始化，使用对应的默认值代替即可

**局部变量没有默认值** ，所以局部变量使用前 **必须** 初始化

```
public static void main(String[] args){
    String s;
    System.out.println("s="+s);
}
```
上述代码无法通过编译，因为使用了未经初始化的变量s

### 8 List、Set和Map有序问题

首先需要明确一点，什么是有序，指的是是否按照插入顺序来存储对象，而非集合内的元素是否排序

|集合|是否有序|是否允许重复|是否允许null|
|:-:|:-:|:-:|:-:|
|Collection|否|是|是|
|List|是|是|是|
|Set|HashSet(否)TreeSet(是)|否|是|
|Map|HashMap(否)TreeMap(是)|key不允许重复，value允许重复|是|