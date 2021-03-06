package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 * <p>
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 * <p>
 * 输入：stones = [1,2]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/8 18:57
 **/
public class _1049_LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {

        // 把所有石头分为两堆石头，其中一堆尽可能贴近sum/2，那么另一堆也尽可能接近sum/2
        // 这样做差后能得到的差值最小
        // 转化为从若干个石头中选出i个石头刚好填满容量大小为sum/2的背包

        // 先求和
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }

        int n = stones.length;
        int v = sum / 2;
        // 定义dp[i][j]表示，前i个石头刚好能填满容量大小为j的背包，所能取得的最大价值（即石头的重量总和）
        int[][] dp = new int[n + 1][v + 1];

        // 初始值，没有石头放入容量为0的背包中，价值（重量）自然为0
        dp[0][0] = 0;

        // 递推表达式
        // 考虑dp[i][j]
        // 如果当前石头的重量已经超过背包容量，则肯定放不进去，当前状态由上一个物品转移得到
        // if(stones[i]>j) dp[i][j]dp[i][j]=dp[i-1][j]
        // 如果当前石头能够放入背包，考虑两个选择，放入或者不放入
        // 如果放入背包，则背包至少要剩余当前物品重量的容量
        // dp[i][j] = dp[i-1][j-stones[i]] + w
        // 如果不放入背包，则由上一个物品转移得到
        // dp[i][j] = dp[i-1][j]

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            int w = stones[i - 1];
            // 遍历背包容量
            for (int j = 1; j <= v; j++) {
                // 物品已经超过背包容量
                if (w > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 两个选择，放入或者不放入，取两者的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + w);
                }
            }
        }
        // dp数组里面保存的是，取出i个物品放入容量大小为sum/2的背包
        // 题目求得是两堆石头做差后的最小结果
        // 所以返回的是v - dp[n][v]

        // v表示sum/2，dp[n][v]表示容量为sum/2背包能得到的最大价值
        // 差值即为两队石头的最小值
        return v - dp[n][v];
    }
}