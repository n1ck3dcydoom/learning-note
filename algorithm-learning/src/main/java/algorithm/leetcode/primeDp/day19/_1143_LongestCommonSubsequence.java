package algorithm.leetcode.primeDp.day19;

public class _1143_LongestCommonSubsequence {

    public static void main(String[] args) {
        String s = "abcde";
        String t = "ace";
        System.out.println(longestCommonSubsequence(s, t));
    }

    public static int longestCommonSubsequence(String s, String t) {
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
        // 第一列，t串为空串
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 如果s[i]=t[j]，dp[i][j]=dp[i-1][j-1]+1
        // 如果s[i]!=t[j]，则说明t[j]不包含s[i]的子串
        // 或者s[i]不包含t[j]的子串
        // 此时长度由t前面的t-1决定和s前面的s-1最大值决定
        // dp[i][j]=max(dp[i][j-1], dp[i-1][j])
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}