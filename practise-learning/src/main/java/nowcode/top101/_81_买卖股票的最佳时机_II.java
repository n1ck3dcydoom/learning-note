package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 17:02
 * Description:
 */

public class _81_买卖股票的最佳时机_II {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 第一步定义 dp 数组,定义 dp[5][i] 表示在第 i 天能够获得的最大利润
        int[][] dp = new int[5][n];

        // 第二步初始值
        // 第 0 天什么都不做
        dp[0][0] = 0;
        // 第 0 天第一次买入股票
        dp[1][0] = -prices[0];
        // 第 0 天第一次买入股票,且第一次卖出股票
        dp[2][0] = 0;
        // 第 0 天第二次买入股票
        dp[3][0] = -prices[0];
        // 第 0 天第二次买入股票,且第二次卖出股票
        dp[4][0] = 0;

        // 第三步状态转移方程,考虑第 i 天能够获得的最大利润 dp[][i]

        for (int i = 1; i < n; i++) {
            // 如果在第 i 天什么都不做,则由第 i-1 天不持有股票转移
            dp[0][i] = dp[0][i - 1];
            // 如果第 i 天第一次买入股票,则由第 i-1 天不持有 或者 第 i-1 天第一次持有转移
            dp[1][i] = Math.max(dp[0][i - 1] - prices[i], dp[1][i - 1]);
            // 如果第 i 天第一次卖出股票,则由第 i-1 不持有股票 或者 第 i-1 天第一次买入股票后第 i 天卖出转移
            dp[2][i] = Math.max(dp[2][i - 1], dp[1][i - 1] + prices[i]);
            // 如果第 i 天第二次买入股票,则由第 i-1 天第一次卖出股票 或者 第 i-1 天第二次买入股票转移
            dp[3][i] = Math.max(dp[3][i - 1], dp[2][i - 1] - prices[i]);
            // 如果第 i 天第二次卖出股票,则由第 i-1 天第二次买入股票,或者第 i-1 天第二次卖出股票转移
            dp[4][i] = Math.max(dp[4][i - 1], dp[3][i - 1] + prices[i]);
        }

        // 最后一天肯定是不持有股票的利润更大,分为一直没有买过,只买卖过一次和买卖过两次
        return Math.max(dp[0][n - 1], Math.max(dp[2][n - 1], dp[4][n - 1]));
    }
}
