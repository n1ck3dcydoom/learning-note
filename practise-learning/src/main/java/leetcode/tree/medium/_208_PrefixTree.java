package leetcode.tree.medium;

import java.util.HashMap;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/17 0:40
 **/
public class _208_PrefixTree {

    /**
     * 前缀树每个节点需要判断是否是一个单词的结尾
     */
    private boolean isWord;
    /**
     * 前缀树每个节点需要包含指向下一个结点的信息
     */
    private HashMap<Character, _208_PrefixTree> children;

    /**
     * Initialize your data structure here.
     */
    public _208_PrefixTree() {
        this.isWord = false;
        this.children = new HashMap<>();
    }

    /**
     * 向前缀树中插入一个单词
     */
    public void insert(String word) {
        // 指向自己的指针;
        _208_PrefixTree trie = this;
        // 向前缀树插入一个单词是，遍历单词的每个字符
        for (char c : word.toCharArray()) {
            // 如果不存在当前字符，则为当前字符新建一个前缀树节点
            if (!trie.children.containsKey(c)) {
                trie.children.put(c, new _208_PrefixTree());
            }
            // 移动指针指向新的节点
            trie = trie.children.get(c);
        }
        // 遍历完成后，把最后一个节点记为单词的结尾
        trie.isWord = true;
    }

    /**
     * 在前缀树中搜索一个单词是否存在
     */
    public boolean search(String word) {
        _208_PrefixTree trie = this.searchPrefix(word);
        // 检查这个单词的前缀是否存在，且最后一个节点是否是单词的结尾
        return trie != null && trie.isWord;
    }

    /**
     * 检查是否有单词以某个前缀开始
     */
    public boolean startsWith(String prefix) {
        _208_PrefixTree trie = this.searchPrefix(prefix);
        // 只要这个前缀存在，无论是否是单词结尾，都说明前缀树中包含当前前缀
        return trie != null;
    }

    /**
     * 检索一个前缀是否存在
     */
    private _208_PrefixTree searchPrefix(String word) {
        _208_PrefixTree trie = this;
        for (char c : word.toCharArray()) {
            if (trie.children.containsKey(c)) {
                // 指针后移，检查下一个前缀树节点
                trie = trie.children.get(c);
            } else {
                return null;
            }
        }
        // 如果存在当前前缀，则返回最后一个前缀树节点
        return trie;
    }
}