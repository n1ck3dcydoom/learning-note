### WeakRefrence

在Java里， 当一个对象obj被创建时， 它被放在Heap（堆）里。
 
当GC运行的时候， 如果发现没有任何引用指向obj， obj就会被回收。
 
或者换句话说， 一个对象被回收， 必须满足两个条件: 

1. 没有任何引用指向它 

2. GC被运行
在现实情况写代码的时候， 我们往往通过把所有指向某个对象的referece置空来保证这个对象在下次GC运行的时候被回收 (可以用java -verbose:gc来观察gc的行为)

```
Object  = new Car();
c = null;
```

但是，手动置空对象对于程序员来说，是一件繁琐且违背自动回收的理念的。

对于简单的情况，手动置空是不需要程序员来做的。

因为在java中，对于简单对象，当调用它的方法执行完毕后，指向它的引用会被从stack中pop出来，所以他就能在下一次GC执行时被回收了。

但是，也有特殊例外。

当使用cache的时候，由于cache的对象正是程序运行需要的，那么只要程序正在运行，cache中的引用就不会被GC给(或者说，cache中的reference拥有了和主程序一样的life cycle)。

那么随着cache中的reference越来越多，GC无法回收的object也越来越多，无法被自动回收。

当这些object需要被回收时， 回收这些object的任务只有交给程序编写者了。

然而这却违背了GC的本质(自动回收可以回收的objects)。

所以，java中引入了weak reference (相对于前面举例中的strong reference)
```
//只要c还指向car object, car object就不会被回收
Object c = new Car(); 
```
**注：什么是强引用：简单来说就是 A a = new A() 这种形式的声明**

未完待续....

[Java弱引用(WeakReference)的理解与使用](https://blog.csdn.net/zmx729618/article/details/54093532)

[弱引用和软引用WeakReference,SoftReference,最简讲解，以及一个应用场景](https://blog.csdn.net/qq_36523667/article/details/78549874)

