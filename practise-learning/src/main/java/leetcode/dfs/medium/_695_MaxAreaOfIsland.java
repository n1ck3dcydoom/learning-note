package leetcode.dfs.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 *  
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/6 21:43
 **/
public class _695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;

        // 用于保存最大岛屿面积
        int res = 0;

        // 遍历网格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 只遍历陆地
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    // 直接求当前递归找到的岛屿的面积
    private int dfs(int[][] grid, int i, int j) {
        // 如果超出边界，则返回面积0
        if (!arrivedSide(grid, i, j)) {
            return 0;
        }
        // 如果不是陆地，返回面积0
        if (grid[i][j] != 1) {
            return 0;
        }
        // 在遍历到陆地后把陆地标记为 '2'，避免重复遍历
        grid[i][j] = 2;
        // 进行递归寻找岛屿所有的面积，肯定是从陆地开始第一次递归的，所以初始面积是1
        return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);

    }


    /**
     * 当前网格(i,j)处于grid的范围内，返回true
     * 否则返回false
     */
    private boolean arrivedSide(int[][] grid, int i, int j) {
        return (0 <= i && i < grid.length) && (0 <= j && j < grid[0].length);
    }
}