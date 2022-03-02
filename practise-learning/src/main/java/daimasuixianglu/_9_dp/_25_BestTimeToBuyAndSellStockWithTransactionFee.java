package daimasuixianglu._9_dp;

public class _25_BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        // 动态规划，每一天只有两个状态

        // 第一步、定义dp数组
        // 定义dp[i][2]表示第i天不持有股票dp[i][0]，第i天持有股票dp[i][1]的最大收益
        // 定义每次买入时，支付手续费
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        // 第二步、状态转移
        // 考虑第i天
        // 如果第i天持有股票，且i-1天也持有股票，则dp[i][1]=dp[i-1][1]
        // 如果第i天持有股票，且i-1天不持有股票，则dp[i][1]=dp[i-1][0]-prices[i]-fee
        // 即dp[i][1]=max(dp[i-1][1], dp[i-1][0]-prices[i]-fee)

        // 如果第i天不持有股票，且i-1天也不持有股票，则dp[i][0]=dp[i-1][0]
        // 如果第i天不持有股票，且i-1天持有股票，则dp[i][0]=dp[i-1][1]+prices[i]
        // 即dp[i][0]=max(dp[i-1][0], dp[i-1][1]+prices[i])

        // 第三步、初始值
        // 由状态转移方程可以得到，后续的dp由前面的推导得到
        // 且所有dp状态都是由dp[1]得到
        dp[1][0] = 0;
        dp[1][1] = -prices[0] - fee;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        // 最后一天不持有股票肯定比持有股票的收益更高
        return dp[n][0];
    }
}
