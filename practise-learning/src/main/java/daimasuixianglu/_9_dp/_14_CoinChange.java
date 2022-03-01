package daimasuixianglu._9_dp;

public class _14_CoinChange {

    public int coinChange(int[] coins, int amount) {
        // 完全背包问题
        // 有i种面值不相同的硬币，每种硬币有无限个，问凑出面值j的最小硬币数

        int n = coins.length;
        // 第一步、定义dp数组
        // 定义dp[j]表示凑出面值j的最小硬币数
        int[] dp = new int[amount + 1];

        // 第二步、初始值
        // 使用0种硬币凑出面值为0所需要的最小硬币数为0
        dp[0] = 0;

        // 第三步、状态转移
        // 考虑面值为j的情况dp[j]
        // 如果最后一枚硬币是coins[i]，那么dp[j]=dp[j-coins[i]]+1
        // 如果最后一枚硬币是coins[i-1]，那么dp[j]=dp[j-coins[i-1]]+1
        // 以此类推，求min(dp[j-coins[0~i]])+1的最小值
        // 可以看到求最小值如果dp数组使用0作为默认值初始化，而所有的dp结果都来自于dp[0]=0
        // 那么min求出来的最后结果也全都是0，所以需要把dp数组除了第0项以外的全部初始化为较大的值
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 完全背包问题，内层循环正序遍历
        // 选取硬币顺序无关，求组合数
        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i - 1] && dp[j - coins[i - 1]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
