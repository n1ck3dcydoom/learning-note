package leetcode.dfs.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 78. 单词搜索
 * @date 2021/5/21 14:58
 **/
public class _79_WordSearch {

    //    public static void main(String[] args) {
    //        char[][] board = new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}};
    //        String word = "abcb";
    //        System.out.println(exist(board, word));
    //    }

    private boolean res = false;

    public boolean exist(char[][] board, String word) {
        // 有点类似岛屿问题
        // 直接dfs搜索

        // 行数
        int m = board.length;
        // 列数
        int n = board[0].length;
        // 辅助数组，保存当前格子是否已经被路径访问了
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res) {
                    break;
                }
                // 如果当前格子不是单词的开头，直接跳过
                if (board[i][j] == word.charAt(0)) {
                    dfs(board, visited, i, j, word, 0);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, String word, int index) {
        // 找到所有退出dfs的情况

        // 超出边界，或者当前格子已经被访问过
        if (!arrivedSide(board, i, j) || visited[i][j]) {
            return;
        }
        // index还没有等于单词的长度，但是当前格子已经不是word的下一个字符了
        if (index < word.length() && board[i][j] != word.charAt(index)) {
            return;
        }
        // 找到单词了！
        // index等于单词的长度，且当前格子的字符等于单词的最后一个字符
        if (index == word.length() - 1 && board[i][j] == word.charAt(index)) {
            res = true;
        }
        // 还没有找到结果，且当前格子等于当前单词的索引，才继续dfs，否则就返回
        if (!res && board[i][j] == word.charAt(index)) {
            // 标记当前格子已经被访问了
            visited[i][j] = true;
            // 往四个方向上进行dfs，有一个方向上找到了就返回true
            dfs(board, visited, i + 1, j, word, index + 1);
            dfs(board, visited, i - 1, j, word, index + 1);
            dfs(board, visited, i, j + 1, word, index + 1);
            dfs(board, visited, i, j - 1, word, index + 1);
            // 回溯需要重置访问状态
            visited[i][j] = false;
        }
    }

    private boolean arrivedSide(char[][] board, int i, int j) {
        return (0 <= i && i < board.length) && (0 <= j && j < board[0].length);
    }
}