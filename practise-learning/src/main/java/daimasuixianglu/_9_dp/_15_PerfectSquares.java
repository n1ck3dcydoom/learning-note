package daimasuixianglu._9_dp;

public class _15_PerfectSquares {

    public int numSquares(int n) {
        // 给定前i个完全平方数，切好装满容量为n的背包，每个完全平方数可以重复使用
        // 完全背包问题

        // 第一步、定义dp数组
        // 定义dp[j]表示使用前i个完全平方数恰好装满容量为j的背包所需要的最小完全平方数的个数
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 没有任何完全平方数能够凑出0
        dp[0] = 0;

        // 第三步、状态转移
        // 考虑恰好装满背包容量为j的情况
        // 若使用第i个完全平方数dp[j]=dp[j-i*i]+1
        // 若使用第i-1个完全平方数dp[j]=dp[j-(i-1)*(i-1)]+1
        // 在每个背包容量下，需要遍历所有完全平方数才能得到最小的结构
        // 即dp[j]=min(dp[j],dp[j-i*i]+1)，由于需要求最小值，如果dp数组默认使用0做初始化，每次min的结果都会是0
        // 所以dp数组需要用大数初始化
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        // 需要遍历背包的时候遍历所有物品
        // 先遍历背包
        for (int i = 1; i <= n; i++) {
            // 遍历所有完全平方数
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - j * j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
        }
        return dp[n];
    }
}
