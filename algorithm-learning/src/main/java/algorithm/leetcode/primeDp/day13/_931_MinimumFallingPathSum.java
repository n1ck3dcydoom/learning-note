package algorithm.leetcode.primeDp.day13;

public class _931_MinimumFallingPathSum {

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int[][] matrix2 = new int[][]{{-19, 57}, {-40, -5}};
        System.out.println(minFallingPathSum(matrix1));
                System.out.println(minFallingPathSum(matrix2));
    }

    public static int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一步、定义dp数组
        // 定义dp[m][n]表示从第一行开始出发到达(m,n)所需要的最小路径和
        int[][] dp = new int[m][n];

        // 第二步、初始值
        // 第一行全部为初始值
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        // 第三步、状态转移
        // 考虑(i,j)，能够到达(i,j)的只有(i-1,j-1)(i-1,j)(i-1,j+)
        // 取最小值，即dp[i][j]=min(dp[i-1][j-1], min(dp[i-1][j], dp[i-1][j+1]) + matrix[i][j]
        for (int i = 1; i < m; i++) {
            int[] pre = dp[i - 1];
            for (int j = 0; j < n; j++) {
                // 上方一定存在
                dp[i][j] = dp[i - 1][j];
                // 检查右上方
                if (j + 1 < n) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                }
                // 检查左上方
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
                dp[i][j] += matrix[i][j];
            }
        }
        // 取最后一行的最小值
        int res = Integer.MAX_VALUE;
        for (int i : dp[m - 1]) {
            res = Math.min(res, i);
        }
        return res;
    }
}