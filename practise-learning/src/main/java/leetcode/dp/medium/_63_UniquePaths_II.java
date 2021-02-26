package leetcode.dp.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * <p>
 * <p>
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/26 20:00
 **/
public class _63_UniquePaths_II {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(nums));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 朴素dp思路
        // 对于地图中任何一个位置，它只能从上一格或者左一格到达当前位置
        // 如果上一格是障碍物，只能从左边到达；如果左边是障碍物，只能从上面到达
        // 如果左边和上面都是障碍物，那么没法到达

        // 题目求得是不同的路径总数，则dp数组存储的也就是路径总数

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // 定义dp[i][j]表示从左上角到达f(i,j)的不同路径总数
        int[][] dp = new int[m][n];

        // 初始值
        // 初始化第一行和第一列的走法，因为他们只能通过左边或者只能通过上边到达
        // 如果遇到障碍物，则置0，且后面都是0

        // 初始化第一行，i遍历需要判断当前是否是障碍物，如果是的话则当前位置和后面的不用初始化1了，数组默认值就是0
        for (int i = 0; i < n && obstacleGrid[0][i] != 1; i++) {
            dp[0][i] = 1;
        }
        // 初始化第一列，i遍历需要判断当前是否是障碍物，如果是的话则当前位置和后面的不用初始化1了，数组默认值就是0
        for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++) {
            dp[i][0] = 1;
        }

        // 考察任意dp[i][j] ，它只能从上一格或者左一格到达，如果其中一个是障碍物，则只能从另一条路到达
        // 即dp[i][j] = dp[i-1][j] + dp[i][j-1]
        // 如果左边和上边都是障碍物，则到达f(i,j)的路径数等于0

        // 问题：如何f(i,j)本身是障碍物导致没法达到，还是说f(i-1,j)和f(i,j-1)都是障碍物导致无法到达？
        // 在遍历i和j的时候，如果发现obstacleGrid[i][j]==1，则说明f(i,j)就是障碍物，不用做递推选择了

        // 遍历行
        for (int i = 1; i < m; i++) {
            // 遍历列
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}