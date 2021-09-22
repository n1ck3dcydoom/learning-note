package algorithm.leetcode.primeDp.day3;

public class _198_HouseRobber {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        int[] nums1 = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob1(nums));
        System.out.println(rob1(nums1));
    }

    public static int rob(int[] nums) {
        int res = 0;
        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[n]表示抢劫前n间房子能够获得的最大金额
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 没有房子，收益为0
        // 只有1间房子，收益为nums[1]
        dp[0] = 0;
        dp[1] = nums[0];

        // 第三步、递推表达式
        // 考虑第i间房子dp[i]，有偷和不偷两种选择
        // 偷，则第i-1不能偷，收益由i-2转移，dp[i] = dp[i-2]+nums[i]
        // 不偷，则收益由i-1转移，dp[i] = dp[i-1]
        // 综上所述，dp[i] = max(dp[i-1], dp[i-2]+nums[i])
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    public static int rob1(int[] nums) {
        int res = 0;
        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[n]表示抢劫前n间房子能够获得的最大金额
        // 优化一下空间，之和前两个值相关
        int ppre = 0;
        int pre = nums[0];

        // 第二步、初始值
        // 没有房子，收益为0
        // 只有1间房子，收益为nums[1]
        //        dp[0] = 0;
        //        dp[1] = nums[0];

        // 第三步、递推表达式
        // 考虑第i间房子dp[i]，有偷和不偷两种选择
        // 偷，则第i-1不能偷，收益由i-2转移，dp[i] = dp[i-2]+nums[i]
        // 不偷，则收益由i-1转移，dp[i] = dp[i-1]
        // 综上所述，dp[i] = max(dp[i-1], dp[i-2]+nums[i])
        for (int i = 2; i <= n; i++) {
            int cur = Math.max(pre, ppre + nums[i - 1]);
            ppre = pre;
            pre = cur;
        }
        return pre;
    }
}