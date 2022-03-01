package daimasuixianglu._9_dp;

public class _18_HouseRobberII {

    public int rob(int[] nums) {
        // 动态规划
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 第一步、定义dp数组
        // 定义dp[i]表示偷前i间房能够获得的最大金额
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 没有房子偷能够获得的最大金额为0
        dp[0] = 0;

        // 第三步、状态转移
        // 考虑第i间房，同样的有：
        // 如果偷i，则i-1一定不能偷，dp[i]=dp[i-2]+nums[i];
        // 如果不偷i，则i-1可以偷(不一定偷)，dp[i]=dp[i-1]
        // 取最大值dp[i]=max(dp[i-1], dp[i-2]+nums[i])

        // 从状态转移方程可以看出，dp[0]和dp[1]都是初始状态
        // 由于第一间房和最后一间房连在一起，这两间房只能挑一间偷
        // 若偷第1间房，则[0~n-2]满足上述状态转移方程，因为n-1一定不能偷
        // 若偷第n-1间房，则[1~n-1]满足上述状态转移方程，因为0一定不能偷

        // 偷第一间房，且dp[n]=dp[n-1]（因为n不偷）
        dp[1] = nums[0];
        for (int i = 2; i <= n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        int first = dp[n - 1];
        // 偷最后一间房，i从3开始遍历，初始状态变为dp[2]和dp[1]
        dp[1] = 0; // 第一间房不能偷
        dp[2] = nums[1]; // 第一间房不能偷的情况下，偷前2间房能够拿到的最大金额为第二间房的金额
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return Math.max(first, dp[n]);
    }
}
