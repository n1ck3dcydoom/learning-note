package algorithm.leetcode.primeDp.day4;

public class _55_JumpGame {

    public static void main(String[] args) {
        //        int[] nums1 = new int[]{2, 3, 1, 1, 4};
        int[] nums2 = new int[]{3, 2, 1, 0, 4};
        //        System.out.println(canJump(nums1));
        System.out.println(canJump2(nums2));
        //        int[] nums3 = new int[]{1, 2};
        //        System.out.println(canJump1(nums3));
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[n]表示能否到达第n层台阶
        boolean[] dp = new boolean[n + 1];

        // 第二步、初始值
        dp[0] = true;
        dp[1] = true;

        // 第三步、递推表达式
        // 考虑第i层台阶dp[i]
        // 如果前面存在一个位置k (0 <= k < i)，满足nums[k] >= (i-k)，则说明能够从位置k跳到位置i
        // 前提是要能够到达dp[k]
        for (int i = 2; i <= n; i++) {
            // 检查前面的每一个位置
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] >= i - j) {
                    dp[i] = dp[j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public static boolean canJump1(int[] nums) {
        int n = nums.length;
        // 方法1的朴素dp是O(n^2)的，优化下
        // 定义dp[n]表示，从第n层出发能够走到的最远位置
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1 + nums[0];

        // 递推表达式
        // 考虑第i层台阶，如果从第i-1层能够走到k的位置，而从i出发能够走到i+nums[i]的位置
        // 如果从i-1层出发能够走的更远，那么dp[i]由dp[i-1]转移得到dp[i] = dp[i-1]
        // 否则考虑直接从i层出发走nums[i]步，即dp[i] = i + nums[i]
        for (int i = 2; i <= n; i++) {
            // 说明能够走到i位置
            if (dp[i - 1] >= i) {
                dp[i] = Math.max(dp[i - 1], i + nums[i - 1]);
                if (dp[i] >= n) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canJump2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        // 贪心
        // 每次更新前面能够走的最远的地方即可
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            // 前提仍然是要能够走到i的位置
            if (max >= i) {
                max = Math.max(max, i + nums[i]);
                if (max >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}