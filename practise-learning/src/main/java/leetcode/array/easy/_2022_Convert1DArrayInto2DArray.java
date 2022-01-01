package leetcode.array.easy;

public class _2022_Convert1DArrayInto2DArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int[][] res = construct2DArray(arr, 2, 2);
        for (int[] r : res) {
            for (int n : r) {
                System.out.print(n + " ");
            }
            System.out.println("\n");
        }
    }

    public static int[][] construct2DArray(int[] original, int m, int n) {
        int k = original.length;
        if (m * n != k) {
            return new int[][]{};
        }
        int[][] res = new int[m][n];
        int p = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = original[p++];
            }
        }
        return res;
    }
}
