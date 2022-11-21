package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 16:50
 * Description:
 */

public class _68_矩阵的最小路径和 {

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}}));
    }

    public static int minPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一步定义 dp 数组,定义 dp[i][j] 表示从 (0,0) 走到 (i,j) 的最短路径和
        int[][] dp = new int[m][n];

        // 第二步初始值,第一行和第一列作为初始值
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }

        // 第三步状态转移方程,考虑走到 (i,j) 时
        // 如果从上面走过来,则 dp[i][j] = dp[i-1][j] + m[i][j]
        // 如果从左面走过来,则 dp[i][j] = dp[i][j-1] + m[i][j]
        // dp[i][j] = min(dp[i][j-1], dp[i-1][j])
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
