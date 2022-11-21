package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 20:40
 * Description:
 */

public class _71_最长上升子序列_I {

    public int LIS(int[] arr) {
        int n = arr.length;
        // 第一步定义 dp 数组,定义 dp[i] 表示以 i 结尾的最长上升子序列长度
        int[] dp = new int[n];

        // 第二步初始状态,以 arr[0] 结尾的最长上升子序列就是他自己一个
        dp[0] = 1;

        // 第三步状态转移方程,考虑以 arr[i] 结尾的最长上升子序列 dp[i]
        // 如果不选 arr[i] ,则 dp[i] = max(dp[0] ~ dp[i-1])
        // 如果选 arr[i], 则 dp[i] = max(dp[j] + 1) && arr[j] < arr[i]
        int res = 1;
        for (int i = 1; i < n; i++) {
            // 如果前面没有比 arr[i] 更小的 arr[j] 存在,则 dp[i] 至少可以取它自己
            dp[i] = 1;
            // 往前遍历,找到所有 arr[j] < arr[i] 的值
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
