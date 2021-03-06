package leetcode.dp.hard;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * <p>
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * <p>
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * <p>
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 * <p>
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/profitable-schemes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/9 10:20
 **/
public class _879_ProfitableSchemes {
    //
    //1
    //        1
    //        [2,2,2,2,2,2,1,2,1,1,2,1,2,2,2,1,2,1,1,2,1,2,1,2,2,2,2,1,1,2,2,2,1,1,2,1,2,2,2,1,2,2,2,2,1,2,2,1,2,2,1,1,1,1,1,1,2,2,2,2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,2,2,1,1,2,2,2,1,1,2,2,1,1,2,2,1,2,2,1,1,2,2,2,2,2]
    //        [2,1,2,2,2,1,0,1,2,0,0,2,2,1,1,1,2,0,1,1,2,0,2,2,1,0,1,0,1,2,2,1,1,2,0,2,1,1,1,1,1,2,0,1,0,2,2,2,2,2,0,1,1,2,1,0,1,0,0,2,1,0,2,0,2,1,1,1,0,1,0,1,2,2,0,1,1,2,2,0,1,0,0,1,1,2,2,2,2,1,0,0,1,2,1,1,1,1,0,1]
    public static void main(String[] args) {
        int n = 1;
        int minProfit = 1;
        int[] group = new int[]{2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 2, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2};
        int[] profit = new int[]{2, 1, 2, 2, 2, 1, 0, 1, 2, 0, 0, 2, 2, 1, 1, 1, 2, 0, 1, 1, 2, 0, 2, 2, 1, 0, 1, 0, 1, 2, 2, 1, 1, 2, 0, 2, 1, 1, 1, 1, 1, 2, 0, 1, 0, 2, 2, 2, 2, 2, 0, 1, 1, 2, 1, 0, 1, 0, 0, 2, 1, 0, 2, 0, 2, 1, 1, 1, 0, 1, 0, 1, 2, 2, 0, 1, 1, 2, 2, 0, 1, 0, 0, 1, 1, 2, 2, 2, 2, 1, 0, 0, 1, 2, 1, 1, 1, 1, 0, 1};
        System.out.println(profitableSchemes(n, minProfit, group, profit));
    }

    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // 考虑简单01背包问题，从i个物品放入容量为j的背包，得到的最大价值为dp[i][j]

        // 非常类似背包问题，只不过01背包问题只有一个限制条件，即物品的重量
        // 这里有两个限制条件，即工作的人数和产生的最小利润

        int work = group.length;
        // 定义dp[i][j][k]表示，完成i份工作，至少需要j个人，能够产生最少利润为k的组合数
        long[][][] dp = new long[work + 1][n + 1][minProfit + 1];

        // 初始值
        // 完成0份工作，需要0个人，产生的最小利润为0
        // 只有一个组合
        // dp[0][0][0] = 1;
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }

        // 递推表达式
        // 01背包问题分析每一个物品
        // 这里分析每一份工作
        // 考虑第i份工作需要j个人，完成k的利润，有两个选择，选择做或者选择不做
        // 如果不能完成第i份工作，人数不够
        // if(j<group[i])  dp[i][j][k] = dp[i-1][j][k]
        // 如果能够完成第i份工作，则第i-1份工作是少需要腾出j-group[i]个人，且第i-1份工作至少需要产生k-profit[i]的利润
        // 总数就是上一份工作的组合数+选择当前规则后的组合数
        // dp[i][j][k] = dp[i-1][j][k] + d[i-1][j-group[i]][k-profit[i]]

        // 10 ^ 9 + 7  表示10异或9然后+7，求次方需要使用Math.pow(10,9)
        int mod = (int) Math.pow(10, 9) + 7;
        // 遍历工作
        for (int i = 1; i <= work; i++) {
            // 当前工作需要的人数
            int worker = group[i - 1];
            // 当前工作能够产生的利润
            int value = profit[i - 1];
            // 遍历人数，从没有人开始
            for (int j = 0; j <= n; j++) {
                // 遍历利润，从没有利润开始
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= worker) {
                        // 这里k-value可能会是负数，即需要得到最少k的利润，但是当前工作能提供大于k的利润
                        // 那么k-value就小于0了
                        // 考虑dp[i][j][k]的定义，为前i份工作使用j个人得到k的利润
                        // 如果k是一个负数，跟得到一个利润为0的效果是一样的，都没有利润
                        // 所以这里把k-value小于0的情况等价为dp[i][j][0]
                        int minK = Math.max(0, k - value);
                        //                        dp[i][j][k] += dp[i - 1][j - worker][minK];
                        //                        dp[i][j][k] %= mod;
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - worker][minK]) % mod;
                    }
                }
            }
        }
        return (int) dp[work][n][minProfit];
    }
}