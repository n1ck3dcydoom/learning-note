package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/16 22:09
 **/
public class _85_MaximalRectangle {


    public static void main(String[] args) {
        char[][] matrix = new char[][]{};
        System.out.println(maximalRectangle(matrix));
    }

    public static int maximalRectangle(char[][] matrix) {

        // 这跟之前求最大正方形的区别在于
        // 正方形的时候，用dp[i][j]表示以(i,j)为右下角的最大正方形，此时正方形的边长，即长和高是相等的
        // 如果按照正方形的解法去定义矩形的dp[i][i]为右下角的最大矩形面积，此时矩形的长宽都无法确定，明显dp数组定义有问题

        // 定义dp[i][j]表示以(i,j)结尾的长，即矩形的长，这个dp数组就是能够确定下来的
        // 递推关系式也很好确定，即dp[i][j] = dp[i][j] == 1 ? dp[i][j-1] + 1 : 0
        // 初始值也很好确定，只需要确定第一列的初始值即可推导出每一行的dp值

        int m = matrix.length;
        // 如果一行都没有，都不用判断宽度了
        if (m == 0) {
            return 0;
        }
        // 至少有一行才计算宽度
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }

        // 保存结果
        int res = 0;

        int[][] dp = new int[m][n];
        // 初始化第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            // 往上遍历找到最大高度
            for (int k = i; k >= 0 && matrix[k][0] == '1'; k--) {
                // 由于只有一列，长度为1，高度为i-k+1
                res = Math.max(res, i - k + 1);
            }
        }

        // 根据第一列，依次遍历每一行，完成dp打表
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = dp[i][j - 1] + 1;
                    // dp填表完成后，在遍历找到每个(i,j)上面的高度
                    // 相当于当我们完成(i,j)的dp值之后，从(i,j)开始往上找最大高度，同时记录最大面积
                    // 保存当前的长度
                    int w = dp[i][j];
                    for (int k = i; k >= 0 && matrix[k][j] == '1'; k--) {
                        // 找最大的高度，同时上面的值要去最小的长度
                        w = Math.min(w, dp[k][j]);
                        res = Math.max(res, w * (i - k + 1));
                    }
                }
            }
        }
        return res;
    }
}