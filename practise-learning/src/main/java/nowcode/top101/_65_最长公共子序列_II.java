package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 16:02
 * Description:
 */

public class _65_最长公共子序列_II {

    public static void main(String[] args) {
        System.out.println(LCS("1A2C3D4B56", "B1D23A456A"));
    }

    public static String LCS(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        // 第一步定义 dp 数组,定义 dp[i][j] 表示 s1 以 i 结尾,s2 以 j 结尾的最长公共子序列
        String[][] dp = new String[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = "";
            }
        }

        // 第二步初始值,若 s1==null 或者 s2==null,即第一行或者第一列都是 0

        // 第三步状态转移方程,考虑 s1 以 i 结尾,s2 以 j 结尾时,dp[i][j]
        // 如果 s1[i] == s2[j] 则由 dp[i-1][j-1] 转移
        // 如果 s1[i] != s2[j] 则 s1[i] 不选,或者 s2[j] 不选,即 max(dp[i-1][j], dp[i][j-1])
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + cs1[i - 1];
                } else {
                    dp[i][j] = dp[i - 1][j].length() >= dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[m][n] == "" ? "-1" : dp[m][n];
    }
}
