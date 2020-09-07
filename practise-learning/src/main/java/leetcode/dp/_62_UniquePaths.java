package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/9/7 21:37
 **/
public class _62_UniquePaths {

    /**
     * 动态规划
     * 假设有i行，j列
     * 想要知道到达第f(i,j)格有多少种步骤，就必须知道它上一格f(i-1,j)和前一格f(i,j-1)的步骤
     * 即递推表达式为：f(i,j) = f(i-1,j) + f(i,j-1)
     *
     * @param m 行
     * @param n 列
     * @return int 到达f(m,n)的不同路径
     */
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        // 只有一行或者一列的时候，直接返回另一个参数即为答案
        if (m == 1) {
            return 1;
        } else if (n == 1) {
            return 1;
        }
        // 初始化第一行和第一列的走法
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            res[0][i] = 1;
        }
        // 从第二行，第二列开始遍历求解所有答案
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }
}