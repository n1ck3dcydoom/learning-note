package daimasuixianglu._9_dp;

public class _6_IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        // 动态规划

        // 第一步、定义dp数组
        // 定义dp[i]表示拆分正整数i可以得到的最大乘积
        int[] dp = new int[n + 1];

        // 第二步、初始状态
        dp[0] = 0;
        dp[1] = 1;

        // 第三步、状态转移
        // 考虑拆分正整数i，将其拆出j
        // 如果i-j不再继续拆分直接相乘，则有dp[i]=(i-j)*j
        // 如果i-j继续拆分多个正整数相乘，则有dp[i]=dp[i-j]*j
        // 求最大的dp[i]，需要遍历j从1~i-1
        for (int i = 2; i <= n; i++) {
            // 寻找拆分乘积最大的j
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[i] = j * (i - j), dp[i - j] * j));
            }
        }
        return dp[n];
    }
}
