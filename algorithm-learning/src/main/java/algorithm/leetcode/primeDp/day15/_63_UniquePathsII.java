package algorithm.leetcode.primeDp.day15;

public class _63_UniquePathsII {

    public static void main(String[] args) {
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(mat));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从(0,0)到达(i,j)不同的走法
        int[][] dp = new int[m][n];

        // 第二步、初始值
        dp[0][0] = 1;
        // 第一行
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        // 第一列
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }

        // 第三步、状态转移
        // 考虑(i,j)只能由上方或者左方转移得到
        // 如果某个方向是障碍物，则dp[ik][jk]的贡献值为0
        // 如果(i,j)是障碍物，则dp[i][j]=0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] += obstacleGrid[i - 1][j] == 1 ? 0 : dp[i - 1][j];
                    dp[i][j] += obstacleGrid[i][j - 1] == 1 ? 0 : dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}