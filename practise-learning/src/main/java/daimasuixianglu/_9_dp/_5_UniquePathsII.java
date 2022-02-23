package daimasuixianglu._9_dp;

public class _5_UniquePathsII {

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 0, 1 } };
        System.out.println(uniquePathsWithObstacles(grid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 动态规划
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从(0,0)出发，走到(i,j)的不同走法总数
        // m*n的网格中，终点是(m-1,n-1)，而非(m,n)
        int[][] dp = new int[m][n];

        // 第二步、初始状态
        // 从(0,0)出发走到(0,0)的走法只有1中
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        // 二维dp通常需要初始化第一行和第一列
        // 第一行
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        // 第一列
        for (int j = 0; j < m; j++) {
            if (obstacleGrid[j][0] == 1) {
                break;
            }
            dp[j][0] = 1;
        }

        // 第三步、状态转移
        // 考虑位置(i,j)只能由上面(i-1,j)或者左面(i,j-1)走到
        // 即dp[i][j]= grid[i-1][j]==1?0:dp[i-1][j] + grid[i][j-1]==1?0:dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = (obstacleGrid[i - 1][j] == 1 ? 0 : dp[i - 1][j])
                            + (obstacleGrid[i][j - 1] == 1 ? 0 : dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
