package daimasuixianglu._9_dp;

public class _29_LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence(s1, s2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        // 最长公共子序列，动态规划

        // 第一步、定义dp数组
        // 定义dp[i][j]表示字符串1以i结尾，字符串2以j结尾的最长公共子序列长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、状态转移方程
        // 考虑字符串1以i结尾，字符串2以j结尾
        // 如果chars1[i]==chars2[j]，则由i-1和j-1转移，dp[i][j]=dp[i-1][j-1]+1
        // 如果chars1[i]!=chars2[j]，则固定i，查找0~j最大的结果；固定j，查找0~i最大的结果，取二者的最大值
        // 即如果i!=j，那么查找以i，j-1结尾的最长公共子序列；或者查找以j，i-1结尾的最长公共子序列

        // 第三步、初始值
        // 二维dp的初始值通常为第0行，第0列，打表初始化
        // 第0行，即字符串1不选取字符，字符串2遍历完
        // for (int i = 0; i <= n; i++) {
        //     dp[0][i] = 0;
        // }
        // 第0列，即字符串1遍历完，字符串2不选取字符
        // for (int i = 0; i <= m; i++) {
        //     dp[i][0] = 0;
        // }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}