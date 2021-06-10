package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额)<= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/10 21:53
 **/
public class _518_CoinChangeII {
    public int change(int amount, int[] coins) {
        int n = coins.length;

        // 物品数量无限，完全背包问题
        // 定义dp[i][j]表示前i枚硬币放入容量为j的背包
        int[][] dp = new int[n + 1][amount + 1];
        // 初始值，0枚硬币放入容量为0的背包
        dp[0][0] = 1;

        // 遍历硬币
        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            // 遍历背包
            for (int j = 0; j <= amount; j++) {
                // 如果背包放不下当前硬币
                if (j < coin) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 能放下当前硬币，有两个选择
                    // 不放入当前硬币 dp[i][j] = dp[i-1][j]
                    // 放入当前硬币，则前i-1枚需要给背包至少空出[j-coin]的容量
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                }
            }
        }
        return dp[n][amount];
    }
}