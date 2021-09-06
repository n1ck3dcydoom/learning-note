package algorithm.leetcode.primeDataStructure.day1;

public class _53_MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = new int[] { -1, -2 };
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        // 动态规划
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 第一步、定义dp数组
        // 令dp[i]表示以i结尾的子数组最大和
        // n+1额外保存0
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 数组为空，最大子序列和为0
        // 数组只有一个值，最大子序列和为这个值
        dp[0] = 0;
        dp[1] = nums[0];

        // 第三步、递推表达式
        // 考虑dp[i]，以i结尾的元素
        // 如果dp[i-1]是一个非零的数，那么加上nums[i]是有正向收益的（最坏情况不变）
        // 如果dp[i-1]是一个负数，那么加上nums[i]没有正向收益，最大值比nums[i]还小（一个数加一个负数一定比这个数本身还要小）
        // dp[i]=dp[i-1]+nums[i] (dp[i-1]>=0)
        // dp[i]=nums[i] (dp[i-1]<0)

        int res = Integer.MIN_VALUE;
        for (int i = 2; i <= n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i - 1];
            } else {
                dp[i] = dp[i - 1] + nums[i - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
