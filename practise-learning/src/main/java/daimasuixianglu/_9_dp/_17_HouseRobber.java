package daimasuixianglu._9_dp;

public class _17_HouseRobber {

    public int rob(int[] nums) {
        // 动态规划

        int n = nums.length;
        // 第一步、定义dp数组
        // 定义dp[i]表示偷取前i间房屋能够获得的最大金额
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 没有房子偷能够获得的最大价值为0
        dp[0] = 0;

        // 第三步、状态转移
        // 考虑前i间房，如果偷第i间房，那么i-1就没法再偷，dp[i]=dp[i-2]+nums[i]
        // 如果不偷第i间房，那么i-1可以偷(但是不一定偷)，dp[i]=dp[i-1]
        // 求最大值dp[i]=max(dp[i-1], dp[i-2]+nums[i])
        // 状态转移方程可以看到i和i-2有关系，所以需要从2开始遍历，同时dp[1]加入初始状态
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
}
