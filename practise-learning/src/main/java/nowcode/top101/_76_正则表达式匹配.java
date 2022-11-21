package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 15:16
 * Description:
 */

public class _76_正则表达式匹配 {

    public static void main(String[] args) {
        System.out.println(match("aab", "c*a*b"));
    }

    public static boolean match(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();
        char[] cs1 = str.toCharArray();
        char[] cs2 = pattern.toCharArray();
        // 第一步定义 dp 数组,定义 dp[i][j] 表示 s1[i] 和 s2[j] 结尾时是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 第二步初始值,两个空串肯定是匹配的
        dp[0][0] = true;
        // 对于第一行,即字符串为空,模式串必须是 x*x* 才能匹配上,如果只考虑奇数位的模式串肯定无法匹配
        for (int i = 1; i <= n; i++) {
            if (cs2[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        // 对于第一列,模式串为空,则肯定不能匹配非空的字符串

        // 第三步状态转移方程,考虑以 s1[i] 和 s2[j] 结尾时是否匹配
        // 若 s2[j] 是普通字符,则 dp[i][j] = dp[i-1][j-1] == true && s1[i] == s2[j]
        // 若 s2[j] == '.' ,则 dp[i][j] = dp[i-1][j-1] 即可
        // 若 s2[j] == '*' ,

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // . 或者最后一位相同,可以匹配
                if (cs2[j - 1] == '.' || cs2[j - 1] == cs1[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cs2[j - 1] == '*') {
                    if (cs2[j - 2] == cs1[i - 1] || cs2[j - 2] == '.') {
                        // 如果 * 前一位能够匹配上
                        // 例如 aab aab* 或者 aa.*
                        // 如果 b* 匹配 0 次 b 则 dp[i][j] = dp[i][j-2]
                        // 如果 b* 匹配 1 次 b 则 dp[i][j] = dp[i][j-1]
                        // 如果 b* 匹配多次 b 则 dp[i][j] = dp[i-1][j] 即字符串 b 不动,模式串 b* 以前所有的 b 都能匹配
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    } else {
                        // 如果 * 前一位匹配不上,但是前两位匹配上,则 x* 可以让 x 匹配 0 次
                        // 例如 aab aabc*
                        // * 前一位 c 匹配不上 b,但是前两位 b 能够匹配上 b,则 c* 可以让 c 匹配 0 次
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
