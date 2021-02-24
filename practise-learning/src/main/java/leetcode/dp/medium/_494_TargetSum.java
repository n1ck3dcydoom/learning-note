package leetcode.dp.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例：
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 *  
 * <p>
 * 提示：
 * <p>
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/23 20:19
 **/
public class _494_TargetSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1};
        int S = 3;
        System.out.println(findTargetSumWays(nums, S));
    }

    // 背包问题通常可以通过打表（填表格）去层层推出结果
    public static int findTargetSumWays(int[] nums, int S) {
        // 朴素dp思路

        int n = nums.length;
        // 从0-i中挑出若干个数组成集合，使得集合的和等于j
        // 定义dp[i][j]表示从0到i的若干个数中，能够凑出j的不同的组合数

        // 考虑第i个数，它有两个选择，加上i或者减去i
        // 1、如果能够通过加上i得到j，那么在i-1的时候，至少要给j预留出j-nums[i]的空间，这样才能在i-1的情况下加上i得到j
        // 即dp[i][j] = dp[i-1][j-nums[i]]
        // 2、如果能够通过减去i得到j，同理，至少要给j预留出j+nums[i]的空间
        // 即dp[i][j] = dp[i-1][j+nums[i]]
        // 综上可以得到递推表达式：
        // dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i]]

        // TODO 这里j的边界值需要考虑下
        // 考虑普通的背包问题，从0到i个物品中选出若干个物品，刚好装满容量为V的背包
        // 背包的容量通常初始化为V+1
        // 如果这个j的数组长度也初始化为nums[]数组的和sum+1的话，即int[][] dp = new int[n + 1][sum + 1];
        // 这里就默认为从0到i个元素中，求得和为0到S的组合数，即默认这些个元素全部相加得到S
        // 从而忽略了元素全部相减得到-sum差的情况，所以这里需要把-sum到sum全部初始化
        // 即int[][] dp = new int[n + 1][2 * sum + 1]

        // 求nums的和
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 如果目标值的S的绝对值大于nums数组和的绝对值，即nums全部相加或者全部相减，都没法得到目标和S
        if (Math.abs(S) > Math.abs(sum)) {
            return 0;
        }
        int[][] dp = new int[n][2 * sum + 1];
        // 由于这里定义了-sum到sum的所有容量，然而数组下标不能为负数，所以全部偏移了sum个位置
        // 即dp[0]表示的是-sum，而dp[sum]表示的是0，dp[2*sum]才表示的是sum

        // 如果和是7，那么初始化的背包容量就是15，表示从-7到7所有的可能，如果目标和是4，那么4应该在15的哪个位置呢？
        // 很容易想到如果数组下标能够从负的开始，那么目标和4所在的位置就等于其下标4，现在由于没法保存负的下标
        // 从而往前面偏移了7个位置，即0表示-7，7表示0，14表示7，那么4的位置就等于7+4=11，即下标11
        // 即目标和+数组和sum(这里的sum就是背包数组的偏移量) res = dp[n][sum + S]

        // 初始化 base case
        // TODO 尤其需要考虑背包容量为0的情况，如果没有物品放入背包，而且物品背包容量为0，那么+0或者-0都可以得到0
        // TODO 所以如果第1个元素值是0，那么它可以通过+0和-0两种方法得到0
        if (nums[0] == 0) {
            dp[0][sum] = 2; // TODO 这里的sum其实就是2*sum+1中，表示j=0的位置
        } else {
            dp[0][sum + nums[0]] = 1; // 这里表示第一个物品价值为w，放入大小为sum+nums[0]即第一个物品的背包的个数
            dp[0][sum - nums[0]] = 1; // 这里表示放入sum-nums[0]的背包
        }

        // 填表做选择
        // 遍历物品
        for (int i = 1; i < n; i++) {
            // 遍历背包，实际上是从-sum遍历到sum
            for (int j = 0; j < 2 * sum + 1; j++) {
                // 考虑边界情况，如果物品体积已经小于背包容量了，那么它之和上一轮物品的右上角的-sum+nums[i]相关
                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j + nums[i]];
                }
                // 同理，如果物品体积大于背包容量了，则它之和上一轮物品的sum-nums[i]相关
                else if (j + nums[i] > 2 * sum) {
                    dp[i][j] = dp[i - 1][j - nums[i]];
                } else {
                    // 根据递推表达式dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i]]
                    dp[i][j] = dp[i - 1][j + nums[i]] + dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[n - 1][S + sum];
    }
}