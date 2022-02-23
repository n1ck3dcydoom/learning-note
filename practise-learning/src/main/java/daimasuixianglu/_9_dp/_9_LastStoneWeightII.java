package daimasuixianglu._9_dp;

public class _9_LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        // 动态规划
        // 转换为将一堆石头分为两堆重量尽可能接近的石头，抵消后有最小剩余重量
        // 转换为选取若干个石头，装满容量为sum/2的背包

        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int v = sum / 2;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示前i个石头装满容量为j的背包，所能得到的最大重量
        int[][] dp = new int[n + 1][v + 1];

        // 第二步、初始值
        dp[0][0] = 0;
        // 第一行，没有物品选中，所能得到的最大重量是0
        for (int i = 1; i <= v; i++) {
            dp[0][i] = 0;
        }
        // 第一列，背包容量为0，所能得到的最大重量是0
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 第三步、状态转移
        // 考虑前i个物品装满容量为j的背包dp[i][j]
        // 如果不放入第i个物品，有dp[i][j]=dp[i-1][j]
        // 如果放入第i个物品，有dp[i][j]=dp[i-1][j-w[i]]+v[i]

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包
            for (int j = 1; j <= v; j++) {
                // 不放入物品i
                if (j < stones[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // 放入物品i
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        // dp[n][v]求得装满一半背包的最大物品价值
        // 另一堆重量为sum-dp[n][v]
        // 取两堆之差的绝对值
        // return Math.abs((sum - dp[n][v]) - dp[n][v]);
        // 实际上，由于v=sum/2，可以得到v<=sum/2
        // 所以dp[n][v]一定是小于等于sum-dp[n][v]
        return sum - dp[n][v] - dp[n][v];
    }

    public int lastStoneWeightII1(int[] stones) {
        // 空间压缩为1为数组
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int v = sum / 2;
        int[] dp = new int[v + 1];
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包，逆序遍历
            for (int j = v; j >= 0; j--) {
                if (j < stones[i - 1]) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return sum - 2 * dp[v];
    }
}
