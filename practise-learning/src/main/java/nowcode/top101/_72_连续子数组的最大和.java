package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 20:49
 * Description:
 */

public class _72_连续子数组的最大和 {

    public int FindGreatestSumOfSubArray(int[] array) {
        int n = array.length;
        // 第一步定义 dp 数组,定义 dp[i] 表示以 arr[i] 结尾的连续子数组最大和
        int[] dp = new int[n];

        // 第二步初始值
        dp[0] = array[0];

        // 第三步状态转移方程,考虑以 arr[i] 结尾的连续子数组最大和
        // 如果前面 dp[i-1] 是负数,那么加上 dp[i-1] 只会比 arr[i] 更小
        // 如果前面 dp[i-1] 非负,那么加上 dp[i-1] 大于等于 arr[i]
        int res = array[0];
        for (int i = 1; i < n; i++) {
            dp[i] = array[i];
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + array[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
