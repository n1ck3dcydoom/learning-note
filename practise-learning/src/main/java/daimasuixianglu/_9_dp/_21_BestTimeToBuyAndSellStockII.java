package daimasuixianglu._9_dp;

public class _21_BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {

        int n = prices.length;
        // 第一步、定义dp数组
        // 定义dp[i][2]表示第i天持有股票dp[i][1]和第i天不持有股票dp[i][0]所取得的最大收益
        int[][] dp = new int[n + 1][2];

        // 第二步、状态转移方程
        // 考虑第i天
        // 如果第i天持有股票，且i-1天也持有股票，则dp[i][1]=dp[i-1][1]
        // 如果第i天持有股票，且i-1天没有股票，则dp[i][1]=dp[i-1][0]-prices[i]
        // dp[i][1]=max(dp[i-1][1], dp[i-1][0]-prices[i])
        // 如果第i天没有股票，且i-1天也没有股票，则dp[i][0]=dp[i-1][0]
        // 如果第i天没有股票，且i-1天持有股票，则dp[i][0]=dp[i-1][1]+prices[i]
        // dp[i][0]=max(dp[i-1][0], dp[i-1][1]+prices[i])

        // 从状态转移方程可以看到，dp数组的构造顺序从0~i，则遍历顺序是从前到后
        // 且后面的dp都是由 dp[1]转移得到
        // 第三步、初始值
        dp[1][0] = 0;
        dp[1][1] = -prices[0];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }
        // 最后一天不持有股票的收益肯定比持有股票的收益更高
        return dp[n][0];
    }
}
