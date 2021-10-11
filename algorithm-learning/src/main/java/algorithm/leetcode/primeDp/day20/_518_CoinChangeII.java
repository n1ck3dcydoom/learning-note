package algorithm.leetcode.primeDp.day20;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 1:12
 **/
public class _518_CoinChangeII {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(change(5, coins));
    }

    public static int change(int amount, int[] coins) {
        // 完全背包问题
        int n = coins.length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示前个i个物品装满容量为j的背包需要的物品个数
        int[][] dp = new int[n + 1][amount + 1];

        // 第二步、初始值
        dp[0][0] = 1;

        // 第三步、状态转移
        // 考虑前i个物品，背包容量为j时，dp[i][j]，两个选择使用物品i和不使用物品i
        // 不使用物品i：dp[i][j]=dp[i-1][j]
        // 使用物品i：
        // 枚举物品i的使用次数，物品i使用k次装满，当j-kw>=0
        // dp[i][j] =  dp[i-1][j-w] + dp[i-1][j-2w] + ... + dp[i-1][j-kw])

        // 枚举物品
        for (int i = 1; i <= n; i++) {
            // 枚举背包
            for (int j = 0; j <= amount; j++) {
                // 不使用物品i
                dp[i][j] = dp[i - 1][j];
                // 使用物品i，枚举使用次数
                for (int k = 1; k * coins[i - 1] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }
}