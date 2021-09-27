package algorithm.leetcode.primeDp.day7;

public class _121_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {

        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示在第n天卖出能够得到的最大利润
        int[] dp = new int[n + 1];

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 0;

        // 第三步、递推表达式
        // 考虑第i天卖出股票dp[i]，能够得到的最大利润和前面i-1天最便宜的价格min
        // 即dp[i] = prices[i] - min
        // 如果前面最便宜的价格min都比当前价格更高，则当天卖出能够得到的利润为0
        int res = 0;
        int min = prices[0];
        for (int i = 2; i <= n; i++) {
            // 第i天能够卖出
            if (min < prices[i - 1]) {
                //                dp[i] = prices[i - 1] - min;
                res = Math.max(res, prices[i - 1] - min);
            }
            // 更新最便宜价格
            min = Math.min(min, prices[i - 1]);
        }

        return res;
    }
}