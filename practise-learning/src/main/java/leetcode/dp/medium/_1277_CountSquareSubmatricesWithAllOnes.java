package leetcode.dp.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 * <p>
 * 输入：matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/15 23:19
 **/
public class _1277_CountSquareSubmatricesWithAllOnes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(countSquares(matrix));
    }

    public static int countSquares(int[][] matrix) {
        // 朴素二维dp

        // 题目求什么，就定义什么
        // 定义dp[i][j]表示以(i,j)为右下角的完全由1组成的正方形数量
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int res = 0;
        // 初始值dp[0][0]由matrix[0][0]决定
        // 初始化第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i] == 1 ? 1 : 0;
            res += dp[0][i];
        }
        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] == 1 ? 1 : 0;
            res += dp[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果matrix[i][j]==0，则一定不能以(i,j)构成正方形，
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    res += dp[i][j];
                }
            }
        }
        return res;
    }
}