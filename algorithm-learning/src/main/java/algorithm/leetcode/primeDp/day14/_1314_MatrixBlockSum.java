package algorithm.leetcode.primeDp.day14;

public class _1314_MatrixBlockSum {

    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}};

        int[][] res = matrixBlockSum(mat, 3);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        // 第一步、定义dp数组
        // 定义dp表示以(i,j)为中心，向四周扩散k格后所围成矩阵的和
        int[][] res = new int[m][n];

        // 定义sum表示以(i,j)为右下节点，从(0,0)开始构成的矩形之和
        int[][] sum = new int[m + 1][n + 1];
        sum[1][1] = mat[0][0];
        // 第一行
        for (int i = 1; i <= n; i++) {
            sum[1][i] = sum[1][i - 1] + mat[0][i - 1];
        }
        // 第一列
        for (int i = 1; i <= m; i++) {
            sum[i][1] = sum[i - 1][1] + mat[i - 1][0];
        }
        // 计算前缀和
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        // 计算原矩阵当中每个位置满足题意得和
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // i+k,j+k可能大于m或者n，取最小值
                // i-k-1,j-k-1可能小于0，取最大值
                res[i - 1][j - 1] = sum[Math.min(i + k, m)][Math.min(j + k, n)]
                        - sum[Math.max(i - k - 1, 0)][Math.min(j + k, n)]
                        - sum[Math.min(i + k, m)][Math.max(j - k - 1, 0)]
                        + sum[Math.max(i - k - 1, 0)][Math.max(j - k - 1, 0)];
            }
        }
        return res;
    }
}