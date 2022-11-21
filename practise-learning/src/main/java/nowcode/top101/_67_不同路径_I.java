package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 16:41
 * Description:
 */

public class _67_不同路径_I {

    public static void main(String[] args) {
        System.out.println(uniquePaths(10, 1));
    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        // 第一步定义 dp 数组,定义 dp[i][j] 表示走到 (i,j) 不同的路径数
        int[][] dp = new int[m][n];
        // 第二步初始值,起点的步数为 0
        dp[0][0] = 0;
        // 第一行和第一列也作为初始值
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        // 第三步状态转移方程,考虑走到 (i,j)
        // 如果是从上面走下来,则 dp[i][j] = dp[i-1][j]
        // 如果是从左侧走下来,则 dp[i][j] = dp[i][j-1]
        // 即 dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
