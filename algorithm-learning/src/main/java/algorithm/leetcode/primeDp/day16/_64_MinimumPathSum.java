package algorithm.leetcode.primeDp.day16;

public class _64_MinimumPathSum {

    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum(mat));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从(0,0)走到(i,j)所需要的最小花费
        int[][] dp = new int[m][n];

        // 第二步、初始值
        dp[0][0] = grid[0][0];
        // 第一行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 第三步、状态转移
        // 考虑位置(i,j)，只能由上方和左方转移得到
        // dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}