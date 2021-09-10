package algorithm.leetcode.primeDataStructure.day5;

public class _73_SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        setZeroes1(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 遍历第一遍找到矩阵中所有的0
        // 两个额外的数组保存对应的行列是否有0出现
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        // 再次遍历矩阵，通过标记数组来更新对应的行和列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 使用矩阵的第一行和第二行代替两个额外数组保存当前行或者当前列是否出现0
        // 会导致第一行和第一列中的原本的0信息丢失
        // 需要两个额外变量保存第一行和第一列中是否出现过0
        boolean flagRow = false;
        boolean flagColumn = false;
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagColumn = true;
                break;
            }
        }
        // 遍历第一遍找到对应行列是否有0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 使用第一行和第一列保存信息
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 根据第一行和第一列的0的信息，更新整个矩阵
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后根据flagRow和flagColumn来更新第一行和第一列
        if (flagRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (flagColumn) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
