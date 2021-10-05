package algorithm.leetcode.primeDp.day14;

public class _304_NumMatrix {

    private int[][] sum;

    public _304_NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 二维矩阵前缀和
        this.sum = new int[m + 1][n + 1];
        // 计算前缀和
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1]
                - sum[Math.max(row1, 0)][col2 + 1]
                - sum[row2 + 1][Math.max(col1, 0)]
                + sum[Math.max(row1, 0)][Math.max(col1, 0)];
    }
}