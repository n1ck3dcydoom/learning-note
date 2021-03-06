### 方法所属的类

`sleep()` 方法是线程 `Thread` 的，`wait()` 和 `notify()` 和 `notifyAll()` 方法都是 `Object` 类的

### 对象池和锁池

1. 对象池：等待被唤醒的池子

2. 锁池：等待竞争锁的池子

### 关于是否释放对象锁问题

* `sleep()` 方法 **不会** 释放对象锁，而且是谁调用谁“睡觉”，例如：
```
即使在a线程里调用b的sleep()方法，也是a去睡觉

如果想要b去睡觉，则必须让b调用sleep()方法
```

所以 `sleep()` 方法 **不会** 让出CPU资源

* `wait()` 方法 **会** 释放掉对象锁（因为 `wait()` 方法只能出现在 `synchronized` 代码块中），也就是说调用了哪个对象的 `wait()` 方法，该线程就会释放掉那个对象的锁，然后就会进入 **对象池** 等待其他线程来唤醒这个线程。

### 总结

`wait()` 对应等待池，`synchronized` 对应锁池。

加了 `synchronized` 关键字的地方就会有相应的获取锁的步骤。未竞争到锁的线程会加入到锁池。

从线程的状态上来说：

1. 调用 `wait()` 后的线程处于 `休眠` 状态，线程 **会** 释放掉对象锁，进入对象池，等待其他线程唤醒。

2. 调用 `sleep()` 后的线程处于 `暂停` 状态，此时仍然是运行中的，只是 `暂停运行` 一会，所以 **不会** 释放掉对象锁，等睡眠时间过后即可继续执行

从池子来说：

1. 对象池：是用来存放那些处于休眠状态的线程，它们等待某一个时机被唤醒然后加入锁池。

2. 锁池：是用来存放那些已经处于活跃状态，并且都在等待去竞争对象锁的线程。