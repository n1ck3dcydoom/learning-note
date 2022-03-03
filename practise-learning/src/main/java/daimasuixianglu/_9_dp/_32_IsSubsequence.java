package daimasuixianglu._9_dp;

public class _32_IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        // 又是子序列问题，动态规划伺候

        // 第一步、定义dp数组
        // 定义dp[i][j]表示s以i结尾，t以j结尾的最长公共子序列长度
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、状态转移方程
        // 考虑s以i结尾，t以j结尾
        // 如果s[i]==t[j]，由s[i-1]和t[j-1]转移，即dp[i][j]=dp[i-1][j-1]+1
        // 如果s[i]!=t[j]，相当于t要删除j（此时s不能动，区别于最长公共子序列的状态转移方程）即dp[i][j]=dp[i][j-1]

        // 第三步、初始值
        // 由状态转移方程可以得到，dp[i][j]由dp[i-1][j-1]转移，左上角方向，遍历顺序从左到右，从上到下
        // 所有dp值都是由dp[0][0]计算得到

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return m == dp[m][n];
    }
}
