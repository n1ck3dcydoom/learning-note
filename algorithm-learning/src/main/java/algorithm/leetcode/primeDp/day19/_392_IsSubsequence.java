package algorithm.leetcode.primeDp.day19;

public class _392_IsSubsequence {

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence1(s, t));
    }

    public static boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();

        // 第一步、定义dp数组
        // 定义dp[i][j]表示s以i结尾的子串在t以j结尾的子串中，最长的公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        // 预留s或者t为空串的情况

        // 第二步、初始值
        // 第一行，s串为空串
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }

        // 第三步、状态转移
        // 考虑s串以i结尾，t串以j结尾的时候
        // 如果s[i]==t[j]，且dp[i-1][j-1]==true，则能够在s[i-1]t[j-1]的后面构成新的子序列
        // dp[i][j]=dp[i-1][j-1]+1
        // 如果s[i]!=t[j]，则说明在t[j-1]后面加上t[j]也无法包含s[i]结尾的子序列
        // 则说明t[j]没法包含s[i]，需要去掉t[j]
        // dp[i][j]=dp[i][j-1]

        for (int i = 1; i <= m; i++) {
            // s是t的子序列，打表只用填写右上部分
            for (int j = i; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        // 如果s在t的最长公共子序列长度等于s，则说明s是t的子序列
        return m == dp[m][n];
    }

    public static boolean isSubsequence1(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        // 双指针遍历
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
}