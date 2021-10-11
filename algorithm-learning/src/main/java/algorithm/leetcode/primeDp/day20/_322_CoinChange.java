package algorithm.leetcode.primeDp.day20;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 0:42
 **/
public class _322_CoinChange {

    public static void main(String[] args) {
        int[] coins = new int[]{1};
        System.out.println(coinChange(coins, 2));
    }

    public static int coinChange(int[] coins, int amount) {
        // 背包容量为v，物品i的花费为coins[i]，价值为coins[i]，个数无限，完全背包问题

        int n = coins.length;
        if (amount == 0) {
            return 0;
        }
        // 第一步、定义dp数组
        // 定义dp[i]表示装满容量为j的背包的不同方法
        int[] dp = new int[amount + 1];

        // 第二步、初始值
        // 没法填满容量为0的背包
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }


        // 第三步、状态转移
        // 当背包容量为i的时候，由前一个状态放入1枚硬币得到容量i，需要遍历所有硬币
        // dp[i] = min(dp[i-coins[1]], dp[i-coins[2]], ... , dp[i-coins[n]]) + 1

        // 遍历背包
        for (int i = 1; i <= amount; i++) {
            // 遍历物品
            for (int j = 0; j < n; j++) {
                // 需要考虑第j个物品当前背包能否装下
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}