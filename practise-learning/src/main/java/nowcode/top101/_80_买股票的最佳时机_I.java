package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 16:48
 * Description:
 */

public class _80_买股票的最佳时机_I {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 第一步定义 dp 数组,定义 dp[2][i] 表示在第 i 天能够获得的最大利润
        // 其中 dp[0][i] 表示第 i 天不持有股票; dp[1][i] 表示第 i 天持有股票
        int[][] dp = new int[2][n];

        // 第二步初始值
        // 第 0 天不买入股票
        dp[0][0] = 0;
        // 第 0 天买入股票
        dp[1][0] = -prices[0];

        // 第三步状态转移方程,考虑第 i 天能够获得的最大利润 dp[][i]
        // 如果在第 i 天持有股票,则如果前一天不持有股票,相当于在第 i 天购入股票 dp[1][i] = - prices[i]
        // 如果前一天持有股票,则 dp[1][i] = dp[1][i-1]
        // 如果在第 i 天不持有股票,则如果前一天也没有持有股票 dp[0][i] = dp[0][i-1]
        // 如果前一天买入股票,则 dp[0][i] = dp[1][i-1] + prices[i] 相当于第 i 天卖出了股票

        for (int i = 1; i < n; i++) {
            // 第 i 天持有
            dp[1][i] = Math.max(dp[1][i - 1], -prices[i]);
            // 第 i 天不持有
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
        }

        // 最后一天肯定是不持有股票的利润更大
        return dp[0][n - 1];
    }
}
