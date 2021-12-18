package leetcode.dfs.medium;

public class _419_BattleshipsBoard {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        System.out.println(countBattleships1(board));
    }

    public static int countBattleships(char[][] board) {
        int res = 0;
        // 只扫描头部
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                // 如果是X，则判断是否是头部
                // 头部的定义：左侧和上侧都不是X
                if ('X' == c) {
                    if (i == 0) {
                        if (j == 0) {
                            res++;
                        } else if (board[i][j - 1] != 'X') {
                            res++;
                        }
                    } else {
                        if (j == 0) {
                            if (board[i - 1][j] != 'X') {
                                res++;
                            }
                        } else if (board[i - 1][j] != 'X' && board[i][j - 1] != 'X') {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static int countBattleships1(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] board, int i, int j) {
        if (overSide(board, i, j) || board[i][j] != 'X') {
            return;
        }
        // 标记访问
        board[i][j] = '.';
        // 只搜索下侧和右侧
        if (!overSide(board, i + 1, j) && board[i + 1][j] == 'X') {
            dfs(board, i + 1, j);
        } else if (!overSide(board, i, j + 1) && board[i][j + 1] == 'X') {
            dfs(board, i, j + 1);
        }
    }

    private static boolean overSide(char[][] board, int i, int j) {
        return i < 0 || j < 0 || i == board.length || j == board[0].length;
    }
}
