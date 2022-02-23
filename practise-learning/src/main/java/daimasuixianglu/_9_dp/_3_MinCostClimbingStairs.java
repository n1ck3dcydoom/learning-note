package daimasuixianglu._9_dp;

public class _3_MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] cs = new int[] { 10, 15, 20 };
        minCostClimbingStairs(cs);
        int a = 1;
    }

    public static int minCostClimbingStairs(int[] cost) {
        // 动态规划

        int n = cost.length;
        // 第一步、定义dp数组
        // cost长度为n，对应下标为0~n-1个台阶，其顶部是n，等价于求到达n的最小花费
        // 定义dp[i]表示到达第i层阶梯所需要的最小花费
        int[] dp = new int[n];

        // 第二步、初始状态
        // 可以选择从0或者1出发，那么到达0或者1的最小花费就是0
        dp[0] = 0;
        dp[1] = 0;

        // 第三步、状态转移
        // 考虑第i个阶梯，由i-1跨1步到达，或者由i-2跨2步到达
        // 即dp[i]=min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2]) + cost[i - 2];
        }
        return dp[n];
    }
}
