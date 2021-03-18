package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/18 22:54
 **/
public class _10_RegularExpressionMatching {

    public static void main(String[] args) {
        String s = "";
        String p = ".*";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {

        // dp的定义很好想，参考44题，就是定义dp[i][j]表示原串s的前i个字符能够匹配模式串p的前j个字符

        // 思考dp[i][j]应该由前面的什么状态转移过来
        // 从模式串p上分情况讨论，有以下3种情况

        // 1、模式串p[j]是一个小写字母，那么有且只有当s[i]也是一个相同的小写字母时，s[i]和p[j]才能匹配上，这样(i,j)的状态可以由(i-1,j-1)转移过来
        // 即 dp[i][j] = dp[i-1][j-1]  && p[i] == s[i]

        // 2、模式串p[j]是一个"."符号，那么无论s[i]是什么，s[i]和p[j]都能匹配上，因为"."符号能够匹配任何一个字符
        // 即 dp[i][j] = dp[i-1][j-1]

        // 3、模式串p[j]是一个"*"符号 (这里跟44题的区别是，44题的*能够匹配0个或多个任意字符串，而这里的*只能够匹配前面那个字母的0次或多次，并非任意字母)
        // 所以这里需要考虑模式串p[j]的前面一个字符p[j-1]
        // 如果p[j-1]不能够够匹配上s[i]，那么可以通过"*"消除p[j-1]，考察p[j-2]能否匹配上s[i]，即由dp[i][j-2]的状态转移得到dp[i][j]
        // 即 dp[i][j] = dp[i][j-2]   当p[j-1]!=s[i]

        // 如果p[j-1]能够匹配上s[i]，即p[j-1] == s[i] || p[j-1] == "."
        // 怎么判断此时的"*"究竟匹配了多少个字符呢

        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();


        boolean[][] dp = new boolean[sLen + 1][pLen + 1];

        // 模式串p和原串s都为空的情况下，匹配成功
        dp[0][0] = true;
        // 二维dp打表一般都需要初始化第一行或者第一列
        // dp[i][0]表示，模式串p为空的情况，原串s不为空，肯定都是匹配失败，直接使用数组的初始值false
        // dp[0][i]表示，模式串不为空，原串为空的情况下
        // 如果第一个字符匹配失败，后面紧跟一个*，就能够消除第一个字符，单独的*必须要后面跟一个*才能够消除
        for (int i = 0; i < pLen; i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }

        // 打表
        // 这里尝试下标和p、s的下标保持一致，这样dp的下标需要+1
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    // 如果p[j] == "*"，且p[j-1]不能和s[i]匹配，那么通过*号消除掉p[j-1]，这样dp[i][j]的状态就由dp[i][j-2]转移过来
                    // 这里需要注意下标的问题
                    if (p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)) {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        // 太难了，这里没想通，我是傻逼
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1]) || dp[i + 1][j - 1];
                    }
                }
            }
        }

        return dp[sLen][pLen];
    }
}