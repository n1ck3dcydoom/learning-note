package algorithm.leetcode.primeDp.day21;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 12:06
 **/
public class _343_IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        // 完全背包问题

        // 第一步、定义dp数组
        // 定义dp[i]表示容量为i的背包得到的最大乘积
        // 和为n，背包容量为n；拆分正数之和，每个物品为1~n
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 0和1不能够拆分
        dp[0] = 0;
        dp[1] = 0;

        // 第三步、状态转移
        // 与01背包类似  dp[j]=max(dp[j], dp[j-w]+v)
        // 而完全背包不同的是，选择物品i装入背包后，可以继续选择物品i装入
        // 1 <= j < i
        // 将i拆分为j和i-j，如果i-j不再拆分，乘积为j*(i-j)
        // 将i拆分为j和i-j，如果i-j继续拆分，乘积为j*dp[i-j]

        // 枚举背包
        for (int i = 2; i <= n; i++) {
            // 枚举物品
            int max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
