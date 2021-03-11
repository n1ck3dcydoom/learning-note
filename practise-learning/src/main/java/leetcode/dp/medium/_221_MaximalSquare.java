package leetcode.dp.medium;

/**
 * @version 1.0
 * @description leetcode
 *
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/3/11 21:28
 **/
public class _221_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        // 看到二维矩阵基本思路就是二维dp

        // 如何定义dp的状态
        // 定义dp[i][j]表示满足某个条件下的最大的正方形的边长
        // 以前的二维dp都是表示从(0,0)到达(i,j)或者从(i,j)到达(n,m)所满足题意的某个状态

        // 二维dp的本质都是打表
        // 打表又分为两个方向
        // 正向打表（从左上遍历到右下）
        // 如果是正向打表的话，那么(i,j)就表示的是某个正方形的右下角
        // 逆向打表（从右下遍历到左上）
        // 如果是正向打表的话，那么(i,j)就表示的是某个正方形的左上角

        // 如何决定打表的顺序呢，就需要看哪个顺序能够定义初始值

        // 如果是正向打表，那么(0,0)就表示以(0,0)作为右下角的最大正方形的边长，看起来很好定义初始值
        // 如果matrix[0][0] = 0 那么dp[0][0] = 0 否则dp[0][0] = 1
        // 同理逆向打表也很好定义初始值

        // 不妨假设通过正向打表，即设dp[i][j]表示以(i,j)作为右下角的最大正方形的边长
        // 那么初始值就是dp[0][0]
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        int res = 0;
        // 初始化dp[0][0]
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        res = Math.max(res, dp[0][0]);
        // 二维dp基本都需要初始化第一列和第一行
        // 初始化第一行，由于只有一行，无法从左部构成更大的正方形，所以初始方块就决定了最大的正方形面积是1还是0
        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            res = Math.max(dp[0][i], res);
        }
        // 同理初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            res = Math.max(dp[i][0], res);
        }

        // 依次打表每一行
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res * res;
    }
}
