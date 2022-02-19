package daimasuixianglu._8_greedy;

public class _3_BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        // 每相邻两天的收益可以量化
        // 7 1 5 3 6 4
        // -6 4 -2 3 -2
        // 每次贪心只取整数收益，即可得到最大收益

        if (prices.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    public int maxProfit1(int[] prices) {
        // 动态规划

        int n = prices.length;
        // 第一步、定义dp数组
        // 定义dp[i][1]表示在第i天持有股票时的最大收益 / dp[i][0]没有持有股票能够得到的最大收益
        int[][] dp = new int[n + 1][2];

        // 第二步、初始状态
        // 第一天没有持有股票
        dp[1][0] = 0;
        // 第一天买入股票
        dp[1][1] = -prices[0];

        // 第三步、状态转移
        // 考虑第i天，四种选择
        // 第i天没有股票，且前一天没有股票
        // dp[i][0]=dp[i-1][0]

        // 第i没有股票，且前一天有股票，则说明在第i天卖出
        // dp[i][0]=dp[i-1][1]+prices[i]

        // 第i天有股票，且前一天没有股票，则说明在第i天买入
        // dp[i][1]=dp[i-1][0]-prices[i]

        // 第i天有股票，且前一天有股票
        // dp[i][1]=dp[i-1][1]

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }
        // 最后一天没有股票的利润肯定比买入股票的利润更高
        return dp[n][0];
    }
}
