package demo.testInstanceof;

/**
 * Created by n!Ck
 * Date: 2019-01-11
 * Time: 20:31
 * Description:
 */
public class TestInstanceof {
    public static void main(String[] args) {
        ClassA obj = new ClassD();
        System.out.println(obj instanceof ClassA);
        System.out.println(obj instanceof ClassB);
        System.out.println(obj instanceof ClassC);
        System.out.println(obj instanceof ClassD);
    }
}
