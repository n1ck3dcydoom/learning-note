package leetcode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/25 15:55
 **/
public class _583_DeleteOperationForTwoStrings {

    public static void main(String[] args) {
        String s1 = "sea";
        String s2 = "eat";
        System.out.println(minDistance(s1, s2));
    }

    public static int minDistance(String word1, String word2) {
        // 有点动态规划的味道
        // 实际上求得是s1和s2的最长公共子序列长度

        int m = word1.length();
        int n = word2.length();
        // 第一步、定义dp数组
        // 定义dp[m][n]表示s1在前m个字符，s2在前n个字符时，最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、初始值
        // s1或者s2为空时，都当做初始状态
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }

        // 第三步、递推表达式
        // 考虑dp[i][j]，若s1[i] == s2[j]
        // 则说明在dp[i-1][j-1]的时候，能够加上s1[i],s2[j]构成更长的公共子序列
        // 即dp[i][j] = dp[i-1][j-1] + 1

        // 若s1[i] != s2[j]
        // 则dp[i][j] 由左(dp[i][j-1])和上(dp[i-1][j])两个状态转移过来
        // 即dp[i][j] = max(dp[i][j-1], dp[i-1][j])

        // 开始打表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // 求得两个串的最长公共子序列长度dp[m][n]后
        // 那么从两个串分别删除m-dp[m][n] ，n-dp[m][n]即是最少操作次数
        return m - dp[m][n] + n - dp[m][n];
    }

    public static int minDistance1(String word1, String word2) {
        // 朴素dp

        int m = word1.length();
        int n = word2.length();
        // 第一步、定义dp[m][n]表示在s1的前m个字符，s2的前n个字符时，使得s1和s2相等需要删掉的最少次数
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、初始值
        // 同样的，s1或s2是空串的时候，都是初始状态
        // 例如，当s2是空串的时候，从s1变为空串需要删除的即为s1的长度
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 同理，当s1是空串的时候，从s2变为空串需要删除的即为s2的长度
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 第三步、递推表达式
        // 考虑dp[i][j]
        // 若s1[i] == s2[j]，则说明不需要删除s1[i]和2[j]
        // 即dp[i][j] = dp[i-1][j-1]

        // 若s1[i] != s2[j]
        // dp[i][j]由两种状态转移过来
        // 1、状态s1[0~i] s2[0~j-1]，让dp[i][j-1]相同时的删除次数，加上删掉s2[j]的1次
        // dp[i][j] = dp[i][j-1] + 1
        // 2、状态s1[0~i-1] s2[0~j]，让dp[i-1][j]相同的删除次数，加上删掉s1[i]的1次
        // dp[i][j] = dp[i-1][j] + 1
        // 综上所述，dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + 1

        // 开始打表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 不需要删除s1[i]和s2[j]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 需要删除s1[i]或者s2[j]
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}