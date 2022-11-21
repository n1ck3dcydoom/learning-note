package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 16:20
 * Description:
 */

public class _66_最长公共子串 {

    public static void main(String[] args) {
        System.out.println(LCS("1AB2345CD", "12345EF"));
    }

    public static String LCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();
        // 第一步定义 dp 数组,定义 dp[i][j] 表示 s1 以 i 结尾,s2 以 j 结尾时的最长公共子串
        String[][] dp = new String[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = "";
            }
        }

        // 第二步初始值,s1==null 或者 s2==null 时,最长公共子串都为空串
        // 即第一行和第一列为默认值空串

        // 第三步状态转移方程,考虑 s1 以 i 结尾,s2 以 j 结尾时 dp[i][j]
        // 如果 s1[i] == s2[j] 则由 dp[i-1][j-1] 转移得到
        // 如果 s1[i] != s2[j] 则 dp[i][j] = 0,子串必须要相等才行,不相等则置空
        String res = "";
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + cs1[i - 1];
                    if (dp[i][j].length() > res.length()) {
                        res = dp[i][j];
                    }
                }
            }
        }
        return res;
    }
}
