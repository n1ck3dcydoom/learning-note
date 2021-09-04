package algorithm.leetcode.primeAlgorithm.day12;

public class _198_HouseRobber {

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 7, 9, 3, 1 };
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        // 动态规划

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 第一步，定义dp数组
        // 令dp[n]表示偷取前n间房屋能够获得的最大金币
        // n+1额外保存0的位置
        int[] dp = new int[n + 1];

        // 第二步，初始值
        // 没有房子偷，能获得的金币为0
        // 只有1间房子，能获得的金币为第一间房子的金币
        dp[0] = 0;
        dp[1] = nums[0];

        // 第三步，递推表达式
        // 对于第i间房子，有两个选择
        // 1、偷，如果偷第i间房子，那么第i-1间房子一定不能偷
        // 即 dp[i] = dp[i-2] + nums[i]
        // 2、不偷，如果不偷第i间房子，那么第i-1间房子就可以偷
        // 即 dp[i] = dp[i-1]
        // 两种情况求最大值，综上所述 dp[i] = max(dp[i-2]+nums[i], dp[i-1])

        // 开始填表，一维表格
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
}
