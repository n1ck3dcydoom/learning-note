package algorithm.leetcode.primeDp.day7;

public class _122_BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit1(nums));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 第一步、定义dp数组
        // 每一天有两个状态：1、手里持有一支股票 2、手里没有股票
        // 定义dp[i][0]表示第i天时，手里没有股票的最大利润
        // dp[i][1]表示第i天时，手里有1支股票的最大利润
        int[][] dp = new int[n + 1][2];

        // 第二步、初始值
        dp[0][0] = 0;
        dp[0][1] = 0;
        // 第1天，手里没有股票时
        dp[1][0] = 0;
        // 第1天，手里有股票时
        dp[1][1] = -prices[0];

        // 第三步、递推表达式
        // 考虑第i天的情况
        // 若第i天手里没有股票dp[i][0]，由前一天手里没有股票和手里有股票两个状态转移过来
        // 若第i-1天手里有股票，那么第i天肯定卖掉了 dp[i][0] = dp[i-1][1] + p[i]
        // 若第i-1天手里没股票，那么第i天手里也没股票 dp[i][0] = dp[i-1][0]

        // 若第i天手里有股票dp[i][1]，由前一天手里没有股票和手里有股票两个状态转移过来
        // 若第i-1天手里有股票，那么第i天肯定也继续持有 dp[i][1] = dp[i-1][1]
        // 若第i-1天手里没股票，那么第i天肯定买入了股票 dp[i][1] = dp[i-1][0] - p[i]

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i - 1], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i - 1], dp[i - 1][1]);
        }
        // 最后一天手里有股票肯定花了p[i]买入了，所以其总利润肯定小于最后一天没股票的情况
        return dp[n][0];
    }

    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 即可以在第i天卖出前面买的股票，然后以第i天的价格再买进持有到后面的第j天

        // 假设再时间段[0~i-1]这段区间内存在m，n (m<n)，可以在m买入，然后n卖出，得到利润为r1
        // 然后再n继续买入持有到i再卖出，得到利润r2
        // 其等价于从m买入，一直持有到i再卖出，得到利润r3
        // 表达式r3 = r1+r2  当m~n~i连续时成立
        // 继续缩小区间范围
        // 使得[m,m+1)~[m+1,m+2)~...~[n-1,n)~[n,n+1)~...~[i-1,i)的所有利润ri之和仍然等于r3
        // 所以只要当前能够再上一天的价格上卖出，那么卖掉就能赚

        // 说简单点，只要把股票涨跌图里面所有的上升区间都收集起来，利润一定是最大的
        int res = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}