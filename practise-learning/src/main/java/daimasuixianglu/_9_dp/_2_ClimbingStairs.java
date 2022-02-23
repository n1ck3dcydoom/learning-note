package daimasuixianglu._9_dp;

public class _2_ClimbingStairs {

    public int climbStairs(int n) {
        // 动态规划

        // 第一步、定义dp数组
        // 定义dp[i]表示到达第i层阶梯不同的方法种数
        int[] dp = new int[n + 1];

        // 第二步、初始状态
        dp[0] = 0;
        dp[1] = 1;

        // 第三步、状态转移
        // 考虑第i阶台阶，可以通过i-1阶跨1步得到，也可以通过i-2阶跨2步得到
        // 即dp[i]=dp[i-1]+dp[i-1]
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
