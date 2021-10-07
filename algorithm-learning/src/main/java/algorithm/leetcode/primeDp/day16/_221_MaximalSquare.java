package algorithm.leetcode.primeDp.day16;

public class _221_MaximalSquare {

    public static void main(String[] args) {
        char[][] mat = new char[][]{{'0'}};
        System.out.println(maximalSquare(mat));
    }

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一步、定义dp数组
        // 定义dp[i][j]表示以(i,j)为右下角的最大正方形面积的边长
        int[][] dp = new int[m][n];

        // 第二步、初始值
        // 第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
        }
        // 第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        }

        // 第三步、状态转移
        // 考虑以(i,j)结尾的，能够找到的最大正方形面积计算方式
        // 状态由(i-1,j)(i,j-1)(i-1,j-1)的最小正方形边长转移得到
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 首先(i,j)也要能够构成正方形
                if (matrix[i][j] != '0') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        // 找到最大边长
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }
}