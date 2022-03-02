package daimasuixianglu._9_dp;

public class _26_LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        // 最长上升子序列长度
        // 动态规划

        // 第一步、定义dp数组
        // 定义dp[i]表示以i结尾的最长上升子序列长度
        int n = nums.length;
        int[] dp = new int[n];

        // 第二步、状态转移方程
        // 考虑以i结尾的子序列
        // 若nums[i]可以和前面的nums[j]构成更大的上升子序列，则dp[i]=dp[j]+1
        // 否则dp[i]=1;

        // 第三步、初始值
        // 有状态转移方程可以得到，后面的dp都是由前面推导出来的，所以遍历顺序为从前到后
        // 所有的dp都由dp[0]计算得到
        // 考虑只有第0个数的情况，此时的最长上升子序列就是nums[0]本身，长度为1
        dp[0] = 1;

        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 严格递增
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
