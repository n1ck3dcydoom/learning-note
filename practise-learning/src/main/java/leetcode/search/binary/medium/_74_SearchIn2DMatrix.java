package leetcode.search.binary.medium;

public class _74_SearchIn2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchMatrix(matrix, 16));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 第一次二分第一列，找到正确的行
        int l = 0, r = m - 1; // 搜索区间[l,r]
        while (l <= r) { // 结束时，l=r+1，搜索区间[r+1,r]为空
            int mid = l + (r - l) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                l = mid + 1;
            } else if (matrix[mid][0] > target) {
                r = mid - 1;
            }
        }
        // r超出了上界0，查找的数比矩阵最小的数还要小
        if (r < 0) {
            return false;
        }
        // 此时r就是确定的行
        int raw = r;
        // 第二次二分对应行，找到是否存在target
        l = 0;
        r = n - 1; // 搜索区间[l,r]
        while (l <= r) // 结束时，l=r+1，搜索区间[r+1,r]为空
        {
            int mid = l + (r - l) / 2;
            if (matrix[raw][mid] == target) {
                return true;
            } else if (matrix[raw][mid] < target) {
                l = mid + 1;
            } else if (matrix[raw][mid] > target) {
                r = mid - 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        // 通过观察可以发现
        // 矩阵右上角开始，左边的数都小于右边，下边的数都小于上边
        // 递归带入任意一个节点均满足这个规律
        // 类似与BST的搜索
        // 如果给定查找值t等于root节点值，返回true
        // 如果给定查找值t小于root节点的值，递归查找root左子树
        // 如果给定查找值t大于root节点的值，递归查找root右子树

        int m = matrix.length;
        int n = matrix[0].length;
        // 从右上角开始往左/下搜索
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            // 继续搜索下方
            else if (matrix[i][j] < target) {
                i++;
            }
            // 继续搜索左方
            else if (matrix[i][j] > target) {
                j--;
            }
        }
        return false;
    }
}