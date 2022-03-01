package daimasuixianglu._9_dp;

public class _13_CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[j]表示凑出j的方案数
        int[] dp = new int[target + 1];

        // 第二步、初始值
        // 不使用任何数，凑出0的方案有1种
        dp[0] = 1;
        // 第一行，不使用任何数，凑出i的方案数为0

        // 第三步、状态转移
        // 考虑使用前i个数凑出j
        // 根据题意，求组合数，且每个物品可以多次使用，完全背包问题
        // 完全背包问题求方案总数，有状态转移方程dp[i][j]=dp[i-1][j]+dp[i-1][j-w[i]]

        // 求排列数，有先遍历背包，再遍历物品
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= n; j++) {
                if (i >= nums[j - 1])
                    dp[i] += dp[i - nums[j - 1]];
            }
        }
        return dp[target];
    }
}
