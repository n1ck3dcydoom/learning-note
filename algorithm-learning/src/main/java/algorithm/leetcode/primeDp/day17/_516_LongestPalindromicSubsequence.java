package algorithm.leetcode.primeDp.day17;

public class _516_LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 第一步、定义dp数组
        // 定义dp[i][j]表示从i开始到j的最长回文子序列长度
        int[][] dp = new int[n][n];

        // 第二步、初始值
        // 一个字符肯定能构成回文子序列
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 第三步、状态转移
        // 考虑dp[i][j]的状态转移情况

        // 当s[i]!=s[j]的时候，和dp[i+1][j-1]不能构成新的回文子序列
        // 取dp[i+1][j]和dp[i][j-1]的较大值
        // 反正加上s[i]和s[j]后也会因为无法构成新回文串而删掉s[i][j]，总长度与之前不发生变化

        // 当s[i]==s[j]的时候，和dp[i+1][j-1]能够构成新的回文子序列
        // dp[i][j] = dp[i+1][j-1] + 2

        // 实际打表的时候，由于 0 <= i <= j < n
        // 只需要填写右上部分表格
        // 根据状态转移方程可以看到，dp[i][j]的状态实际上和dp[i+1]下一行的状态相关
        // 所以填表的时候需要从最后一行开始往前

        for (int i = n - 2; i >= 0; i--) {
            // j=i作为初始状态计算了，j从i+1开始遍历
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }
        // 包含整个字符串的最长回文子序列
        return dp[0][n - 1];
    }
}
