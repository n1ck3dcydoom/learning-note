package leetcode.simulate.medium;

/**
 * hash存储，暴力扫描
 */
//public class _677_MapSumPairs {
//
//    private HashMap<String, Integer> hash;
//
//    public _677_MapSumPairs() {
//        this.hash = new HashMap<>();
//    }
//
//    public void insert(String key, int val) {
//        hash.put(key, val);
//    }
//
//    public int sum(String prefix) {
//        if (hash.isEmpty()) {
//            return 0;
//        }
//        int sum = 0;
//        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
//            if (entry.getKey().startsWith(prefix)) {
//                sum += entry.getValue();
//            }
//        }
//        return sum;
//    }
//}

/**
 * trie树
 */
public class _677_MapSumPairs {
    static class TrieNode {
        /**
         * 单词节点的权重
         */
        int val = 0;

        /**
         * next指针
         */
        TrieNode[] next = new TrieNode[26];
    }

    /**
     * 根节点
     */
    TrieNode root;

    public _677_MapSumPairs() {
        this.root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode p = this.root;
        // 遍历输入串
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            // 对应字符节点不存在则创建
            if (p.next[index] == null) {
                p.next[index] = new TrieNode();
            }
            // 移动root指针到对应字符节点
            p = p.next[index];
        }
        // 到达末尾，更新单词的权重
        // 朴素字典树需要更新是否是单词的标记位 isWord = true
        p.val = val;
    }

    public int sum(String prefix) {
        TrieNode p = this.root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            p = p.next[index];
            // 对应字符节点不存在，说明字典树里面不存在这个前缀
            if (p == null) {
                return 0;
            }
        }
        // 从prefix的最后一个字符节点开始往后面搜索是否有完整的单词
        return search(p, 0);
    }

    private int search(TrieNode node, int res) {
        if (node == null) {
            return 0;
        }
        // 如果搜索到一个完整单词，则记录权重
        if (node.val > 0) {
            res += node.val;
        }
        // 搜索所有可能的分支
        for (TrieNode p : node.next) {
            if (p != null) {
                res = search(p, res);
            }
        }
        return res;
    }
}