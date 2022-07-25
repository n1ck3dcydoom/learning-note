package daimasuixianglu._9_dp;

/**
 * Created by n!Ck
 * Date: 2022/3/4
 * Time: 23:23
 * Description:
 */

public class _33_DistinctSubsequences {

    public int numDistinct(String s, String t) {
        // 动态规划

        // 第一步、定义dp[i][j]表示s以i结尾的子序列出现t的次数
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、状态转移方程
        // 考虑s以i结尾的子序列出现t的情况
        // 若s[i-1]==t[j-1]，如果选s[i]，那么有dp[i][j]=dp[i-1][j-1]
        // 如果不选s[i]，则由s[i-1]来和t[j]匹配，则dp[i][j]=dp[i-1][j]
        // 如果s[i-1]!=t[j-1]，则一定不选s[i]，由s[i-1]和t[j]匹配，有dp[i][j]=dp[i-1][j]

        // 第三步、初始值
        // 二维dp打表可以看到所有状态都是由dp[1][1]转移得到，遍历顺序从左往右，从上往下
        dp[0][0] = 1;
        // 第0行，即s为空串，匹配t
        // 除了t也是空串有1种方式，其他t情况全为0（非空串无法匹配空串）
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 0;
        }
        // 第0列，即s匹配，t为空串，
        // 无论s是什么，都可以把s变为空串匹配上t的空串，所以第0列全是1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // s[i]==t[i]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 选择s[i]匹配t[j]，由s[i-1]匹配t[j-1]
                    dp[i][j] += dp[i - 1][j - 1];
                    // 不选择s[i]匹配t[j]，则由s[i-1]匹配t[j]
                    dp[i][j] += dp[i - 1][j];
                } else {
                    // 由s[i-1]匹配t[j]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
