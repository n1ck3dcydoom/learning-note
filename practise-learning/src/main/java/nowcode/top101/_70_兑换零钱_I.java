package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 19:32
 * Description:
 */

public class _70_兑换零钱_I {

    public static void main(String[] args) {
        System.out.println(minMoney(new int[]{2887, 2355, 335, 4472, 4561, 1819, 3808}, 4953));
    }

    public static int minMoney(int[] arr, int aim) {
        // 完全背包问题
        int n = arr.length;
        // 第一步定义 dp 数组，定义 dp[i] 凑出面值为 i 的最少货币数
        int[] dp = new int[aim + 1];

        // 第二步初始值
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[i] = i + 1;
        }

        // 第三步状态转移方程，考虑使用前 i 种货币凑出面值为 j 的最少货币数 dp[i][j]
        // 若不选择第 i 种货币，则 dp[i][j] = dp[i-1][j]
        // 若选择第 i 种货币，则 dp[i][j] = dp[i-1][j-arr[i]] + 1

        // 遍历背包
        for (int i = 1; i <= aim; i++) {
            // 遍历物品
            for (int j = 0; j < n; j++) {
                if (i - arr[j] >= 0 && dp[i] != i + 1) {
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
                }
            }
        }
        return dp[aim] == aim + 1 ? -1 : dp[aim];
    }
}
