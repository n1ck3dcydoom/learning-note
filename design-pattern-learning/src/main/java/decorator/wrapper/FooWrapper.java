package decorator.wrapper;

import decorator.foo.Foo;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 抽象的foo接口的包装类
 * @date 2021/9/27 23:05
 **/
public abstract class FooWrapper implements Foo {
    /**
     * 抽象包装类持有包装类的接口引用，需要用具体实现类初始化这个变量
     */
    protected Foo foo;

    /**
     * 其有参构造函数通过传入具体被包装实现类来初始化内部的引用
     *
     * @param foo certain foo class
     */
    FooWrapper(Foo foo) {
        this.foo = foo;
    }

    /**
     * 抽象包装类的方法调用本质上是调用内部的被包装类foo的实现类的具体方法
     */
    @Override
    public void foo() {
        this.foo.foo();
    }
}