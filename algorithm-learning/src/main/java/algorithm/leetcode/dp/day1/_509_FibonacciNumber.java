package algorithm.leetcode.dp.day1;

public class _509_FibonacciNumber {
    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(10));

        System.out.println(fibDfs(2));
        System.out.println(fibDfs(3));
        System.out.println(fibDfs(4));
        System.out.println(fibDfs(10));
    }

    public static int fibDfs(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibDfs(n - 1) + fibDfs(n - 2);
    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        // 第一步，设计dp数组
        // 定义dp[n]表示第n个斐波那契数
        // n+1保存额外的0
        int[] dp = new int[n + 1];

        // 第二步，初始值
        dp[0] = 0;
        dp[1] = 1;

        // 第三步，递推表达式
        // F(i) = F(i-1) + F(i-2) ，其中i>1
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}