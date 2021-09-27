package decorator;

import decorator.foo.AfooImpl;
import decorator.foo.BfooImpl;
import decorator.foo.Foo;
import decorator.wrapper.AfooWrapper;
import decorator.wrapper.BfooWrapper;
import decorator.wrapper.FooWrapper;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/27 23:12
 **/
public class Client {

    public static void main(String[] args) {
        // 外部在使用装饰器模式时，持有的是抽象的包装类对象FooWrapper
        Foo afoo = new AfooImpl();
        Foo bfoo = new BfooImpl();

        FooWrapper afooWrapper = new AfooWrapper(afoo);
        FooWrapper bfooWrapper = new BfooWrapper(bfoo);

        // 直接使用未被包装的foo对象
        afoo.foo();
        bfoo.foo();

        // 使用包装对象扩展agoo和bgoo能力
        afooWrapper.foo();
        bfooWrapper.foo();
    }
}