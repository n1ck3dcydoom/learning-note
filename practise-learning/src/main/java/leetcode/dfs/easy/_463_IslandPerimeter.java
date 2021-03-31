package leetcode.dfs.easy;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * <p>
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 * 示例 2：
 * <p>
 * 输入：grid = [[1]]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：grid = [[1,0]]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/31 20:28
 **/
public class _463_IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        // 遍历网格，跟遍历树一样，dfs，网格有上下左右4个方向，类似于树的左右子结点
        // 可以类比得出，网格其实就是一个4叉树

        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int m = grid[0].length;


        // 遍历网格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 只遍历陆地
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 如果是因为超出grid的边界，那么此时周长+1
        if (!arrivedSide(grid, i, j)) {
            return 1;
        }
        // 如果是遍历到了海洋，此时周长也可以+1
        if (grid[i][j] == 0) {
            return 1;
        }
        // 如果是已经标记过的陆地，返回0，此时没有达到边缘，不构成边，所以不影响周长的计算
        if (grid[i][j] == 2) {
            return 0;
        }
        // 如果是陆地，先标记当前陆地已经被访问过了
        grid[i][j] = 2;
        // 按照上下左右4个方向进行dfs
        return dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
    }

    /**
     * 当前网格(i,j)处于grid的范围内返回true
     * 否则返回false
     */
    private boolean arrivedSide(int[][] grid, int i, int j) {
        return (0 <= i && i < grid.length) && (0 <= j && j < grid[0].length);
    }
}