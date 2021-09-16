package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/17 0:15
 **/
public class _212_WordSearchII {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        List<String> res = findWords(board, words);
        if (!res.isEmpty() && res.size() > 0) {
            for (String str : res) {
                System.out.print(str + " ");
            }
        }
    }

    // dfs深搜方向数组
    private static int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        // 暴力搜索每个单词是否存在
        for (String word : words) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    // 首字母相同才dfs遍历
                    if (board[i][j] == word.charAt(0)) {
                        if (dfs(board, word, 0, i, j)) {
                            // 避免重复添加
                            if (!res.contains(word)) {
                                res.add(word);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean dfs(char[][] board, String word, int index, int x, int y) {
        // 超出边界
        if (!inSide(board, x, y)) {
            return false;
        }
        // 路径等于word长度了
        if (index == word.length() - 1) {
            // 检查最后一个字符是否匹配
            return board[x][y] == word.charAt(index);
        }
        // 路径没有匹配上word的字符
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        // 标记访问
        board[x][y] = '$';
        // 检查四个方向
        for (int i = 0; i < 4; i++) {
            int[] wayTo = way[i];
            // 只要有一个方向找到了就返回true
            if (dfs(board, word, index + 1, x + wayTo[0], y + wayTo[1])) {
                // 撤销当前选择
                board[x][y] = word.charAt(index);
                return true;
            }
        }
        // 四个方向都没找到，回溯
        // 撤销当前选择
        board[x][y] = word.charAt(index);
        return false;
    }

    private static boolean inSide(char[][] board, int x, int y) {
        return (0 <= x && x < board.length) && (0 <= y && y < board[0].length) && board[x][y] != '$';
    }
}