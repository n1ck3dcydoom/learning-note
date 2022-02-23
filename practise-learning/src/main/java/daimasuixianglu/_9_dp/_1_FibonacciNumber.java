package daimasuixianglu._9_dp;

public class _1_FibonacciNumber {
    
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        // 动态规划
        // 第一步、定义dp数组
        // 定义dp[i]表示第i个斐波那契数
        int[] dp = new int[n+1];

        // 第二步、初始状态
        dp[0] = 0;
        dp[1] = 1;

        // 第三步
        // 状态转移
        // 考虑第i个数，有F(i)=F(i-1)+F(i-2)
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
