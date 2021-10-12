package algorithm.leetcode.primeDp.day21;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 12:36
 **/
public class _279_PerfectSquares {

    public static void main(String[] args) {
        System.out.println(numSquares(12));
        //        System.out.println(numSquares(13));
    }

    public static int numSquares(int n) {
        // 完全背包问题

        // 第一步、定义dp数组
        // 定义dp[i]表示装满容量为i的背包所需要的物品数量
        int[] dp = new int[n + 1];
        // 还需要一个数组保存n前面所有的完全平方数，即所有物品
        int m = (int) Math.sqrt(n);
        int[] nums = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            nums[i] = i * i;
        }
        // 第二步、初始值
        dp[0] = 0;

        // 第三步、状态转移
        // dp[i]=max(dp[i], dp[i-w]+v)

        // 枚举背包
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            // 枚举物品
            for (int j = 1; j <= m; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - nums[j]] + 1);
                }
            }
        }
        return dp[n];
    }
}