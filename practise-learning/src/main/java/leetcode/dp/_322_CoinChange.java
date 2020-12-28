package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/28 23:52
 **/
public class _322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        // 定义dp[i]表示凑出面值为i的最小硬币数
        // +1为了dp数组的下标保持和amount一直
        int[] dp = new int[amount + 1];
        // 初始状态，凑出面值0所需要的最少硬币书是0枚硬币
        // 因为dp[amount]永远凑不出amount+1
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        // 选择（递推关系式）
        // 每次需要做出选择一枚硬币，或者不选择硬币
        //          0 , n=0
        // dp[n]=   -1, n<0
        //          min(dp[n-coin]+1 , coin ∈ coins) , n>0
        // 例如 coins=[1,2,5], amount=11
        //                  dp[11-1]+1(选择面值为1的硬币，需要上一轮凑出面值为10的最小次数+1)
        // 例如dp[11]=min{  +dp[11-2]+1(选择面值为2的硬币，需要上一轮凑出面值为9的最小次数+1) }
        //                  +dp[11-5]+1(选择面值为5的硬币，需要上一轮凑出面值为6的最小次数+1)
        for (int i = 0; i <= amount; i++) {
            // 选择硬币
            for (int coin : coins) {
                // i-coin>=0 表示当前i的面值下，选择了coin的硬币后，仍然还可以继续做出下一轮选择
                // 如果i-coin<= 表示当前硬币已经超出了凑出i所需要的最小面值了
                if (i - coin >= 0) {
                    // 这里的遍历其实已经是上述dp[11]的右侧求解所有子dp的过程了
                    // 每次遍历保存选择当前硬币后的最小次数为temp dp[i]
                    // 遍历完所有coins后，temp dp[i]自然也就是最小的dp[i]了
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        // 返回对应amount下需要的dp值
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}