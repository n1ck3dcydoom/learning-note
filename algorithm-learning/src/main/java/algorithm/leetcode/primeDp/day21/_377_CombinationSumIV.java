package algorithm.leetcode.primeDp.day21;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 1:38
 **/
public class _377_CombinationSumIV {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(combinationSum4(nums, 4));
    }

    public static int combinationSum4(int[] nums, int target) {
        // 完全背包问题
        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[i]表示装满容量为i的方法
        int[] dp = new int[target + 1];

        // 第二步、初始值
        // 背包为空时，没有物品放入视为1中方法
        dp[0] = 1;

        // 第三步、状态转移
        // 考虑容量为i的背包
        // 如果不放入
        // dp[i]=dp[i-1]
        // 如果放入
        // dp[i]=dp[j-w]

        // 枚举背包
        for (int i = 1; i <= target; i++) {
            // 枚举物品
            for (int j = 0; j < n; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}