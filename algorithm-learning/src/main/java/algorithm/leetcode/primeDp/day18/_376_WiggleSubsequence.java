package algorithm.leetcode.primeDp.day18;

public class _376_WiggleSubsequence {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(wiggleMaxLength(nums));
    }

    public static int wiggleMaxLength(int[] nums) {

        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[i]表示以nums[i]结尾的最长摆动子序列长度
        //        int[] dp = new int[n];

        // 第二步、初始值
        //        dp[0] = 1;

        // 第三步、状态转移
        // 考虑以nums[i]结尾的情况
        // 如果前面的数nums[j]处于上升状态，那么nums[i]<nums[j]
        // 这样可以和nums[j]构成一个新的摆动序列，且nums[i]处于下降状态
        // 如果前面的数nums[j]处于下降状态，那么nums[i]>nums[j]
        // 这样可以和nums[j]构成一个新的摆动序列，且nums[i]处于上升状态

        // 扩展dp数组的定义为二维，记录以nums[i]结尾的上升状态和下降状态
        // 0表示处于下降状态
        // 1表示处于上升状态
        //        int[][] dp = new int[n][2];
        //        dp[0][0] = 1;
        //        dp[0][1] = 1;
        //
        //        int res = 1;
        // dp[i]之和dp[i-1]相关，优化空间存储
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            // 往前寻找
            //            for (int j = i - 1; j >= 0; j--) {
            //                // 说明nums[i]对于nums[j]来说处于上升状态
            //                // dp[i]由dp[j]的下降状态转移
            //                if (nums[j] < nums[i]) {
            //                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
            //                }
            //                // 说明nums[i]对于nums[j]来说处于下降状态
            //                // dp[i]由dp[j]的上升状态转移
            //                else if (nums[j] > nums[i]) {
            //                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
            //                }
            //            }
            if (nums[i - 1] < nums[i]) {
                up = down + 1;
            } else if (nums[i - 1] > nums[i]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}