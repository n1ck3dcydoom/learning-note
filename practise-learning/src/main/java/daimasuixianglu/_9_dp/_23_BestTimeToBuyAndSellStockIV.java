package daimasuixianglu._9_dp;

public class _23_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        // 动态规划
        // 与买股票3一样，从2笔交易变成了k笔交易
        // 可以举例：0继续持有，1第一次买入，2第一次卖出，3第二次买入，4第二次卖出，5第三次买入，6第三次卖出。。。。
        // 发现规律，从1开始，每次奇数则是买入，偶数则是卖出
        // 且交易笔数k带来了2*k种状态，加上最初的0继续持有，总计有2*k+1种状态

        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // 第一步、定义dp数组
        // 定义dp[i][j]表示第i天处于第j种状态的最大收益
        int[][] dp = new int[n + 1][2 * k + 1];

        // 第二步、状态转移方程
        // 与买股票3一样，每天第j次买入和第j次卖出的状态，与前一天的第j-1次买入和j-1次卖出相关
        // 第i天第j次买入，且i-1天没有股票，相当于i-1天是第j-1次卖出状态，则dp[i][j]=dp[i-1][j-1]-prices[i]
        // 第i天第j次买入，且i-1天有股票，相当于i-1天是第j次买入状态，则dp[i][j]=dp[i-1][j]

        // 第三步、初始值
        // 从状态转移方程得到，有dp[1][j]是初始状态
        // 每次买入都是-prices[0]，每次卖出都是0
        // 每次买入发生在奇数状态，将2k以前的所有奇数都初始化为-prices[0]
        dp[1][0] = 0;
        for (int i = 1; i <= 2 * k; i = i + 2) {
            dp[1][i] = -prices[0];
        }

        for (int i = 2; i <= n; i++) {
            // 当前什么都不做，跟前一天什么都不做状态一样
            dp[i][0] = dp[i - 1][0];
            // 计算第j次买入和第j次卖出的状态
            for (int j = 1; j <= 2 * k; j = j + 2) {
                // 奇数买入
                // 若j是某k次买入，则j-1就是第k-1次卖出，这两个状态是相邻的
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i - 1]);
                // 偶数买入
                // 若j+1是某k+1次卖出，则j就是第k次买入，这两个状态是相邻的
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i - 1]);
            }
        }
        // 最后一次卖出肯定是最大收益
        return dp[n][2 * k];
    }
}
