package daimasuixianglu._9_dp;

public class _27_LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        // 动态规划，最长连续上升子序列

        // 第一步、定义dp数组
        // 定义dp[i]表示以i结尾的最长连续上升子序列长度
        int n = nums.length;
        int[] dp = new int[n + 1];

        // 第二步、状态转移方程
        // 考虑以i结尾的数，由于必须要求连续，所以dp[i]的状态之和dp[i-1]有关
        // 若nums[i]>nums[i-1]，则dp[i]=dp[i-1]+1;
        // 否则dp[i]=1，表示仅有自己作为连续子序列

        // 第三步、初始值
        // 通过状态转移方程可以得到，后续的dp都是由前面的计算得到，所以遍历顺序从前到后
        // 所有的dp数组都是从dp[1]开始推导得到
        dp[1] = 1;

        int res = dp[1];
        for (int i = 2; i <= n; i++) {
            dp[i] = 1;
            if (nums[i - 1] > nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
