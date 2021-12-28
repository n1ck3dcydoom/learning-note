package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _472_ConcatenatedWords {

    public static void main(String[] args) {
        String[] words = new String[]{"cat", "cats", "catsdogcats", "dog",
                "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> res = findAllConcatenatedWordsInADict1(words);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        // 直接dfs搜索
        List<String> res = new ArrayList<>();
        for (String word : words) {
            dfs(res, words, word, word, 0);
        }
        return res;
    }

    private static void dfs(List<String> res, String[] words, String raw, String target, int deep) {
        if ("".equals(target) && deep >= 2) {
            System.out.println(raw + " " + deep);
            res.add(raw);
        }
        for (String word : words) {
            // 跳过自己
            if (word.equals(raw)) {
                continue;
            }
            if (target.startsWith(word)) {
                // 加入选择
                target = target.substring(word.length(), target.length());
                // 继续dfs搜索
                dfs(res, words, raw, target, deep + 1);
                // 撤销选择
                target = word + target;
            }
        }
    }

    private static Trie root = new Trie();

    public static List<String> findAllConcatenatedWordsInADict1(String[] words) {
        // 直接dfs搜索内存爆了
        // 使用字典树优化单词查找
        // 对给定字典排序，长的单词肯定由前面短的单词构成
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());

        List<String> res = new ArrayList<>();
        // 搜索每个单词
        for (String word : words) {
            // 空串不考虑
            if ("".equals(word)) {
                continue;
            }
            // 是一个连接词则加入结果集
            if (dfs(word, 0)) {
                res.add(word);
            }
            // 不是连接词则加入字典树，供其他连接词查找
            // 连接词不用放入字典树，即使他是另外一个更长单词的连接词组成
            // 例如 abcdab ab cd abcd
            // 排序后有 ab cd abcd abcdab
            // 对于abcd是一个连接词，由ab + cd构成，他也是abcdab的一个连接词组成，但是它不需要放入字典树
            // 因为abcdab可以由 ab + cd + ab更短的连接词构成
            else {
                insert(word);
            }
        }
        return res;
    }

    private static boolean dfs(String word, int pos) {
        // dfs搜索当前单词的每个字符，从字典树里面查找是否有短单词
        // 搜索到末尾则结束
        if (pos == word.length()) {
            return true;
        }
        // 从字典树里面查找单词
        Trie node = root;
        for (int i = pos; i < word.length(); i++) {
            char c = word.charAt(i);
            // 如果当前不是一个叶子节点，继续往下查找
            node = node.children[c - 'a'];
            // 字典树中不存在当前前缀
            if (node == null) {
                return false;
            }
            // 如果当前是一个叶子节点
            if (node.end) {
                // 继续dfs搜索后面部分，直到搜索到末尾或者字典树中不存在某个前缀
                if (dfs(word, i + 1)) {
                    return true;
                }
            }

        }
        return false;
    }

    private static void insert(String word) {
        // 往字典树里面插入一个单词
        Trie node = root;
        for (char c : word.toCharArray()) {
            // 前缀树中不存在此前缀则创建出来
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie(c);
            }
            node = node.children[c - 'a'];
        }
        // 记此节点为叶子节点(一个完整单词)
        node.end = true;
    }

    static class Trie {
        boolean end;
        char c;
        Trie[] children;

        public Trie() {
            this.end = false;
            // 最多26个字母构成单词开头
            this.children = new Trie[26];
        }

        public Trie(char c) {
            this.end = false;
            this.c = c;
            this.children = new Trie[26];
        }
    }
}
