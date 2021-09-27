package decorator.wrapper;

import decorator.foo.Foo;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/27 23:09
 **/
public class AfooWrapper extends FooWrapper {
    public AfooWrapper(Foo afoo) {
        super(afoo);
    }

    @Override
    public void foo() {
        foo.foo();
        this.goo();
    }

    /**
     * 具体的包装类还根据自己的需求，扩展了其他功能
     */
    private void goo() {
        System.out.println("A goo");
    }
}