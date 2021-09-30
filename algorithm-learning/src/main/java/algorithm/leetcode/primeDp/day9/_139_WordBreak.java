package algorithm.leetcode.primeDp.day9;

import java.util.Arrays;
import java.util.List;

public class _139_WordBreak {

    public static void main(String[] args) {
        List<String> wordDict1 = Arrays.asList("apple", "pen");
        List<String> wordDict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak("applepenapple", wordDict1));
        System.out.println(wordBreak("catsandog", wordDict2));
        List<String> wordDict3 = Arrays.asList("aaaa", "aaa");
        System.out.println(wordBreak("aaaaaaa", wordDict3));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        // 动态规划

        int n = s.length();
        // 第一步、定义dp数组
        // 定义dp[n]表示s串以n结尾的字符是否能够被空格拆分为字典中的单词
        boolean[] dp = new boolean[n + 1];

        // 第二步、初始值
        // 哨兵0设置为true
        dp[0] = true;
        // 首字符是否出现在字典当中
        dp[1] = wordDict.contains(String.valueOf(s.charAt(0)));

        // 第三步、状态转移
        // 考虑s串以i结尾dp[i]，其状态由前一个dp[k]=true转移
        // 如果dp[k]=true，那么可以分为[0~k] [k+1,i]，检查[k+1,i]是否能够继续拆分为wordDic里面的单词
        //        for (int i = 1; i <= n; i++) {
        //            // 倒着往前找第一个出现的dp[k]=true
        //            for (int j = i - 1; j >= 0; j--) {
        //                if (dp[j] && !dp[i]) {
        //                    // 找到dp[k]=true后，被k分为两段[0~k],[k+1,i]
        //                    // 检查[k+1,i]是否是一个字典中的单词
        //                    String word = s.substring(j, i);
        //                    dp[i] = wordDict.contains(word);
        //                }
        //            }
        //        }

        // 优化一下
        // 倒着往前面找dp[k]的想法没错，只需要找到第一个就够了
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                String word = s.substring(j, i);
                if (dp[j] && wordDict.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}