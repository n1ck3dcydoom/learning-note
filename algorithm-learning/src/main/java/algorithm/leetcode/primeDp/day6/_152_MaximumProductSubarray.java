package algorithm.leetcode.primeDp.day6;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/26 23:59
 **/
public class _152_MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, -1};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        // 动态规划

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示以nums[n]结尾的乘积最大的乘积
        int[] dp = new int[n + 1];

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = nums[0];

        // 第三步、递推表达式
        // 考虑以nums[i]结尾的最大乘积dp[i]
        // 三个选择
        // 1、nums[i] < 0，则需要乘上dp[i-1]最小的负数乘积，这样负负得正才最大
        // 2、nums[i] > 0，则需要乘上dp[i-1]最大的负数乘积，这样正正才最大
        // 3、nums[i] = 0，前面最小的负数乘积和最大的正数乘积都等于dp[i-1]
        // 每次需要保存两个值最小负数乘积和最大正数乘积

        // 节约空间使用两个变量保存
        int dpmax = nums[0];
        int dpmin = nums[0];
        int res = nums[0];
        for (int i = 2; i <= n; i++) {
            int temp = dpmax;
            // 里层inmax = Math.max(dpmax*nums[i-1], nums[i-1])
            // 如果nums[i-1]>0，那么较大值是dpmax*nums[i-1]
            // 如果nums[i-1]<=0，那么较大值是nums[i-1]
            // 外层dpmax = Math.max(dpmin*nums[i-1], inmax)
            // 如果nums[i-1]<0，那么较大值是dpmin*nums[i-1]
            // 如果nums[i-1]>=0，那么较大值是nums[i-1]
            dpmax = Math.max(dpmin * nums[i - 1], Math.max(dpmax * nums[i - 1], nums[i - 1]));
            // 这里的dpmax可能已经被修改过了，需要注意使用临时变量保存dpmax
            dpmin = Math.min(temp * nums[i - 1], Math.min(dpmin * nums[i - 1], nums[i - 1]));
            res = Math.max(res, dpmax);
        }
        return res;
    }
}