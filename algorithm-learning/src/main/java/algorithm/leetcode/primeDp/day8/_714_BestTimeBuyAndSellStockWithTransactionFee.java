package algorithm.leetcode.primeDp.day8;

public class _714_BestTimeBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 第一步、定义dp数组
        // 每天有两个状态，持有或者不持有股票
        // dp[n][0]表示第n天不持有股票
        // dp[n][1]表示第n天持有股票
        int[][] dp = new int[n + 1][2];

        // 第二步、初始值
        dp[0][0] = 0;
        dp[0][1] = 0;
        // 第一天
        dp[1][0] = 0;
        dp[1][1] = -prices[0];

        // 第三步、状态转移
        // 考虑第i天，可能持有或不持有，每次手续费以卖出时收取一次
        // 若第i天持有股票，若i-1天本身也持有股票
        // dp[i][1]=dp[i-1][1]
        // 若i-1天不持有股票，则第i天买入
        // dp[i][1]=dp[i-1][0]-p[i]
        // 若第i天没有持有股票，若i-1天也没有股票
        // dp[i][0]=dp[i-1][0]
        // 若i-1天持有股票，则第i天卖出，同时收取手续费
        // dp[i][0]=dp[i-1][1]+p[i]-fee
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1] - fee);
        }
        // 最后一天不持有股票肯定比持有股票收益更高
        return dp[n][0];
    }
}