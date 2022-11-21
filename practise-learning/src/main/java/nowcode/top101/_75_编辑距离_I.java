package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 14:58
 * Description:
 */

public class _75_编辑距离_I {

    public int editDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();
        // 第一步定义 dp 数组,定义 dp[i][j] 表示 s1[i] 结尾和 s2[j] 结尾的编辑距离
        int[][] dp = new int[m + 1][n + 1];

        // 第二步初始值,若 s1 为空串,则由 s1 变为 s2 每次增加一个字符
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        // 同理若 s2 为空串,则由 s1 变为 s2 每次减少一个字符
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        // 第三步状态转移方程,考虑以 s1[i] 结尾和 s2[j] 结尾的编辑距离 dp[i][j]
        // 1. 如果 s1[i] == s2[j] 则 dp[i][j] = dp[i-1][j-1] 不需要编辑操作
        // 2. 如果 s1[i] != s2[j] 则可能是修改最后一位,或者 s2 删除一个字符,或者 s1 增加一个字符
        // dp[i][j] = dp[i-1][j-1] + 1 需要一次修改操作,将 s1[i] 修改为 s2[j]
        // dp[i][j] = dp[i-1][j] + 1 需要 s1 新增一个字符等于 s2[j]
        // dp[i][j] = dp[i][j-1] + 1 需要 s1 删除一个字符等于 s1[i]
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
