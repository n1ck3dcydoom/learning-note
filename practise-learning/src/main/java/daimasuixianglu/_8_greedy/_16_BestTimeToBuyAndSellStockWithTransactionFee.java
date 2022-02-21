package daimasuixianglu._8_greedy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/2/21
 * Time: 22:57
 * Description:
 */

public class _16_BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    public static int maxProfit(int[] prices, int fee) {
        // 我他妈直接动态规划

        int n = prices.length;
        // 第一步、定义dp数组
        // 定义dp[i][1]表示第i天持有股票的收益，dp[i][0]表示第i天没有持有股票的收益
        int[][] dp = new int[n + 1][2];

        // 第二步、初始状态
        // 约定买入时计算手续费
        // 第1天持有股票的收益为-p[0]-fee
        // 第1天没有股票的收益为0
        dp[1][1] = -prices[0] - fee;
        dp[1][0] = 0;

        // 第三步、状态转移
        // 考虑第i天，手里没有股票时
        // 如果i-1天有股票，则第i天卖出，dp[i][0]=dp[i-1][1]+p[i-1]
        // 如果i-1天没有股票，则第i天也没有股票，dp[i][0]=dp[i-1][0]
        // 考虑第i天，手里有股票时
        // 如果i-1天也有股票，则第i天继续持有，dp[i][1]=dp[i-1][1]
        // 如果i-1天没有股票，则第i天买入，dp[i][1]=dp[i-1][0]-p[i-1]-fee

        // 从第2天开始计算
        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1] - fee);
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
        }
        // 最后一天没有持有股票的收益肯定比持有股票的收益高
        return dp[n][0];
    }
}
