package leetcode.search.binary.medium;

public class _240_SearchIn2DMatrixII {


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(matrix, 26));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // 与矩阵搜索1(74.)不同的是，每一行的开头不是严格小于上一行的结尾
        // 没法在第一次二分中得到对应的行
        // 只能遍历行/列，依次二分查找

        int m = matrix.length;
        int n = matrix[0].length;
        // 遍历行
        for (int[] ints : matrix) {
            // 对每一行做二分查找
            int l = 0, r = n - 1; // 搜索区间是[l,r]
            while (l <= r) // 结束时l=r+1，搜索区间[r+1,r]为空
            {
                int mid = l + (r - l) / 2;
                if (ints[mid] == target) {
                    return true;
                } else if (ints[mid] < target) {
                    l = mid + 1;
                } else if (ints[mid] > target) {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}