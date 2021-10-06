package algorithm.leetcode.primeDp.day15;

public class _62_UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从(0,0)到(i-1,j-1)不同的步数
        int[][] dp = new int[m][n];

        // 第二步、初始值
        // 第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 第三步、状态转移
        // 考虑(i,j)由(i-1,j)或者(i,j-1)转移得到
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}