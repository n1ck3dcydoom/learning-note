package algorithm.leetcode.primeDp.day2;

public class _746_MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] c1 = new int[]{10, 15, 20};
        System.out.println(minCostClimbingStairs(c1));

        int[] c2 = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(c2));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // 第一步、定义dp数组
        // 定义dp[n]表示走到第n层台阶所需要的最小花费
        int[] dp = new int[n + 1];

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 0;
        dp[2]=Math.min(cost[0],cost[1]);

        // 第三步、递推表达式
        // 考虑dp[i]，有两种方式到达dp[i]
        // 从dp[i-1]走1步到达dp[i]，dp[i]1 = dp[i-1] + cost[i-1]
        // 从dp[i-2]走2步到达dp[i]，dp[i]2 = dp[i-2] + cost[i-2]
        // 综上所述 dp[i] = min(dp[i]1, dp[i]2)
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}