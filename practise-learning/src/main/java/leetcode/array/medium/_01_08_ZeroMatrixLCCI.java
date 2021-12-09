package leetcode.array.medium;

public class _01_08_ZeroMatrixLCCI {

    public static void main(String[] args) {
        int[][] m1 = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] m2 = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        setZeroes(m1);
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                System.out.print(m1[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
        setZeroes(m2);
        for (int i = 0; i < m2.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                System.out.print(m2[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] raw = new boolean[m];
        boolean[] col = new boolean[n];

        // 分别统计横纵上0的位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    raw[i] = true;
                    col[j] = true;
                }
            }
        }
        // 0所在行列清零
        for (int i = 0; i < m; i++) {
            if (raw[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (col[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
