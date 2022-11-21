package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 14:15
 * Description:
 */

public class _59_N皇后问题 {

    private static int count = 0;

    public static void main(String[] args) {
        Nqueen(9);
        System.out.println(count);
    }

    public static int Nqueen(int n) {
        // 从第 1 行开始搜索
        int[][] grid = new int[n][n];
        dfs(grid, 0);
        return count;
    }

    private static void dfs(int[][] grid, int x) {
        // 遍历完所有行之后,则说明找到了一种可行解
        if (x == grid.length) {
            count++;
            return;
        }
        // 遍历当前行的每个元素
        for (int i = 0; i < grid.length; i++) {
            // 剪枝,检查当前位置是否能够放下 Q
            if (!check(grid, x, i)) {
                continue;
            }
            // 当前格子尝试放下 Q
            grid[x][i] = 1;
            // 递归搜索下一行
            dfs(grid, x + 1);
            // 撤销当前的 Q
            grid[x][i] = 0;
        }
    }

    private static boolean check(int[][] grid, int x, int y) {
        int n = grid.length;
        // 检查行,因为遍历的顺序是从左往右,从上往下,所以 [x, y] 后面和下面的不用检查
        for (int i = 0; i < y; i++) {
            if (grid[x][i] == 1) {
                return false;
            }
        }
        // 检查列,因为遍历的顺序是从左往右,从上往下,所以 [x, y] 后面和下面的不用检查
        for (int i = 0; i < x; i++) {
            if (grid[i][y] == 1) {
                return false;
            }
        }
        // 检查左上右下对角线,因为遍历的顺序是从左往右,从上往下,所以 [x, y] 后面和下面的不用检查
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }
        // 检查右上左下对角线,因为遍历的顺序是从左往右,从上往下,所以 [x, y] 后面和下面的不用检查
        for (int i = x - 1, j = y + 1; i >= 0 && j < n; i--, j++) {
            if (grid[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
}
