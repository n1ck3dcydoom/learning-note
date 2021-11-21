package algorithm.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2020/9/23 16:32
 **/
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib2(7));
    }

    /**
     * 递归
     */
    private static long fib0(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib0(n - 1) + fib0(n - 2);
    }

    /**
     * 带备忘录的递归
     */
    private static long fib1(int n) {
        if (n < 1) {
            return 0L;
        }
        long[] book = new long[n + 1];
        return fib1book(book, n);
    }

    private static long fib1book(long[] book, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 如果备忘录中已经计算过了，直接返回这个值，不再继续递归重复计算
        if (book[n] != 0) {
            return book[n];
        }
        // 带着备忘录递归计算
        book[n] = fib1book(book, n - 1) + fib1book(book, n - 2);
        return book[n];
    }

    /**
     * dp动态规划
     * O(n)的空间复杂度
     */
    private static long fib2(int n) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 优化一下空间使用率的dp
     * O(1)的空间复杂度
     */
    private static long fib3(int n) {
        long t1 = 1L;
        long t2 = 1L;
        long temp = 0L;
        if (n <= 2) {
            return 1L;
        }
        for (int i = 3; i <= n; i++) {
            temp = t1 + t2;
            t1 = t2;
            t2 = temp;
        }
        return temp;
    }
}