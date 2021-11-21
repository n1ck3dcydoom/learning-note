package algorithm.recursion;

import utils.TimeUtil;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 求斐波那契数列
 * @date 2020/5/12 20:08
 **/
public class Fibonacci {
    public static void main(String[] args) {
        int n = 40;
        long start1 = TimeUtil.now();
        System.out.println(fibonacci2(n));
        long end1 = TimeUtil.now();
        System.out.println("Factorial cost time " + (end1 - start1) + " ms");

        long start2 = TimeUtil.now();
        System.out.println(fibonacci3(n));
        long end2 = TimeUtil.now();
        System.out.println("Circulate cost time " + (end2 - start2) + " ms");
        // 当n = 40的时候，已经能看出明显的耗时差距了
        // 165580141
        // Factorial cost time 249 ms
        // 165580141
        // Circulate cost time 0 ms
    }

    /**
     * 第一步：明确这个函数是干嘛的
     * 求解斐波那契数列的第n项
     */
    private static long fibonacci0(int n) {
        return 0L;
    }

    /**
     * 第二步：寻找结束递归的条件
     * F(0) = F(1) = 1 , F(2) = F(1) + F(0) = 2 , F(3) = F(2) + F(1) = 3
     * F(4) = F(3) + F(2) = 5 ,,,,,, F(n) = F(n-1) + F(n-2)
     * 可以看到当 n <= 3的时候 F(n) = n
     */
    private static long fibonacci1(int n) {
        if (n <= 3) {
            return n;
        }
        return 0L;
    }

    /**
     * 第三步：求解条件表达式
     * 从第二步可以得出递归的条件表达式：
     * 求第n项，需要知道第n-1和第n-2项的值，那么可以直接return F(n-1) + F(n-2)
     */
    private static long fibonacci2(int n) {
        if (n <= 3) {
            return n;
        }
        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }

    /**
     * 递归明显存在重复计算问题，例如求第5项，可以画出下面这颗树
     *                      F(5)
     *                   /       \
     *                 F(4)      F(3)
     *                /   \     /    \
     *              F(3) F(2) F(2)   F(1)
     *             /   \
     *           F(2)  F(1)
     * 其中F(3)被计算了两遍，所以我们可以考虑通过缓存每次的中间计算结果到数组里面
     */
    private static long fibonacci3(int n) {
        // 数组第0项不使用
        int[] cache = new int[n + 1];
        cache[1] = 1;
        cache[2] = 2;
        cache[3] = 3;

        for (int i = 4; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }
}