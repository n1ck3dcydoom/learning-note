package algorithm.leetcode.primeDp.day8;

public class _309_BestTimeBuyAndSellStockWthCooldown {
    public int maxProfit(int[] prices) {

        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 第一步、定义dp数组
        // 参考前一道股票买卖122.可以卖出后当天立即买入，有如下两个状态
        // dp[n][0]-表示当天没有股票
        // dp[n][1]-表示当天有股票
        // 这里没有股票的情况，又分为两种情况
        // dp[n][0]-表示当天没有股票，且当天不处于冷却期
        // dp[n][2]-表示当天没有股票，且当天处于冷却期
        int[][] dp = new int[n + 1][3];

        // 第二步、初始值
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        // 第一天无股票，不处于冷却期
        dp[1][0] = 0;
        // 第一天买入股票
        dp[1][1] = -prices[0];
        // 第一天无股票，处于冷却期
        dp[1][2] = 0;

        // 第三步、状态转移
        // 考虑第i天的情况
        // 若第i天有股票买入，分情况讨论
        // 第i-1天本身也持有股票 dp[i][1] = dp[i-1][1]
        // 第i-1天没有股票，那么必须要第i-1不处于冷却期，第i天才能买入 dp[i][1] = dp[i-1][0] - p[i]
        // 即 dp[i][1] = max(dp[i-1][1], dp[i-1][0] - p[i])

        // 第i天没有股票，且不处于冷却期
        // 第i-1天也没有股票，无论第i-1有没有冷却期，都一样 dp[i][0] = max(dp[i-1][0], dp[i-1][2])

        // 第i天没有股票，且处于冷却期
        // 则说明第i-1天一定卖出了股票
        // dp[i][2] = dp[i-1][1] + p[i]
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        // 最后一天如果还持有股票，其总收益肯定是低于不持有股票的两种情况的
        return Math.max(dp[n][0], dp[n][2]);
    }
}