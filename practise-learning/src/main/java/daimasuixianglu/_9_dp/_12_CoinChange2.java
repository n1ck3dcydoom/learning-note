package daimasuixianglu._9_dp;

public class _12_CoinChange2 {

    public int change(int amount, int[] coins) {
        // 动态规划

        int n = coins.length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示用前i个物品凑出面值为j的方案数
        int[][] dp = new int[n + 1][amount + 1];

        // 第二步、初始状态
        // 使用0个硬币凑出面值为0的方案数为1，即不选择任何硬币
        dp[0][0] = 1;
        // 第一行，使用0个硬币凑出面值为j的方案数，0种
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = 0;
        }
        // 第一列，使用i个硬币凑出面值为0的方案数为1，即不选择任何硬币
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // 第三步、状态转移
        // 考虑使用前i枚硬币凑出面值为j的方案数
        // 若不使用第i枚硬币，则有dp[i][j]=dp[i-1][j]
        // 若使用第i枚硬币，则有dp[i][j]=dp[i-1][j-w[i]]
        // 综上所述有dp[i][j]=dp[i-1][j]+dp[i-1][j-w[i]]

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            // 遍历背包
            for (int j = 1; j <= amount; j++) {
                // 完全背包问题，遍历每种物品的使用次数
                for (int k = 0; k * coin <= j; k++) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - k * coin];
                }
            }
        }
        return dp[n][amount];
        // 总结，对于完全背包问题来说
        // 如果求组合数，则先遍历物品，再遍历背包
        // 如果求排列数，则先遍历背包，再遍历物品

        // 例如有物品1，5两类
        // 如果求组合数则
        // for(物品：物品数)
        // for(背包：背包数)
        // 那么物品只遍历了一次，只有{1,5}这一种结果被选中

        // 如果求排列数
        // for(背包：背包数)
        // for(物品：物品数)
        // 可以看到每个背包容量，都遍历了物品1，5，那么最终结果将有{1,5} {5,1}两种结果产生
    }

    public int change1(int amount, int[] coins) {
        // 优化到一维
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 求组合数，先遍历物品
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            // 再遍历背包，从容量0开始
            for (int j = 0; j <= amount; j++) {
                if (j >= coin) {
                    dp[j] += dp[j - coin];
                }
            }
        }
        return dp[amount];
    }
}
