package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _14_NQueens {

    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }
        dfs(grid, n, 0);
        return res;
    }

    private void dfs(char[][] grid, int count, int index) {
        // 如果皇后已经全部合法的放置到棋盘上，结束搜索
        if (count == 0) {
            List<String> list = new ArrayList<>();
            for (char[] chars : grid) {
                list.add(new String(chars));
            }
            res.add(list);
            return;
        }
        // 搜索当前行上面的每个位置
        for (int i = 0; i < grid[index].length; i++) {
            // 检查当前位置所在列和对角线上是否有其他皇后
            if (check(grid, index, i)) {
                // 做出选择
                grid[index][i] = 'Q';
                // 搜索下一层
                dfs(grid, count - 1, index + 1);
                // 撤销选择
                grid[index][i] = '.';
            }
        }
    }

    private boolean check(char[][] grid, int x, int y) {
        // 检查(x,y)如果放上皇后，是否合法
        // 检查第x行
        // 不用检查行，因为一行只能有一个Q

        // 检查第y列，剪枝x后面的还没有遍历到，不需要检查
        for (int i = 0; i < x; i++) {
            if (grid[i][y] == 'Q') {
                return false;
            }
        }
        // 检查左上方对角线到(x,y)
        // 剪枝，因为(x,y)右下角的对角线还没有遍历到，不需要检查
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == 'Q') {
                return false;
            }
        }
        // 检查右上对角线到(x,y)
        // 剪枝，因为(x,y)左下角的对角线还没有遍历到，不需要检查
        for (int i = x - 1, j = y + 1; i >= 0 && j < grid.length; i--, j++) {
            if (grid[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
