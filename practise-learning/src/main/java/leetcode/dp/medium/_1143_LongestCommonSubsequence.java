package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/25 15:59
 **/
public class _1143_LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence(s1, s2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        // 动态规划常规套路

        int m = text1.length();
        int n = text2.length();
        // 第一步、定义dp数组，两个字符串，一般都是从二维朴素dp开始下手
        // 定义dp[m][n]表示s1中前m个字符，和s2中前n个字符的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、初始值
        dp[0][0] = 0;
        // 第一列和第一行都可以初始化，即s1为空或者s2为空时
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        // 第三步、递推表达式
        // 考虑s1前i个元素和s2前j个元素时
        // dp[i][j]有三个状态可以转移得到
        // 1、dp[i-1][j-1]
        // 2、dp[i][j-1]
        // 3、dp[i-1][j]
        // 如果s1[i]==s2[j]，则说明以i-1和j-1结尾的两个子串其公共子序列长度可以+1
        // 即dp[i][j] = dp[i-1][j-1]+1
        // 如果s1[i]!=s2[j]，则说明不能够和前面i-1和j-1构成更长的公共子序列
        // 那么dp[i][j]由左dp[i][j-1]和上方dp[i-1][j]的状态转移
        // 即dp[i][j] = max(dp[i][j-1], dp[i-1][j])

        // 开始打表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }
}