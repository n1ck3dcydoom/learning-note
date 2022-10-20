## 接口和抽象类的区别

一次性彻底弄清楚接口和抽象类的区别.

### 抽象类

```java
public abstract class Logger {

    private String loggerName;

    public void log(String msg) {
        // do something other before do log
        doLog(msg);
    }

    public abstract void doLog(String msg);
}

public class InfoLogger extends Logger {
    @Override
    public void doLog(String msg) {
        // print info log msg
    }
}
```

1. 抽象类无法被实例化将.
2. 抽象类中可以有成员变量,也有实现了的方法 `log()` ,还可以有只有定义而无实现的方法 `doLog()`.
3. 抽象类的子类必须实现抽象父类的所有抽象方法,或者子类也定义为抽象类.

### 接口

```java
public interface IFilter {
    void doFilter(Request req) throws FilterException;
}

public class MessageFilter implements IFilter {
    @Override
    public void doFilter(Request req) throws FilterException {
        // do something with req message
    }
}

public class PictureFilter implements IFilter {
    @Override
    public void doFilter(Request req) throws FilterException {
        // do something with req picture
    }
}
```

1. 接口不包含成员变量,仅有方法的定义.
2. 接口只能定义方法,不能实现方法(JDK8 引入了 default 关键字,让接口方法可以有默认实现).
3. 类实现接口时,必须实现接口里面定义的所有方法,不允许只实现一部分

### 接口和抽象类的对比

简单来说,抽象类描述的是一种 `is-a` 的关系,明确的表示了实现类是一种接口类的具体类型.

而接口则描述了一种 `has-a` 的关系,说明实现类含有接口所定义的所有行为,具备某种能力.

飞机是一种抽象的事物,直升机也是飞机,战斗机也是飞机,这是典型的 `is-a` 关系

飞是一种行为,一种能力,飞机能够飞,鸟也能飞,这是典型的 `has-a` 关系.

对于接口,有一种更为形象的说法,那就是 **协议**.

### 接口和抽象类能够解决什么问题

先说抽象类:

最直接的感受就是,抽象类可以定义一些公共的方法,并且这些方法可以被其实现类复用.

Q:普通的继承也可以达到方法复用的效果,为什么还需要抽象类?

A:普通的继承确实可以复用,但因此丧失了多态的特性.

再说接口:

如果说抽象类是为了提高代码的复用率,那么接口就完全是为了 **解耦** .

使用方只需要关注接口所定义的方法的作用,而完全无需关注接口的内部实现.

### 总结

什么时候使用抽象类,什么时候使用接口:

1. 如果需要表达 `is-a` 的关系,就使用抽象类.
2. 如果需要表达 `has-a` 的关系,就使用接口.
3. 一般是先有多个已经实现的子类,将其中共同的公共方法抽象到抽象类里,是一种自下而上的设计.
4. 而接口则是先定义接口方法,明确接口提供的行为和能力,再定义实现类,是一种自上而下的设计.