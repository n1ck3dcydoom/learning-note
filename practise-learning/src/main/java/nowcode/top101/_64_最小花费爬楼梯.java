package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 15:43
 * Description:
 */

public class _64_最小花费爬楼梯 {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 90, 1, 1, 80, 1}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 0 || n == 1) {
            return 0;
        }

        // 第一步定义 dp 数组，定义 dp[i] 表示到达第 i 阶台阶所需要的最小花费
        int[] dp = new int[n + 1];

        // 第二步初始值
        dp[0] = 0;
        dp[1] = 0;

        // 第三步状态转移方程，考虑第 i 阶台阶的最小花费 dp[i]
        // 如果在 i - 1 跳 1 步得到，则有 dp[i] = dp[i - 1] + cost[i - 1]
        // 如果在 i - 2 跳 2 步得到，则有 dp[i] = dp[i - 2] + cost[i - 2]
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
