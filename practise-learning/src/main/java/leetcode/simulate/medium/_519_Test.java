package leetcode.simulate.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2021/11/27 15:17
 **/
public class _519_Test {

    public static void main(String[] args) {
        _519_RandomFlipMatrix test = new _519_RandomFlipMatrix(3, 4);
        int[] res = test.flip();
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        res = test.flip();
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        res = test.flip();
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        test.reset();

        res = test.flip();
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
}
