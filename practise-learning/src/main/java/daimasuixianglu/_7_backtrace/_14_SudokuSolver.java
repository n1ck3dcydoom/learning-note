package daimasuixianglu._7_backtrace;

public class _14_SudokuSolver {

    public static void main(String[] args) {

        char[][] grid = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(grid);
    }

    public static void solveSudoku(char[][] board) {
        dfs(board);
        int a = 1;
    }

    /**
     * 递归函数有返回值？找到某条路径满足条件后，立刻返回
     */
    private static boolean dfs(char[][] grid) {
        // 搜索横向和纵向每一个空网格
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == grid[i][j]) {
                    // 尝试填入1~9当中的每个数字
                    for (int k = 1; k <= 9; k++) {
                        if (check(grid, i, j, (char) (k + '0'))) {
                            // 做出选择
                            grid[i][j] = (char) (k + '0');
                            // 递归搜索
                            if (dfs(grid)) {
                                return true;
                            }
                            // 撤销选择
                            grid[i][j] = '.';
                        }
                    }
                    // 找完了9个数字都没法填入当前空格，则说明当前路径无解
                    return false;
                }
            }
        }
        // 遍历完所有网格，都没有中途返回false，则说明找到了某条路径满足条件
        return true;
    }

    private static boolean check(char[][] grid, int x, int y, char n) {
        // 检查行
        for (int i = 0; i < 9; i++) {
            if (grid[x][i] == n) {
                return false;
            }
        }
        // 检查列
        for (int i = 0; i < 9; i++) {
            if (grid[i][y] == n) {
                return false;
            }
        }
        // 检查单元格
        // 如何确定在哪一个单元格上
        // 0 1 2
        // 3 4 5
        // 6 7 8
        // 将二维数组降维到一维数组
        // 有(x,y) -> x/n * n + y/n
        // 例如(3,5) -> 3/3 * 3 + 5/3 -> 1*3 + 1 = 4 即第4号单元格上
        // 单元格的遍历起点如何确定？
        // 例如(3,5) -> 第4号单元格上
        // 遍历的起点横坐标 row=x/3*3，纵坐标col=y/3*3
        // 即4号单元格的起点为 (3/3*3=3, 5/3*2=3) -> (3,3)
        int row = (x / 3) * 3, col = (y / 3) * 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (grid[i][j] == n) {
                    return false;
                }
            }
        }
        return true;
    }
}
