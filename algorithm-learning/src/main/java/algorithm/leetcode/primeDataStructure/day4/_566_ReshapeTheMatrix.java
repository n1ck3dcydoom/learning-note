package algorithm.leetcode.primeDataStructure.day4;

public class _566_ReshapeTheMatrix {

    public static void main(String[] args) {
        int[][] mat = new int[][] { { 1, 2 }, { 3, 4 } };
        int[][] res = matrixReshape(mat, 2, 2);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        if (m * n != r * c) {
            return mat;
        }

        int l = 0;
        // 原始矩阵压缩成一维矩阵
        int[] array = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[l++] = mat[i][j];
            }
        }
        l = 0;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = array[l++];
            }
        }
        return res;
    }
}
