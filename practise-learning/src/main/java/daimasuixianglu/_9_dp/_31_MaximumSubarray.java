package daimasuixianglu._9_dp;

public class _31_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        // 动态规划

        // 第一步、定义dp数组
        // 定义dp[i]表示以i结尾的最大子序列之和
        int n = nums.length;
        int[] dp = new int[n + 1];

        // 第二步、状态转移方程
        // 考虑以i结尾nums[i]，之前的最大子序列之和为dp[i-1]
        // 对于i来说，两个选择，加入之前的最大子序列之和，或者以自己开始一个全新的子序列
        // 如果dp[i-1]是一个负数，那么肯定有dp[i-1]+nums[i]<nums[i]
        // 如果dp[i-1]是一个非负数，那么肯定有dp[i-1]+nums[i]>=nums[i]
        // 所以dp[i]=dp[i-1]+nums[i] (dp[i-1]>=0)，dp[i]=nums[i] (dp[i-1]<0)

        // 第三步、初始值
        // 有状态转移方程可以得到，后续dp[i]都是由dp[i-1]转移得到，所以遍历顺序从前到后
        // 所有的dp值都是由dp[1]计算得到
        dp[1] = nums[0];
        // 使用res保存最大的dp[i]
        int res = dp[1];
        for (int i = 2; i <= n; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i - 1];
            } else {
                dp[i] = nums[i - 1];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
