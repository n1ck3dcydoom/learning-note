package daimasuixianglu._9_dp;

import java.util.Arrays;
import java.util.List;

public class _16_WordBreak {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "pen");
        System.out.println(wordBreak("applepenapple", words));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        // 使用前i个单词，恰好组成字符串s，且每个单词可以重复使用，完全背包问题

        // 第一步、定义dp数组
        // 定义dp[i]表示能否使用给定的单词构成长度为i的字符串
        boolean[] dp = new boolean[s.length() + 1];

        // 第二步、初始值
        // 字符串长度为0可以用字典给定单词组成，不使用任何单词
        dp[0] = true;

        // 第三步、状态转移
        // 考虑长度为i的字符串，如果长度为j的字符串能够由字典中的单词构成（j<i），且[j,i]也是字典里面的单词
        // 则dp[i]=dp[j]
        // 否则dp[i]=false
        // 需要检查容量i下所有的物品，所以先遍历背包，再遍历物品

        // 遍历背包
        for (int i = 1; i <= s.length(); i++) {
            // 从后往前遍历物品
            for (int j = i; j >= 0; j--) {
                // 如果找到了dp[j]==true，且s.substring(j,i)也是一个字典里面的单词
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // 从后往前寻找，找到了最近的一个true就可以不用往前继续寻找了
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
