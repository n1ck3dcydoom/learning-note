package leetcode.dfs.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/31 19:58
 **/
public class _200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        // 遍历网格，跟遍历树一样，dfs，网格有上下左右4个方向，类似于树的左右子结点
        // 可以类比得出，网格其实就是一个4叉树

        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int m = grid[0].length;

        int res = 0;

        // 遍历网格
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 只遍历陆地
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    // 进行一次dfs搜索完从当前网格出发后所能形成的所有岛屿后，岛屿数量++
                    // 而且在遍历到陆地后把陆地标记为 '2'，避免重复遍历
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 二叉树的dfs是如何结束的？
        // if(root == null)
        //    return null;
        // 即遍历到当前节点的下一个节点，发现是null后，立马回退
        // 网格如何发现下一个节点是null呢，即通过网格的边缘确定，如果当前节点已经超出边缘了，则立马回退
        // 还有就是如果当前遍历的网格是海洋，也立即返回，只遍历所有的陆地

        // 如果超出边界，返回
        if (!this.arrivedSide(grid, i, j)) {
            return;
        }
        // 标记当前陆地已经被遍历过了
        grid[i][j] = '2';
        // 往4个方向进行dfs
        // 往下
        dfs(grid, i + 1, j);
        // 往上
        dfs(grid, i - 1, j);
        // 往左
        dfs(grid, i, j - 1);
        // 往右
        dfs(grid, i, j + 1);
    }

    /**
     * 当前网格(i,j)处于grid的范围内，且是陆地，返回true
     * 否则返回false
     */
    private boolean arrivedSide(char[][] grid, int i, int j) {
        return (0 <= i && i < grid.length) && (0 <= j && j < grid[0].length) && grid[i][j] == '1';
    }

}