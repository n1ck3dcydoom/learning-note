package algorithm.leetcode.primeDp.day19;

public class _72_EditDistance {

    public static void main(String[] args) {
        String s = "";
        String t = "r";
        System.out.println(minDistance(s, t));
    }

    public static int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();
        // 第一步、定义dp数组
        // 定义dp[i][j]表示把w1以i结尾的子串转化为w2以j结尾的子串需要的最少操作步骤
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、初始值
        // w1为空串，每次新增一个w2中的字符
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        // w2为空串，每次新增一个w1中的字符
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        // 第三步、状态转移
        // 考虑w1以i结尾，w2以j结尾的时候，dp[i][j]有三个选择
        // 如果w1能够删掉一个字符，则dp[i][j]=dp[i-1][j]+1;
        // abcd -> uvw 需要k步，现在是abcde -> uvw 要在abcd的基础上删掉e得到abcd
        // 即dp[abcde]=dp[abcd]+1，操作删掉w1，即dp[i][j]=dp[i-1][j]+1

        // 如果w1能够新增一个字符，则dp[i][j]=dp[i][j-1]+1
        // abcd -> uv 需要k步，现在是abcde -> uvw，要在uv的基础上增加一个w得到uvw
        // 即dp[uvw]=dp[uv]+1，操作新增w2，即dp[i][j]=dp[i][j-1]+1

        // 如果w1能够替换一个字符
        // abcd -> uvw 需要k步，现在是abcde -> uvwx，要把abcde的末尾e替换为uvwx的末尾x
        // 如果w1的末尾已经等于w2的末尾，则不需要额外步数，即dp[i][j]=dp[i-1][j-1]
        // 如果w1的末尾不等于w2的末尾，则需要1步，即dp[i][j]=dp[i-1][j-1]+1

        // 求最小值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[m][n];
    }
}