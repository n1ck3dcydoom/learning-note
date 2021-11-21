package leetcode.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/29 19:28
 **/
public class _416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        // 转换为01背包问题，即从数组中取出若干个数，使之和恰好等于数组和的一半
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果数组的和为奇数，除以二后有小数点，这个时候永远无法满足恰好装满一半
        if (sum % 2 != 0) {
            return false;
        }
        // 背包容量等于数组和的一半
        int v = sum / 2;

        // 背包问题的基础递推表达式：
        // F[i, v] 表示前 i 件物品恰放入一个容量为 v 的背包可以获得的最大价值。Wi表示物品i的价值，则其状态转移方程便是：
        // F[i, v] = max{F[i − 1, v], F[i − 1, v − Ci] + Wi}

        // 1、定义dp数组的含义
        // 定义dp[i][j] = true 表示，将前i个物品刚好可以放入容量为j的背包，本题中j=sum/2
        int n = nums.length;
        // 为了和第1个物品对应起来，dp数组的物品维度长度+1
        boolean[][] dp = new boolean[n + 1][v + 1];

        // 2、初始化，定义base case
        // 考虑如果背包容量为0，则前i个数只要都不取即可满足，即dp[i][0]=true
        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }
        // 考虑如果没有物品，即dp[0]，则只要背包容量大于0，则都不可能满足，即dp[0][1~i]=false
        for (int i = 1; i <= v; i++) {
            dp[0][i] = false;
        }

        // 3、选择，即状态转移方程
        // 考虑第i个物品，两种选择：放入 或者 不放入
        // 3.1 如果不放入第i个物品，则考虑dp[i-1][j]
        // 则若前i-1个物品能够满足容量为j的背包，那么不放入物品i，也能满足j的背包  即 dp[i][j] = (dp[i-1][j] = false)
        // 若前i-1个物品不能满足容量为j的背包，那么不放入物品i，也不能满足j的背包  即 dp[i][j] = (dp[i-1][j] = true)
        // ********** 即 dp[i][j] = dp[i-1][j]
        // 3.2 如果放入第i个物品，则考虑dp[i-1][j-nums[i-1]]
        // nums[i]为物品的重量数组，数组的下标从0开始算，所以物品i的重量为nums[i-1]
        // j-nums[i-1]的含义：
        // 若前i-1个物品能够满足容量为j-nums[i-1]的背包，那么放入重量为nums[i-1]的物品i后，也能够满足容量为j的背包
        // 即dp[i][j] = (dp[i][j-nums[i-1]] = true)
        // 若前i-1个物品不能够满足容量为j-nums[i-1]的背包，那么放入重量为nums[i-1]的物品i后，也不能满足容量为j的背包
        // 即dp[i][j] = (dp[i][j-nums[i-1]] = false)
        // ********** 即 dp[i][j] = dp[i-1][j-nums[i-1]]
        //
        // 综上所述：
        //            {  不放入第i个物品  dp[i][j] = dp[i-1][j]
        // dp[i][j] = {  放入第i个物品    dp[i][j] = dp[i-1][j-nums[i-1]]

        // 遍历物品数组
        for (int i = 1; i <= n; i++) {
            // 遍历背包容量
            for (int j = 1; j <= v; j++) {
                // nums[i-1]才是第i个物品的实际重量，下标需要减1
                // j为当前背包的容量，如果当前物品已经放不下了，即不放入第i个物品
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 如果能够放入第i个物品，则考虑需不需要放入
                else {
                    // 不放入第i个物品  dp[i][j] = dp[i-1][j]
                    // 如果放入        dp[i][j] = dp[i-1][j-nums[i-1]]
                    // 上述两个分支，有一个为true，则dp[i][j] = true，如果两个都为false，则dp[i][j] = false
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][v];
    }
}