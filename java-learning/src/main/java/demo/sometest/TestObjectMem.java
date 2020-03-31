package demo.sometest;

/**
 * Created by n!Ck
 * Date: 2019-02-28
 * Time: 13:33
 * Description:
 * 测试一下java参数传递的内存模型
 */
public class TestObjectMem {
    public static void main(String[] args) {
        new TestObjectMem().ref1();
    }

    private void ref1() {
        int i = 2;
        Val v = new Val();
        v.i = 3;
        ref2(v, i);
        System.out.println(v.i);
    }

    private void ref2(Val v, int i) {
        v.i = 4;
        i = 5;
        Val val = new Val();
        v = val;
        System.out.print(v.i + "," + i + ",");
    }

    public static class Val {
        int i = 1;
    }
}
