package algorithm.leetcode.primeDataStructure.day3;

public class _121_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = new int[] { 1, 2 };
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        // 动态规划

        int n = prices.length;

        // 定义dp[i]表示第i天卖出能够获得的最大利润
        // n+1为0保留额外空间
        int[] dp = new int[n + 1];

        // 初始值
        // 第0天和第一天没有利润
        dp[0] = 0;
        dp[1] = 0;

        // 记录前i天能够购买的最小值
        int min = prices[0];
        // 记录当前能够获得的最大利润
        int res = 0;

        // 递推表达式
        // 考虑dp[i]，有两个选择
        // 1、i前面出现的最小值min都比第i天的价格更高
        // 那么卖出也没利润，即dp[i] =0
        // 2、在第i天卖出，dp[i] = prices[i] - min

        // 打表
        for (int i = 2; i <= n; i++) {
            if (min >= prices[i - 1]) {
                // 不卖
                dp[i] = 0;
            } else {
                // 卖
                dp[i] = prices[i - 1] - min;
            }
            // 更新最小值
            min = Math.min(min, prices[i - 1]);
            // 更新最大利润
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
