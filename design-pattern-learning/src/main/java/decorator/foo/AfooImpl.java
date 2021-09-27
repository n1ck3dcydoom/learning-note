package decorator.foo;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 具体被包装的对象Bfoo（实现了规范其行为的接口）
 * @date 2021/9/27 23:03
 **/
public class AfooImpl implements Foo {
    @Override
    public void foo() {
        System.out.println("A foo");
    }
}