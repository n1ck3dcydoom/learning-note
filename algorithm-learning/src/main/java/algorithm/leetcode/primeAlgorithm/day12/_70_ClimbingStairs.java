package algorithm.leetcode.primeAlgorithm.day12;

public class _70_ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        // 动态规划

        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        // 第一步、定义dp数组
        // 令dp[n]表示爬n格台阶所有不同的走法
        int[] dp = new int[n + 1];

        // 第二步、初始状态
        // 如果没有台阶，那么也没有走法
        dp[0] = 0;
        // 只有一步台阶，有唯一一种从0层跨1步的走法
        dp[1] = 1;
        // 只有两步台阶，有两种走法，从0层跨2步，从0层跨1步再跨1步
        dp[2] = 2;

        // 第三步、递推表达式（状态转移方程）
        // 考虑第i层时，我们有两种走法
        // 1、从i-1层跨1步台阶到达第i层
        // 即 dp[i] = dp[i-1]
        // 2、从i-2层跨2步台阶到达第i层
        // 即 dp[i] = dp[i-2]
        // 综上所述，dp[i] = dp[i-1] + dp[i-2]

        // 开始填表，一维表格
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
