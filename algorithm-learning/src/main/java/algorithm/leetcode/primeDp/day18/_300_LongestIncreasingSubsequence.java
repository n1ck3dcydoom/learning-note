package algorithm.leetcode.primeDp.day18;

public class _300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {

        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[i]表示以nums[i]结尾的最长上升子序列长度
        int[] dp = new int[n];

        // 第二步、初始值
        dp[0] = 1;

        // 第三步、状态转移
        // 考虑以i结尾的时候
        // 如果nums[i]>nums[i-1]那么可以和前面的序列构成更长的上升子序列
        // dp[i]=dp[i-1]+1
        // 如果nums[i]<=nums[i-1]，则需要往前找到一个比nums[i]更小的数num[j]
        // 这样nums[i]才能和nums[j]构成新的子序列
        // dp[i]=dp[j]+1
        // 如果找到头了都找不到nums[j]<nums[i]的话，则dp[i]=1
        int res = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            // 往前寻找nums[j]<nums[i]的情况
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}