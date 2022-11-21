package nowcode.top101;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 16:41
 * Description:
 */

public class _79_打家劫舍_II {

    public int rob(int[] nums) {
        // 收尾两间房不能同时偷,则动态规划查找两次
        // 第一次从头到倒数第二间
        // 第二次从第二间到尾
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(doRob(Arrays.copyOfRange(nums, 0, n - 1)), doRob(Arrays.copyOfRange(nums, 1, n)));
    }

    public int doRob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 第一步定义 dp 数组,定义 dp[i] 表示偷前 i 间房能够取得的最大收益
        int[] dp = new int[n];

        // 第二步初始值,只有 1 间房肯定偷
        dp[0] = nums[0];
        // 有两间房,选价值大的偷
        dp[1] = Math.max(nums[0], nums[1]);

        // 第三步状态转移方程,考虑偷前 i 间房能够取得的最大收益 dp[i]
        // 如果不偷第 i 间房,则 dp[i] = dp[i-1]
        // 如果偷第 i 间房,则 dp[i] = dp[i-2] + nums[i] 不能偷第 i-1 间房,由 dp[i-2] 转移
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}
