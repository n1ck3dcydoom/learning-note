package daimasuixianglu._9_dp;

public class _24_BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        // 动态规划
        // 与买股票2类似，买股票2有两个状态，手上有股票的最大收益，手上没有股票的最大收益

        // 第一步、定义dp数组
        // 定义dp[i][j]表示第i天状态为j的最大收益
        // j=0，持有股票
        // j=1，不持有股票，已经卖出，且不处于冷冻期
        // j=2，不持有股票，当天卖出
        // j=3，不持有股票，当天是冷冻期
        int n = prices.length;
        int[][] dp = new int[n + 1][4];

        // 第二步、状态转移方程
        // 考虑第i天
        // 若i持有股票，且i-1也持有股票，则dp[i][0]=dp[i-1][0]
        // 若i持有股票，且i-1不持有股票，说明i买入了，若i-1是冷冻期，则dp[i][0]=dp[i-1][3]-prices[i]
        // 若i持有股票，且i-1不持有股票，说明i买入了，若i-1不是冷冻期，则dp[i][0]=dp[i-1][1]-prices[i]
        // 即dp[i][0]=max(dp[i-1][0], max(dp[i-1][1]-prices[i], dp[i-1][3]-prices[i]))

        // 若i不持有股票，且i不是冷冻期，若i-1是冷冻期，则dp[i][1]=dp[i-1][3]
        // 若i不持有股票，且i不是冷冻期，若i-1不是冷冻期，则dp[i][1]=dp[i-1][1]
        // 这两种情况的不持有股票，是没有卖出收益的，根据dp的定义dp[i][1]是之前就已经卖出股票了
        // 即dp[i][1]=max(dp[i-1][3], dp[i-1][1])

        // 若i不持有股票，且i当天卖出，则i-1必定持有股票
        // 即dp[i][2]=dp[i-1][0]+prices[i]

        // 若i不持有股票，且i是冷却期，则i-1必定是刚卖出股票
        // 即dp[i][3]=dp[i-1][2]

        // 第三步、初始值
        // 有状态转移方程可以得到，dp都是由前面的状态推导得出，则遍历顺序是顺序遍历
        // 且初始状态都是dp[1]

        // 第一天持有股票
        dp[1][0] = -prices[0];
        // 第一天不持有，买了又卖，不在冷冻期收益也只有0
        dp[1][1] = 0;
        // 第一天不持有，买了又卖
        dp[1][2] = 0;
        // 第一天不持有，冷冻期（不会出现这种情况，初始化为0）
        dp[1][3] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i - 1], dp[i - 1][1] - prices[i - 1]));
            dp[i][1] = Math.max(dp[i - 1][3], dp[i - 1][1]);
            dp[i][2] = dp[i - 1][0] + prices[i - 1];
            dp[i][3] = dp[i - 1][2];
        }
        // 最后一天不持有股票的收益肯定大于持有股票的收益
        return Math.max(dp[n][1], Math.max(dp[n][2], dp[n][3]));
    }
}
