package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/17 0:15
 **/
public class _212_WordSearchII {

    // dfs深搜方向数组
    private int[][] way = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
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

    private boolean dfs(char[][] board, String word, int index, int x, int y) {
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

    private boolean inSide(char[][] board, int x, int y) {
        return (0 <= x && x < board.length) && (0 <= y && y < board[0].length) && board[x][y] != '$';
    }

    /**
     * 使用前缀树优化
     */
    public List<String> findWords1(char[][] board, String[] words) {
        // 使用set去重
        HashSet<String> res = new HashSet<>();
        // 遍历所有单词，构建字典树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // 检查每个格子
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, i, j, res);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, Trie trie, int x, int y, HashSet<String> res) {
        // 超出边界
        if (!inSide(board, x, y)) {
            return;
        }
        char c = board[x][y];
        // 当前字符不在字典树当中
        if (!trie.children.containsKey(c)) {
            return;
        }
        // 如果达到了单词结尾
        Trie t = trie.children.get(c);
        if (!"".equals(t.word)) {
            res.add(t.word);
        }
        // 标记当前访问
        board[x][y] = '$';
        // 检查四个方向
        for (int i = 0; i < 4; i++) {
            int[] wayTo = way[i];
            dfs(board, t, x + wayTo[0], y + wayTo[1], res);
        }
        // 撤销选择
        board[x][y] = c;
    }

    class Trie {
        // 使用字符串直接保存前缀树中的单词，其他情况为空串
        private String word;
        private HashMap<Character, Trie> children;

        public Trie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        /**
         * 插入一个单词到前缀树
         */
        public void insert(String word) {
            Trie trie = this;
            // 遍历每个字符
            for (char c : word.toCharArray()) {
                if (!trie.children.containsKey(c)) {
                    trie.children.put(c, new Trie());
                }
                // 指向下一个前缀树节点
                trie = trie.children.get(c);
            }
            trie.word = word;
        }
    }
}