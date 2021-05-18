package leetcode.tree.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/18 23:48
 **/
public class _208_ImplementTriePrefixTree {
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    class Trie {

        // 对于字符串建立字典树，每个节点就可能有26个子节点
        private Trie[] childs;
        // 当前节点是否是一个路径的终点
        boolean ended;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            // 初始化
            this.childs = new Trie[26];
            this.ended = false;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie p = this;
            // 遍历输入字符串
            for (char c : word.toCharArray()) {
                // 找到当前根节点下，有没有当前字符的子节点
                // 如果没有当前字符的子节点，则新建出这个子节点
                if (p.childs[c - 'a'] == null) {
                    p.childs[c - 'a'] = new Trie();
                }
                // 当前根节点指向这个被新建的子节点，或者指向这个已有的子节点
                p = p.childs[c - 'a'];
            }
            // 遍历完输入串之后，标识当前节点为一个路径的终点
            p.ended = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            //            Trie p = this;
            //            // 遍历输入字符串
            //            for (char c : word.toCharArray()) {
            //                // 如果遍历到某个字符的对应的路径的子节点不存在时，表明字典树中不含有这个字符串
            //                if (p.childs[c - 'a'] == null) {
            //                    return false;
            //                }
            //                // p指针移动指向当前字符后面的子节点字符
            //                p = p.childs[c - 'a'];
            //            }
            //            // 如果遍历完输入串后，最后一个子节点并不是一条路径的终点，说明字典树里面也不含有这个字符串
            //            // 否则字典树里含有当前输入串
            //            return p.ended;
            Trie p = searchPrefix(word);
            // 返回最后找到的字典树节点，如果这个节点不为null，且这个节点是个路径的终点
            // 则说明字典树里含有当前输入字符串
            return p != null && p.ended;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            //            Trie p = this;
            //            // 前缀查找
            //            for (char c : prefix.toCharArray()) {
            //                // 如果输入前缀中不存在某个子节点，则直接返回fasle
            //                if (p.childs[c - 'a'] == null) {
            //                    return false;
            //                }
            //                // 指针后移
            //                p = p.childs[c - 'a'];
            //            }
            //            // 如果遍历完输入前缀后都没有返回false，那么无论最后一个字符节点是否是一条路径的终点
            //            // 都说明字典树含有当前输入前缀 (不一定是一个完整的字符串索引)
            //            return true;
            Trie p = this.searchPrefix(prefix);
            // 返回最后找到的字典树节点，如果这个节点不为null，且这个节点是个路径的终点
            // 无论这个节点是不是路径的终点，都说明字典树里含有输入前缀
            return p != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie p = this;
            // 前缀查找
            for (char c : prefix.toCharArray()) {
                // 如果输入前缀中不存在某个子节点，返回null
                if (p.childs[c - 'a'] == null) {
                    return null;
                }
                // 指针后移
                p = p.childs[c - 'a'];
            }
            // 返回最后一个找到的字典树节点指针
            return p;
        }
    }

}