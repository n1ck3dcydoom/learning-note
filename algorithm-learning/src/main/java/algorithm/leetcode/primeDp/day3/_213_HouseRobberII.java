package algorithm.leetcode.primeDp.day3;

public class _213_HouseRobberII {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 3, 2};
        System.out.println(rob(nums1));
        int[] nums2 = new int[]{1, 2, 3, 1};
        System.out.println(rob(nums2));
        int[] nums3 = new int[]{0};
        System.out.println(rob(nums3));
    }

    public static int rob(int[] nums) {
        int res = 0;
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示抢劫前n间房子能够获得的最大金额
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 没有房子，收益为0
        // 只有1间房子，收益为nums[1]
        dp[0] = 0;
        //        dp[1] = nums[0];

        // 第三步、递推表达式
        // 考虑第i间房子dp[i]，有偷和不偷两种选择
        // 偷，则第i-1不能偷，收益由i-2转移，dp[i] = dp[i-2]+nums[i]
        // 不偷，则收益由i-1转移，dp[i] = dp[i-1]
        // 最后1间房特殊考虑，如果第1间没有偷，那么上述表达式成立
        // 如果第1间偷了，那么dp[n] = dp[n-1]
        // 综上所述，dp[i] = max(dp[i-1], dp[i-2]+nums[i])

        // 1、偷第1间房
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            if (i == n) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            }
        }
        res = dp[n];

        // 2、不偷第1间
        dp[1] = 0;
        dp[2] = nums[1];
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        res = Math.max(res, dp[n]);
        return res;
    }
}