package leetcode.dfs.medium;

public class _79_WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board, word1));
        // System.out.println(exist(board, word2));
        // System.out.println(exist(board, word3));
    }

    public static boolean exist(char[][] board, String word) {
        // dfs深搜
        // 访问数组
        boolean[][] visited = new boolean[board.length][board[0].length];
        // 只从字母开头字符开始寻找
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, visited, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, boolean[][] visited, String word, int index, int x, int y) {
        // 如果超出边界、已经访问过、不等于需要寻找的字符
        if (!inSide(board, visited, x, y)) {
            return false;
        }
        if (index == word.length() - 1) {
            return board[x][y] == word.charAt(index);
        }
        // 没有匹配上
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        // 标记当前格子已经被访问过
        visited[x][y] = true;

        int[][] search = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++) {
            int[] to = search[i];
            // 只要有一个方向找到下一个字符了，就返回true
            if (dfs(board, visited, word, index + 1, x + to[0], y + to[1])) {
                return true;
            }
        }
        // 回溯，撤销选择
        visited[x][y] = false;
        return false;
    }

    private static boolean inSide(char[][] board, boolean[][] visited, int x, int y) {
        return (0 <= x && x < board.length) && (0 <= y && y < board[0].length) && !visited[x][y];
    }
}
