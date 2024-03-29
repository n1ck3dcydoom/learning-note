## 继承和组合

### 少用继承,多用组合

考虑设计一个关于"鸟"的类,定义一个抽象类 `AbstractBire` 所有的鸟直接继承这个抽象类

在抽象类里定义一个抽象方法 `fly()` 那么所有的鸟都通过继承得到了 **飞行** 的能力

但事实并非如此,例如鸵鸟就不会飞

那么鸵鸟类应该怎么处理从父类继承得到的 `fly()` 方法呢,直接重写抛出异常不能解决所有问题

例如还有其他的鸟也不会飞,那么所有不会飞的鸟都将重写 `fly()` 方法抛出异常,这大大增加了工作量

同时也破坏了 **迪米特法则(也叫最少知识原则)** :暴露不应该暴露的接口给外部,增加了外部使用过程中误用的概率

那如果设计两个子类,分别是会飞的鸟 `FlyableBird` 和不会飞的鸟 `UnFlyableBire` 目前为止看起来是解决了这个问题

但是鸟不仅仅会飞,还会跑,还会下蛋; 这其中每种能力又可以互相组合,那么子类的数量将呈指数级上升

### 通过组合,接口,委托来解决继承带来的子类爆炸增长的问题

设计若干个接口,`Flyable`, `Runable`, `Eggable`

那么会飞的鸟就会实现 `Flyable` 接口,会跑的鸟就会实现 `Runable` 接口,既会飞还会跑又会下蛋的鸟就需要实现三个接口

这带来了新问题,就是每种继承了接口的鸟,都需要把接口的方法重新实现一遍;一百种会飞的鸟就要重写一百遍 `fly()` 方法

可以实现一个父类 `Flybility`, `Runbility` 继承对应的接口,在类中实现对应的方法

然后不同的鸟类通过持有对应的实例,将自己的行为委托给对应的实例去执行

```java
public class Chicken implements Runable, Eggalbe { // 接口
    private Runbility runbility = new Runbility(); // 组合
    private Eggbility eggbility = new Eggbility(); // 组合

    @Override
    public void run() {
        runbility.run(); // 委托
    }

    @Override
    public void egg() {
        eggbility.egg(); // 委托
    }
}
```

继承描述的是 `is-a` 关系,组合描述的是 `has-a` 关系

Q:什么时机选择继承,什么时机选择组合?

A:如果继承关系稳定,而且继承深度有限且不复杂. 那么可以大胆地使用继承

如果继承关系不稳定,或者继承深度复杂,就尽可能地使用组合来解决继承带来的问题

