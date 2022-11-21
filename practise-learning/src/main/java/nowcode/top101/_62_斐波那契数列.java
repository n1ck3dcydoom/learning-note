package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 15:34
 * Description:
 */

public class _62_斐波那契数列 {

    public static void main(String[] args) {
        System.out.println(Fibonacci(4));
    }

    public static int Fibonacci(int n) {
        // 我他妈直接动态规划

        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        // 直接使用两个滚动元素
        int p = 1;
        int pp = 1;
        for (int i = 3; i <= n; i++) {
            int t = p + pp;
            p = pp;
            pp = t;
        }

        return pp;
    }
}
