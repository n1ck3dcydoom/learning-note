package daimasuixianglu._9_dp;

public class _30_UncrossedLines {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // 连接nums1[i]和nums2[j]，保证nums1[i]==nums2[j]
        // 只要保证nums1中出现和nums2相等的子序列即可，相对位置相等

        // 动态规划，最长相等子序列的长度

        // 第一步、定义dp数组
        // 定义dp[i][j]表示nums1以i结尾，nums2以j结尾的最长相等子序列长度
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、状态转移方程
        // 考虑nums1以i结尾，nums2以j结尾
        // 若nums1[i]==nums2[j]，则由i-1和j-1转移，即dp[i][j]=dp[i-1][j-1]+1
        // 若nums1[i]!=nums2[j-1]，则考虑以i，j-1结尾的最长公共子序列长度，i-1，j结尾的最长公共子序列长度，取最大值

        // 第三步、初始值
        // 由状态转移方程可以得到，所有dp[i][j]结果都由dp[i-1][j-1]，dp[i][j-1]，dp[i-1][j]三个方向转移得到
        // 由二维数组打表方向可以发现，来自于上方，左上方和左方，都是历史结果，所以遍历顺序也是从左往右，从上往下打表
        // 初始状态，所有dp的结果都是由左上角的初始值dp[0][0]得到，二维打表一般第0列和第0行也作为初始值

        // 第0行，nums1不选取数字，nums2遍历完
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        // 第0列，nums1遍历完，nums2不选取数字
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 相等
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 不相等
                else {
                    // 固定i，检查j-1结尾；固定j，检查i-1结尾，取最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
