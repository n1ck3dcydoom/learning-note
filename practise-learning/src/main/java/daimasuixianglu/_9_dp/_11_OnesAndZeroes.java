package daimasuixianglu._9_dp;

public class _11_OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        // 转换为选取i个字符串，装满元素0容量为m，元素1容量为n的背包（背包有两个维度）

        int len = strs.length;
        // 第一步、定义dp数组
        // 定义dp[k][i][j]表示前k个字符串装满i个0，j个1的背包的最大子串长度
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        // 初始状态
        // 0个字符串，装满0个0，0个1的背包，有且有1种情况，什么都不装，子串长度为0
        dp[0][0][0] = 0;

        // 遍历物品
        for (int k = 1; k <= len; k++) {
            int zero = countZero(strs[k - 1]);
            int one = strs[k - 1].length() - zero;
            // 遍历背包
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 装不下
                    if (i < zero || j < one) {
                        dp[k][i][j] = dp[k - 1][i][j];
                    } else {
                        dp[k][i][j] = Math.max(dp[k][i][j], dp[k - 1][i - zero][j - one] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    private int countZero(String str) {
        int count = 0;
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }
}
