package algorithm.recursion;

import utils.TimeUtil;

/**
 * @author zhanglei
 * @version 1.0
 * @description 求n的阶乘
 * @date 2020/5/12 19:39
 **/
public class Factorial {
    public static void main(String[] args) {
        int n = 6;
        long start1 = TimeUtil.now();
        System.out.println(factorial2(n));
        long end1 = TimeUtil.now();
        System.out.println("Factorial " + n + "! cost time " + (end1 - start1) + " ms");
    }

    /**
     * 递归的三要素第一点：明确递归函数要做什么
     * 这里定义了一个求解n的阶乘的函数，即明确了函数的功能
     */
    private static long factorial0(int n) {
        return 0L;
    }

    /**
     * 递归的三要素第二点：寻找递归结束的条件
     * 求解n的阶乘，如果已经知道了F(n-1)的值
     * 是不是可以很简单的求解出n的阶乘：F(n) = F(n-1) * n
     * <p>
     * 那么n-1的阶乘如何求解呢，是不是可以继续推导出：
     * F(n-1) = F(n-2) * (n - 1)
     * <p>
     * 那么当求解2的阶乘是 F(2) = F(1) * 2
     * 1的阶乘是 F(1) = F(0) * 1
     * 0的阶乘是我们定义的 0! = 1  即 F(0) = 1
     * 所以 1 和 2 的阶乘很简单的就能得出
     * F(1) = 1   F(2) = 2
     * <p>
     * 那么F(3)呢？ F(3) = F(2) * 3 = 2 * 3 = 6
     * 明显我们可以看到：
     * 当 n <= 2的时候
     * F(n) = n
     * <p>
     * 当 n > 2的时候
     * F(n) = F(n-1) * n
     * <p>
     * 综上所述可以看出递归的结束条件就是当 n <= 2 的时候，直接return n 就行了
     */
    private static long factorial1(int n) {
        // 慢慢地往框架里面添加代码
        // 现在我们已经得出结束递归的条件了
        if (n <= 2) {
            return n;
        }
        return 0L;
    }

    /**
     * 在上述的推导过程中，我们已经得出了递归的第三个要素：条件表达式
     * F(n) = F(n-1) * n
     * <p>
     * 即求n的阶乘，我们只需要这个函数返回 n-1的阶乘 * n
     */
    private static long factorial2(int n) {
        if (n <= 2) {
            return n;
        }
        return factorial2(n - 1) * n;
    }
}