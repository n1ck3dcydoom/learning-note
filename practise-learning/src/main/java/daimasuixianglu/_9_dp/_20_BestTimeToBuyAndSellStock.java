package daimasuixianglu._9_dp;

public class _20_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        // 动态规划
        int n = prices.length;
        // 第一步、定义dp数组
        // 定义dp[i][2]表示第i天持有dp[i][1]或者不持有dp[i][0]股票能够获得的最大利益
        int[][] dp = new int[n][2];

        // 第二步、状态转移方程
        // 考虑第i天有股票
        // 如果第i-1天也持有股票，那么dp[i][1]=dp[i-1][1];
        // 如果i-1天没有持有股票，相当于第i天买入股票dp[i][1]=dp[i-1][0]-price[i]
        // dp[i][1]=max(dp[i-1][1], dp[i-1][0]-price[i])
        // 考虑第i天没有股票
        // 如果第i-1天也没有股票，那么dp[i][0]=dp[i-1][0]
        // 如果第i-1天持有股票，相当于第i天卖出股票dp[i][0]=dp[i-1][1]+price[i]
        // dp[i][0]=max(dp[i-1][0], dp[i-1][1]+price[i])

        // 由状态转移方程可以看到，所有的状态都是由dp[0][0],dp[0][1]转移得到
        // 第三步、初始状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 第四步、确定遍历顺序
        // 由状态转移方程可以得到顺序是从前往后遍历的
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // 最后一天不持有的收益肯定大于持有股票的收益
        return dp[n - 1][0];
    }
}
