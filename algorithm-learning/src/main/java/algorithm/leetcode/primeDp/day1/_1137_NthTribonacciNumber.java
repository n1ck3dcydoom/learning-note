package algorithm.leetcode.primeDp.day1;

public class _1137_NthTribonacciNumber {

    public static void main(String[] args) {
        System.out.println(tribonacci(25));
    }

    public static int tribonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        // 第一步，定义dp数组
        // 定义dp[n]表示第n个泰波那契数的值
        // n+1额外保存0的位置
        int[] dp = new int[n + 1];

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        // 第三步、递推表达式
        // T(n) = T(n-1) + T(n-2) + T(n-3) , n>2
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}