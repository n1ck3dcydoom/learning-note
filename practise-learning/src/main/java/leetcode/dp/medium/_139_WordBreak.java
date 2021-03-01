package leetcode.dp.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/1 22:52
 **/
public class _139_WordBreak {

    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> dic = new ArrayList<>();
        dic.add("aaa");
        dic.add("aaaa");
        System.out.println(wordBreak(s, dic));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        // 朴素dp

        // 题目中求的是能否拆分成若干个单词，则dp数组里面就保存boolean类型

        // 很容易想到，定义dp[i]表示，以下标i结尾的子数组恰好能够被拆分为出现在字典中的单词
        // 如何由前面的状态推出dp[i]呢？
        // l e e t c o d e k k     "leet", "code"
        // 0 1 2 3 4 5 6 7 8 9
        // 由于需要刚好拆分为字典中的单词，那么下标i对应的字母，一定是字典中某个单词的结尾项
        // 找到这个结尾项的单词后i = 7，往前减去单词的长度k = 4
        // 得到dp[i-k]，如果dp[i-k] = dp[3] = true，即这个单词前面的子串也能够拆分为字典中的单词
        // 且从i-k+1到i刚好能够组成这个单词，那么dp[i] = true

        // 从上面的分析可以得知，递推表达式: dp[i] = dp[j] && j+1到i能够组成单词，单词长度=i-j

        // 题目中说了非空字符串
        int n = s.length();

        // 定义dp数组，长度加1，dp[0]作为初始值
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // 遍历s串
        for (int i = 1; i <= n; i++) {
            // 遍历字典，通过substring找到以i下标结尾的字母单词，然后检查i-j能否构成这个单词
            for (int j = 0; j < i; j++) {
                // 首先i-j必须得是true，这样才会判断i-j到i能否构成单词
                if (dp[j]) {
                    // 从s中截取下标j到i的子串，左闭右开
                    // l e e t c o d e
                    // 0 1 2 3 4 5 6 7
                    // 1 2 3 4 5 6 7 8
                    // 如果i=8，当j遍历到4的时候，substring(4,8)截取的就是code
                    // substring 返回4到8-1的新串
                    if (wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                    // 这里的else会导致已经计算好的dp[j]再次被修改
                    // 其实如果没有break的话，计算出来的j不是从0开始遍历的，表明j在中途可能和j到i构成了单词
                    // 但是j前面的状态是错误的
                    // 如果当我们已经找到j到i所组成的单词时，应该直接break退出j的循环
                    // 避免再次修改dp[j]
                    //                    else {
                    //                        dp[i] = false;
                    //                    }
                }
            }
        }
        return dp[n];
    }
}