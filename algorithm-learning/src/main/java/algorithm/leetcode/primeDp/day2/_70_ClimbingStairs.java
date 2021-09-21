package algorithm.leetcode.primeDp.day2;

public class _70_ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(10));
    }

    public static int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示爬到n层楼梯的方法总数
        int[] dp = new int[n + 1];

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        // 第三步、递推表达式
        // 考虑dp[i]有两种方式到达dp[i]
        // 从dp[i-1]走1步，即dp[i]=dp[i-1]
        // 从dp[i-2]走两步，即dp[i]=dp[i-2]
        // 综上所述dp[i] = dp[i-1]+dp[i-2]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}