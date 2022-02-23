package daimasuixianglu._9_dp;

public class _4_UniquePaths {

    public int uniquePaths(int m, int n) {
        // 动态规划

        // 第一步、定义dp数组
        // 定义dp[i][j]表示从(0,0)出发，走到(i,j)的不同走法总数
        // m*n的网格中，终点是(m-1,n-1)，而非(m,n)
        int[][] dp = new int[m][n];

        // 第二步、初始状态
        // 二维dp通常需要初始化第一行和第一列
        // 第0行
        // 考虑位置(i,j)，如果由上面(i-1,j)往下走得到，有dp[i][j]=dp[i-1][j]
        // 如果由左侧(i,j-1)往右走得到，有dp[i][j]=dp[i][j-1]
        // 综上所述有：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // m*n的网格中，终点是(m-1,n-1)，而非(m,n)
        return dp[m - 1][n - 1];
    }
}
