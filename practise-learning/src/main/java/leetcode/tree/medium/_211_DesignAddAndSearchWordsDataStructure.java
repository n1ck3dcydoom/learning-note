package leetcode.tree.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/19 22:38
 **/
public class _211_DesignAddAndSearchWordsDataStructure {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        //        System.out.println(wordDictionary.search("pad"));
        //        System.out.println(wordDictionary.search("bad"));
        //        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }


    /**
     * 字典树 Trie树
     */
    static class WordDictionary {

        // 当前节点是否是一个完整单词
        private boolean word;
        // 指向下一个节点
        private WordDictionary[] children;

        public WordDictionary() {
            this.word = false;
            // 26个字母
            this.children = new WordDictionary[26];
        }

        public void addWord(String word) {
            WordDictionary t = this;
            // 遍历插入单词的所有字符
            char[] chars = word.toCharArray();
            for (char c : chars) {
                // 当前字符节点不存在，创建一个新的Trie树节点
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new WordDictionary();
                }
                t = t.children[c - 'a'];
            }
            // 设置当前Trie节点为单词结尾
            t.word = true;
        }

        public boolean search(String word) {
            //            return this.search0(word);
            return this.dfs(word, this, 0);
        }

        /**
         * 查找当前单词是否存在于Trie树当中
         *
         * @param word 单词
         * @return true 单词存在于Trie树  false 单词不存在于Trie树
         */
        private boolean search0(String word) {
            WordDictionary t = this;
            char[] chars = word.toCharArray();

            for (char c : chars) {
                if (t.children[c - 'a'] == null) {
                    return false;
                }
                t = t.children[c - 'a'];
            }
            return t.word;
        }

        private boolean dfs(String word, WordDictionary root, int index) {
            if (root == null) {
                return false;
            }
            boolean res = false;
            char c = word.charAt(index);
            // 如果是"."  遍历所有children节点
            if ('.' == c) {
                for (WordDictionary w : root.children) {
                    if (w == null) {
                        continue;
                    }
                    // 继续搜索children中存在的节点
                    if (index == word.length() - 1) {
                        res |= w.word;
                    } else {
                        res |= dfs(word, w, index + 1);
                    }
                }
                // 如果.都匹配不到字符
                return res;
            }
            // 不是 "."  继续往下搜索
            WordDictionary t = root.children[c - 'a'];
            // 当前Trie节点不存在
            if (t == null) {
                return false;
            }
            if (index == word.length() - 1) {
                return t.word;
            }
            return dfs(word, t, index + 1);
        }
    }
}